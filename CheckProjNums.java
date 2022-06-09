package poised;

import java.sql.*;
import java.util.ArrayList;

/**
 * Responsible for checking if a <code>proj_num</code> of the Project table 
 * exists.
 * 
 * @author Nadia
 * @Version 2.0
 * @Since 1 June 2022
 *
 */
public abstract class CheckProjNums {

	/**
	 * Creates an ArrayList of the <code>proj_num</code> of all the records
	 * in the project table and checks if user input matches one of them.
	 * Used to validate that a <code>proj_num</code> is unique when a new
	 * project is being created.
	 * 
	 * @param userEntry			a String of the project number to be checked
	 * @return					<code>true</code> if the <code>userEntry</code>
	 * 							matched to a project number already in the 
	 * 							database, or <code><false</code> if not.
	 * @throws SQLException		JDBC API error
	 */
	public static boolean isProjNumInDatabase(String userEntry) 
			throws SQLException {

		// establish connection to the DB
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
				"otheruser",
				"swordfish");
		Statement statement = connection.createStatement();
		
		// request results of all columns from the table
		ResultSet tableInfo = statement.executeQuery(
				"SELECT proj_num FROM project");

		// create an ArrayList of id numbers to check if user input is there
		ArrayList<String> projNums = new ArrayList<>();
		while (tableInfo.next()) {
			projNums.add(tableInfo.getString(1));
		}
		// close DB connection
		connection.close();
		statement.close();
		
		return (projNums.contains(userEntry));
	}
}
