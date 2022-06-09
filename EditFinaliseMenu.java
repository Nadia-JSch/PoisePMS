package poised;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Abstract class that prints the edit and finalisation sub menu options and 
 * uses a <code>switch</code> block to perform the user specified action(s).
 * Calls functions that use getters and setters as defined in the 
 * <code>Project</code> and 
 * <code>Person</code> classes, to edit the attributes.
 * Calls methods from the <code>FinaliseProject</code> class that mark projects
 * as complete, set date of completion, generate an invoice if there is an 
 * outstanding amount, and removes the 
 * project from the current objects List.
 * 
 * @author Nadia
 * @version 2.0
 * @since 1 June 2022
 * @see Project.addPaidAmount
 * @see Project.addProjCost
 * @see Project.addDeadline
 * @see Person.editPersonPhone
 * @see Person.editPersonEmail
 * @see Project.getCustomer
 * @see Project.getContractor
 * @see FinaliseProject.finaliseDate
 * @see FinaliseProject.generateInvoice
 * @see FinaliseProject.writeCompletedProjToFile
 * 				
 */
public abstract class EditFinaliseMenu {

	/**
	 * Constructor made explicit and private so the class can't be instantiated
	 * by the implicit public one.
	 */
	private EditFinaliseMenu() {
	}

	// new scanner object to read user input
	static Scanner userEntry = new Scanner(System.in); 

	/**
	 * A string of sub menu options used exclusively 
	 * for passing into the <code>edits</code> method of the same class.
	 * @value #EDITING_MENU}
	 */
	private static final String EDITING_MENU = """

			=== Editing Options === 
			(1)  -  Edit project cost
			(2)  -  Edit project fee paid
			(3)  -  Edit project deadline
			(4)  -  Edit customer phone number
			(5)  -  Edit customer email address
			(6)  -  Edit contractor phone number 
			(7)  -  Edit contractor email address
			(8)  -  Edit architect phone number 
			(9)  -  Edit architect email address
			(10) -  Edit engineer phone number 
			(11) -  Edit engineer email address
			(12) -  Edit manager phone number 
			(13) -  Edit manager email address
			(f)  -  Finalise Project
			(mm) -  Return to the main menu

			Please enter your selection: """;

