package poised;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Child class of Project Class to handle finalising requirements of 
 * marking a project as complete, generating invoice if there's an amount 
 * owing and writing finalised project information to the database.
 * 
 * @author Nadia
 * @version 2.0
 * @since 1 June 2022
 *
 */
public abstract class FinaliseProject extends Project{
	
	
	/**
	 * Marks a project as complete by capturing the date of completion,
	 * and setting the <code>projFinalised</code> Project attribute to 
	 * <code>true</code>. Updates the database by adding the date of completion
	 * and changing the <code>proj_is_done</code> value to <code>1</code> (true).
	 * 
	 * @param projectObjName	the Project bject to be finalised
	 * @throws SQLException  	JDBC API error
	 * @throws Exception 		If the input can't be parsed from a string to 
	 * 						 	LocalDate type in the dd/MM/yyyy format
	 */
	public static void finaliseDate(Project projectObjName) throws SQLException {
		
		// date format  
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		
		// request date from user in dd/MM/yyyy format
		System.out.println(""
				+ "Please enter the project's date of completion (dd/MM/yyyy)");
		String userDateComplete = userText.nextLine();
		
		// establish connection to the DB
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
				"otheruser",
				"swordfish");
		Statement statement = connection.createStatement();
		
	
		// keep requesting input until a date in dd/MM/yyyy format is entered
		while (projectObjName.getDateCompleted() == null) {
			try {
				LocalDate formattedDate = LocalDate.parse(userDateComplete, formatter);
				// set date to Project object
				projectObjName.setDateCompleted(formattedDate);
				/* if the completion date is successfully set, 
				 * then set Boolean projFinalised to true */
				projectObjName.setProjFinalised();
				
				/* add date to the 'proj_done_on' column of the project table */ 
				statement.executeUpdate(
						"UPDATE project SET proj_done_on='" + formattedDate
						+ "' WHERE proj_num=" + projectObjName.getProjNumber()
						);
				
				/* change boolean 'proj_is_done' to true in the DB*/ 
				statement.executeUpdate(
						"UPDATE project SET proj_is_done=true"
						+ " WHERE proj_num=" + projectObjName.getProjNumber()
						);
				
				// display success message
				System.out.println("Project marked complete\nDate completed: " 
				+ projectObjName.getDateCompleted());
	
			} catch (Exception e) {
				System.out.println(userDateComplete + " is not a valid date");
			}
		}
	}
	/**
	 * Checks if there is a difference between and <code>feeTotal</code> 
	 * and <code>feePaid</code> and prints that difference along with 
	 * the Customer details as an invoice.
	 * 
	 * @param projectObjName	the Project to be finalised
	 */
	// create a method to generate an invoice if necessary
	public static void generateInvoice(Project projectObjName) {
		// for formatting numbers to 2 decimal places
		NumberFormat formatter = new DecimalFormat("#0.00");
		
		// calculate the difference between the total and paid amounts
		double owing = projectObjName.getFeeTotal() - projectObjName.getFeePaid();
		if (owing > 0) {
			// display customer details and amount owing
			System.out.println("=== Customer Invoice ===\nAmount outstanding: R" 
					+ formatter.format(owing) + "\n"
					+ projectObjName.getCustomer());
		}
	}
	
	
	/**
	 * Removes the completed project from the List of current projects.
	 * 
	 * @param theList		a List of current projects
	 * @param project		the project to be removed from the List
	 * @return 				an update list of current projects
	 */
	public static List<Project> removeCompletedProj(List<Project> theList, Project project) {
		if (project.isProjFinalised()) {
			theList.remove(project);
		}
		return theList;
	}
}
