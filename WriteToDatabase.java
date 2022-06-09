package poised;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

/**
 * A class with a dedicated method that writes new project information to
 * the PoisePMS database.
 * 
 * @author Nadia
 * @version 2.0
 * @since 1 June 2022
 *
 */
public abstract class WriteToDatabase {

	// ################ Constants and Constructor #################
	/**
	 * Constructor made explicit and private so the class can't be instantiated
	 * by the implicit public one.
	 */
	private WriteToDatabase() {
	}

	/**
	 * Formats numbers used for currency to two decimal places {
	 * @value #DEC_FORMATTER}
	 */
	static final NumberFormat DEC_FORMATTER = new DecimalFormat("#0.00");  
	
	
	// ######################## Method ###############################
	/**
	 * Takes in a Project object, connects to the PoisePMS database,
	 * gets the Project and associated Person object's information and 
	 * updates the relevant tables in the database with that information.
	 * <p>
	 * Used to add a new project to the database.
	 * 
	 * @param projObj			a Project and it's Person attributes that have
	 * 							values entered into by the user.
	 * @throws SQLException		a JDBC API error
	 */
	public static void addProjectToDatabase(Project projObj) 
			throws SQLException {

		// connect to the DB
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
				"otheruser",
				"swordfish");

		Statement statement1 = connection.createStatement();
		Statement statement2 = connection.createStatement();
		Statement statement3 = connection.createStatement();
		Statement statement4 = connection.createStatement();
		Statement statement5 = connection.createStatement();
		Statement statement6 = connection.createStatement();

		// get project information
		String proj_num = projObj.getProjNumber();
		String proj_name = projObj.getProjName();
		String proj_addr = projObj.getProjAddress();
		String proj_erf = projObj.getProjErf();
		String proj_type = projObj.getProjType();
		
		/* format prices to 2 decimals and change the default 
		 * comma separator into a period */
		String proj_cost = DEC_FORMATTER.format(projObj.getFeeTotal()).replace(',', '.');
		String proj_paid = DEC_FORMATTER.format(projObj.getFeePaid()).replace(',', '.');
		
		LocalDate proj_due_on = projObj.getProjDeadline();
		
		Boolean proj_is_done = projObj.isProjFinalised();
		// leaving proj_done_on as null
		LocalDate proj_done_on = null;
		
		// customer
		String cust_Fname = projObj.getCustomer().getFirstName();
		String cust_Lname = projObj.getCustomer().getLastName();
		String cust_phone = projObj.getCustomer().getPhoneNumber();
		String cust_email = projObj.getCustomer().getEmail();
		String cust_addr = projObj.getCustomer().getWorkAddress();
		// contractor
		String cont_Fname = projObj.getContractor().getFirstName();  
		String cont_Lname = projObj.getContractor().getLastName();   
		String cont_phone = projObj.getContractor().getPhoneNumber(); 
		String cont_email = projObj.getContractor().getEmail();      
		String cont_addr  = projObj.getContractor().getWorkAddress();
		// architect
		String arch_Fname = projObj.getArchitect().getFirstName();   
		String arch_Lname = projObj.getArchitect().getLastName();   
		String arch_phone = projObj.getArchitect().getPhoneNumber(); 
		String arch_email = projObj.getArchitect().getEmail();   
		String arch_addr  = projObj.getArchitect().getWorkAddress(); 
		// architect
		String engr_Fname = projObj.getEngineer().getFirstName();   
		String engr_Lname = projObj.getEngineer().getLastName();   
		String engr_phone = projObj.getEngineer().getPhoneNumber(); 
		String engr_email = projObj.getEngineer().getEmail();   
		String engr_addr  = projObj.getEngineer().getWorkAddress(); 
		// architect
		String mang_Fname = projObj.getManager().getFirstName();   
		String mang_Lname = projObj.getManager().getLastName();   
		String mang_phone = projObj.getManager().getPhoneNumber(); 
		String mang_email = projObj.getManager().getEmail();   
		String mang_addr  = projObj.getManager().getWorkAddress(); 


		// make changes to the project database with the information
		statement1.executeUpdate(
				"INSERT INTO project VALUES ("
						+ "'" + proj_num + "', "
						+ "'" + proj_name + "', "
						+ "'" + proj_addr + "', "
						+ "'" + proj_erf + "', "
						+ "'" + proj_type + "', "
						+ proj_cost + ", "
						+ proj_paid + ", "
						+ "'" + proj_due_on + "', "
						+ proj_is_done + ", "
						+ proj_done_on + ", "
						+ "'" + cust_Fname + "', "
						+ "'" + cust_Lname + "', "
						+ "'" + cont_Fname + "', "
						+ "'" + cont_Lname + "', "
						+ "'" + arch_Fname + "', "
						+ "'" + arch_Lname + "', "
						+ "'" + engr_Fname + "', "
						+ "'" + engr_Lname + "', "
						+ "'" + mang_Fname + "', "
						+ "'" + mang_Lname + "'"
						+ ");");

		// make changes to the customer database with the information
		statement2.executeUpdate(
				"INSERT INTO customer VALUES ("
						+ "'" + cust_Fname + "', "
						+ "'" + cust_Lname + "', "
						+ "'" + cust_phone + "', "
						+ "'" + cust_email + "', "
						+ "'" + cust_addr + "', "
						+ "'" + proj_num + "'"
						+ ");");

		statement3.executeUpdate(
				"INSERT INTO contractor VALUES ("
						+ "'" + cont_Fname + "', "
						+ "'" + cont_Lname + "', "
						+ "'" + cont_phone + "', "
						+ "'" + cont_email + "', "
						+ "'" + cont_addr + "', "
						+ "'" + proj_num + "'"
						+ ");");

		// make changes to the architect database with the information
		statement4.executeUpdate(
				"INSERT INTO architect VALUES ("
						+ "'" + arch_Fname + "', "
						+ "'" + arch_Lname + "', "
						+ "'" + arch_phone + "', "
						+ "'" + arch_email + "', "
						+ "'" + arch_addr + "', "
						+ "'" + proj_num + "'"
						+ ");");

		// make changes to the engineer database with the information
		statement5.executeUpdate(
				"INSERT INTO engineer VALUES ("
						+ "'" + engr_Fname + "', "
						+ "'" + engr_Lname + "', "
						+ "'" + engr_phone + "', "
						+ "'" + engr_email + "', "
						+ "'" + engr_addr + "', "
						+ "'" + proj_num + "'"
						+ ");");


		// make changes to the manager database with the information
		statement6.executeUpdate(
				"INSERT INTO manager VALUES ("
						+ "'" + mang_Fname + "', "
						+ "'" + mang_Lname + "', "
						+ "'" + mang_phone + "', "
						+ "'" + mang_email + "', "
						+ "'" + mang_addr + "', "
						+ "'" + proj_num + "'"
						+ ");");
		
		
		// close connections
		connection.close();
		statement1.close();
		statement2.close();
		statement3.close();
		statement4.close();
		statement5.close();
		statement6.close();
	}
}
