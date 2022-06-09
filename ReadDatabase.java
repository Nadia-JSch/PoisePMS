package poised;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// a class with the function to parse the entire database into object attributes
/**
 * Class responsible for reading the entire PoisePMS database and parsing that
 * information into a Project object and its related Person objects. That is,
 * objects from the Architect, Customer, Contractor, Engineer and Manager classes.
 * 
 * @author Nadia
 * @version 2.0
 * @since 1 June 2022
 */
public abstract class ReadDatabase {

	// ################### Constants and Constructor ####################
	/**
	 * Constructor made explicit and private so the class can't be instantiated
	 * by the implicit public one.
	 */
	private ReadDatabase() {
	}

	/* create ArrayList for storing project objects created from reading 
	 * "Current projects.txt"
	 */
	static ArrayList<Project> projList = new ArrayList<>();

	/**
	 * Formats numbers used for currency to two decimal places as 
	 * {@value #DATE}
	 */
	private static final String DATE = "yyyy-MM-dd";
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE);



	// ######################### Method #################################
	/**
	 * Connects to the PoisePMS database, runs queries on each of its six tables,
	 * creates new Project and Person objects and sets the queried information
	 * to those object attributes. Each Project is then added to an ArrayList
	 * which is returned.
	 * 
	 * @return					a list of Projects
	 * @throws SQLException		JDBC API error 
	 */
	public static List<Project> readDbGetList() throws SQLException {

		// connect to the DB
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
				"otheruser",
				"swordfish");

		// create direct line to DB for executing queries
		Statement statement1 = connection.createStatement();
		Statement statement2 = connection.createStatement();
		Statement statement3 = connection.createStatement();
		Statement statement4 = connection.createStatement();
		Statement statement5 = connection.createStatement();
		Statement statement6 = connection.createStatement();

		ResultSet resultsProjTable;
		ResultSet resultsCustTable;
		ResultSet resultsContTable;
		ResultSet resultsArchTable;
		ResultSet resultsEngrTable;
		ResultSet resultsMangTable;

		// get data from all tables in the PoisePMD DB
		resultsProjTable = statement1.executeQuery(
				"SELECT * FROM project"
				);
		resultsCustTable = statement2.executeQuery(
				"SELECT * FROM customer"
				);
		resultsContTable = statement3.executeQuery(
				"SELECT * FROM contractor"
				);
		resultsArchTable = statement4.executeQuery(
				"SELECT * FROM architect"
				);
		resultsEngrTable = statement5.executeQuery(
				"SELECT * FROM engineer"
				);
		resultsMangTable = statement6.executeQuery(
				"SELECT * FROM manager"
				);


		while (resultsProjTable.next() && resultsCustTable.next()
				&& resultsContTable.next() && resultsArchTable.next()
				&& resultsEngrTable.next() && resultsMangTable.next()) {
			// create objects and set the people as attributes to the project
			Project aNewProject = new Project();
			Person customerObj = new Customer();
			Person contractorObj = new Contractor();
			Person architectObj = new Architect();
			Person engineerObj = new Engineer();
			Person managerObj = new Manager();
			aNewProject.setContractor(contractorObj);
			aNewProject.setCustomer(customerObj);
			aNewProject.setArchitect(architectObj);
			aNewProject.setEngineer(engineerObj);
			aNewProject.setManager(managerObj);


			// set all project details read from file
			aNewProject.setProjNumber(resultsProjTable.getString("proj_num"));
			aNewProject.setProjName(resultsProjTable.getString("proj_name"));
			aNewProject.setProjType(resultsProjTable.getString("proj_type"));
			aNewProject.setProjAddress(resultsProjTable.getString("proj_addr"));
			aNewProject.setProjErf(resultsProjTable.getString("proj_erf"));
			
			// for the prices, convert the Strings to Doubles
			double feeDouble = Double.parseDouble(resultsProjTable.getString("proj_cost"));
			aNewProject.setFeeTotal(feeDouble);
			double paidDouble = Double.parseDouble(resultsProjTable.getString("proj_paid"));
			aNewProject.setFeePaid(paidDouble);
			
			// convert the String to LocalDate
			LocalDate deadlineLD = LocalDate.parse(resultsProjTable.getString("proj_due_on"), formatter);
			aNewProject.setProjDeadline(deadlineLD);
			// mark project as complete
			if (resultsProjTable.getString("proj_is_done").equals("true")) {
				aNewProject.setProjFinalised();
			}
			
			// set customer details
			customerObj.setFirstName(resultsCustTable.getString("cust_Fname"));
			customerObj.setLastName(resultsCustTable.getString("cust_Lname"));
			customerObj.setPhoneNumber(resultsCustTable.getString("cust_phone"));
			customerObj.setEmail(resultsCustTable.getString("cust_email"));
			customerObj.setWorkAddress(resultsCustTable.getString("cust_addr"));
			// set contractor details
			contractorObj.setFirstName(resultsContTable.getString("cont_Fname"));
			contractorObj.setLastName(resultsContTable.getString("cont_Lname"));
			contractorObj.setPhoneNumber(resultsContTable.getString("cont_phone"));
			contractorObj.setEmail(resultsContTable.getString("cont_email"));
			contractorObj.setWorkAddress(resultsContTable.getString("cont_addr"));
			// set architect details
			architectObj.setFirstName(resultsArchTable.getString("arch_Fname"));
			architectObj.setLastName(resultsArchTable.getString("arch_Lname"));
			architectObj.setPhoneNumber(resultsArchTable.getString("arch_phone"));
			architectObj.setEmail(resultsArchTable.getString("arch_email"));
			architectObj.setWorkAddress(resultsArchTable.getString("arch_addr"));
			// set engineer details
			engineerObj.setFirstName(resultsEngrTable.getString("engr_Fname"));
			engineerObj.setLastName(resultsEngrTable.getString("engr_Lname"));
			engineerObj.setPhoneNumber(resultsEngrTable.getString("engr_phone"));
			engineerObj.setEmail(resultsEngrTable.getString("engr_email"));
			engineerObj.setWorkAddress(resultsEngrTable.getString("engr_addr"));
			// set manager details
			managerObj.setFirstName(resultsMangTable.getString("mang_Fname"));
			managerObj.setLastName(resultsMangTable.getString("mang_Lname"));
			managerObj.setPhoneNumber(resultsMangTable.getString("mang_phone"));
			managerObj.setEmail(resultsMangTable.getString("mang_email"));
			managerObj.setWorkAddress(resultsMangTable.getString("mang_addr"));

			// add the projects to an ArrayList
			projList.add(aNewProject);
		}

		// close connections
		resultsProjTable.close();
		resultsCustTable.close();
		resultsContTable.close();
		resultsArchTable.close();
		resultsEngrTable.close();
		resultsMangTable.close();
		statement1.close();
		statement2.close();
		statement3.close();
		statement4.close();
		statement5.close();
		statement6.close();
		
		// return the list
		return projList;
	}
}