	/**
	 * Displays a sub menu with options to alter selected details of a Project
	 * or to mark it as complete. Updates both Project and Person objects along
	 * with relevant database value.
	 * <p>
	 * Call methods to valid user's input where necessary, set that 
	 * input to the relevant object attribute, and change that 
	 * value in the database.
	 * <p>
	 * Calls methods from the <code>FinaliseProject</code> class. That is, 
	 * the <code>finaliseDate</code> method to 
	 * record the Project's date of completion, and the <code>generateInvoice</code> 
	 * method to display an invoice to the user if there is still an amount 
	 * owing.
	 * 
	 * @param project 			returned object from the 
	 * 							<code>searchProjects</code> method
	 * @throws SQLException  	JDBC API error
	 */
	public static void edits(Project project) throws SQLException {
		String menuChoice;
		do {
			// print out menu and user's input
			System.out.println(EDITING_MENU);
			menuChoice = userEntry.nextLine().toLowerCase();
			System.out.printf("Menu selection: \'%s\'%n", menuChoice);

			// connect to DB
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
					"otheruser",
					"swordfish");
			Statement statement = connection.createStatement();


			switch (menuChoice) {
			/**
			 * Call methods to valid user's input where necessary, set that 
			 * input to the relevant object attribute, and change that 
			 * value in the database.
			 */

			// change project deadline (project table in DB)
			case "1" -> {
				// change object's attribute
				project.addProjCost(project);
				// get changed value
				double p_cost = project.getFeeTotal();
				// update DB with the changed value
				statement.executeUpdate(
						"UPDATE project SET proj_cost=" + p_cost + " WHERE proj_num="
								+ project.getProjNumber());
			}

			// change project deadline (project table in DB)
			case "2" -> {
				// change object's attribute
				project.addPaidAmount(project);
				// get changed value
				double p_paid = project.getFeePaid();
				// update DB with the changed value
				statement.executeUpdate(
						"UPDATE project SET proj_paid=" + p_paid + " WHERE proj_num="
								+ project.getProjNumber());
			}

			// change project deadline (project table in DB)
			case "3" -> {
				// change object's attribute
				project.addDeadline(project);
				// get changed value
				LocalDate p_deadline = project.getProjDeadline();
				// update DB cell in table with the changed value
				statement.executeUpdate(
						"UPDATE project SET proj_due_on='" + p_deadline + "' WHERE proj_num="
								+ project.getProjNumber());
			}

			// change customer phone number (customer table in DB)
			case "4" -> {
				// change object's attribute
				project.getCustomer()
				.editPersonPhone(project.getCustomer(), "Customer");
				// get changed value
				String cu_phone = project.getCustomer().getPhoneNumber();
				// update DB cell in table with the changed value
				statement.executeUpdate(
						"UPDATE customer SET cust_phone='" + cu_phone + "' WHERE proj_num="
								+ project.getProjNumber());
			}	

			// change customer email (customer table in DB)
			case "5" -> {
				// change object's attribute
				project.getCustomer()
				.editPersonEmail(project.getCustomer(), "Customer");
				// get changed value
				String cu_email = project.getCustomer().getEmail();
				// update DB cell in table with the changed value
				statement.executeUpdate(
						"UPDATE customer SET cust_email='" + cu_email + "' WHERE proj_num="
								+ project.getProjNumber());

			}

			// change contractor phone number (contractor table in DB)
			case "6" -> {
				// change object's attribute
				project.getContractor()
				.editPersonPhone(project.getContractor(), "Contractor");
				// get changed value
				String co_phone = project.getContractor().getPhoneNumber();
				// update DB cell in table with the changed value
				statement.executeUpdate(
						"UPDATE contractor SET cont_phone='" + co_phone + "' WHERE proj_num="
								+ project.getProjNumber());
			}

			// change contractor email (contractor table in DB)
			case "7" -> {
				// change object's attribute
				project.getContractor()
				.editPersonEmail(project.getContractor(), "Contractor");
				// get changed value
				String co_email = project.getContractor().getEmail();
				// update DB cell in table with the changed value
				statement.executeUpdate(
						"UPDATE contractor SET cont_email='" + co_email + "' WHERE proj_num="
								+ project.getProjNumber());
			}

			// change architect phone number (architect table in DB)
			case "8" -> {
				// change object's attribute
				project.getArchitect()
				.editPersonPhone(project.getArchitect(), "Architect");
				// get changed value
				String ar_phone = project.getArchitect().getPhoneNumber();
				// update DB cell in table with the changed value
				statement.executeUpdate(
						"UPDATE architect SET arch_phone='" + ar_phone + "' WHERE proj_num="
								+ project.getProjNumber());
			}

			// change architect email (architect table in DB)
			case "9" -> {
				// change object's attribute
				project.getArchitect()
				.editPersonEmail(project.getArchitect(), "Architect");
				// get changed value
				String ar_email = project.getArchitect().getEmail();
				// update DB cell in table with the changed value
				statement.executeUpdate(
						"UPDATE architect SET arch_email='" + ar_email + "' WHERE proj_num="
								+ project.getProjNumber());
			}

			// change engineer phone number (engineer table in DB)
			case "10" -> {
				// change object's attribute
				project.getEngineer()
				.editPersonPhone(project.getEngineer(), "Engineer");
				// get changed value
				String en_phone = project.getEngineer().getPhoneNumber();
				// update DB cell in table with the changed value
				statement.executeUpdate(
						"UPDATE engineer SET engr_phone='" + en_phone + "' WHERE proj_num="
								+ project.getProjNumber());
			}
			// change engineer email (engineer table in DB)
			case "11" -> {
				// change object's attribute
				project.getEngineer()
				.editPersonEmail(project.getEngineer(), "Engineer");
				// get changed value from object
				String en_email = project.getEngineer().getEmail();
				// update DB cell in table with the changed value
				statement.executeUpdate(
						"UPDATE engineer SET engr_email='" + en_email + "' WHERE proj_num="
								+ project.getProjNumber());
			}

			// change manager phone number (manager table in DB)
			case "12" -> {
				// change object's attribute
				project.getManager()
				.editPersonPhone(project.getManager(), "Manager");
				// get changed value
				String ma_phone = project.getManager().getPhoneNumber();
				// update DB cell in table with the changed value
				statement.executeUpdate(
						"UPDATE manager SET mang_phone='" + ma_phone + "' WHERE proj_num="
								+ project.getProjNumber());
			}

			// change manager email (manager table in DB)
			case "13" -> {
				// change object's attribute
				project.getManager()
				.editPersonEmail(project.getManager(), "Manager");
				// get changed value from object
				String ma_email = project.getManager().getEmail();
				// update DB cell in table with the changed value
				statement.executeUpdate(
						"UPDATE manager SET mang_email='" + ma_email + "' WHERE proj_num="
								+ project.getProjNumber());
			}


			// #### Finalising project ###
			case "f" -> {
				/* call method to add date completed and mark project as complete
				   both on the project object and in the DB */
				FinaliseProject.finaliseDate(project);

				// check if the customer has an outstanding payment and generate invoice 
				FinaliseProject.generateInvoice(project);

			} 
			case "mm" -> System.out.println();
			default -> System.out.println("Invalid selection. Please try again");
			}
			
			// close connections
			statement.close();
			connection.close();
			
			// exit to main menu
		} while (!menuChoice.equals("mm"));
	}
}
