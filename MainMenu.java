
package poised;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * The main/driver code for the Poised Project Management System that displays
 * the main menu option and handles the user's selection accordingly.
 *
 * @author Nadia Schmidtke
 * @version 2.00, 1 June 2022
 */
public class MainMenu {

	/**
	 * Creates a List of projects using data read from <code></code> before 
	 * display the main menu options and using a <code>switch</code> block
	 * to handle the option selected by the user. Users can choice to view 
	 * current projects, overdue projects, create a new project, edit and 
	 * finalise a specific project.
	 * 
	 * @param args 					the Java main method's string input parameter
	 * @throws SQLException 		JDBC API error
	 * @see Project
	 * @see Person
	 * 
	 */
	public static void main (String[] args) throws SQLException {

		// new scanner object to read user input
		try (Scanner userInput = new Scanner(System.in)) {
			// display welcome message
			System.out.println("Welcome to the Poised Project Manager!\n");


			// call method to read text file and return a list of projects
			List<Project> getListFromDb = ReadDatabase.readDbGetList();

			// === Main menu options ===
			String menuChoice;
			// do/while ends when the user inputs 'e'
			do {
				String mainMenu = """

						====== Main Menu Options ====== 
						v  -  View Current Projects
						o  -  View Overdue Projects
						c  -  Create a New Project
						s  -  Search / Edit / Finalise 
						e  -  Exit 
						Please enter your selection: """;

				// print out menu and read the user's input
				System.out.println(mainMenu);
				menuChoice = userInput.nextLine().toLowerCase();
				System.out.printf("Menu selection: \'%s\'%n", menuChoice );


				switch (menuChoice) {

				/* display all current projects in the list
				 which are all of them because completed projects are removed
				 from the list */

				case "v" -> {
					// print each list element without using a for loop
					System.out.println(getListFromDb.size());
					getListFromDb.forEach(System.out::println);
				}

				// display projects in the list that are overdue
				case "o" -> {
					for (Project project : getListFromDb) {
						/* check if a project's due date is before 
						 * today's date and display it */
						if (project.getProjDeadline().isBefore(LocalDate.now())) {
							System.out.println("=== Overdue Project ===");
							System.out.println(project);
						} 
					}	
				}
				/* ############### Create New Project ###########
				 create a new project, save it to the List and write to file */
				case  "c" -> {
					// === create new project and people objects ===
					Project projectTemp = new Project();
					Person customerObj = new Customer();
					Person contractorObj = new Contractor();
					Person architectObj = new Architect();
					Person engineerObj = new Engineer();
					Person managerObj = new Manager();
					// set people objects as project attributes
					projectTemp.setContractor(contractorObj);
					projectTemp.setCustomer(customerObj);
					projectTemp.setArchitect(architectObj);
					projectTemp.setEngineer(engineerObj);
					projectTemp.setManager(managerObj);

					/* display message informing the user that capturing data
					is a long process */
					System.out.println("""
							=== Note ===
							You are about to start entering lots of information.
							First you'll enter information about the project. 
							Next, you'll enter details about the customer and 
							contractor. 
							Finally, you'll enter details about the architect. 
							It'll take some time, but it's necessary. 
							Thank you for your time!
							===========
							""");

					/* ###########  Capturing Project & People Data  #########
						capture data & set project number, 
					 	name, type, address and ERF number */
					projectTemp.addProjectInfo(projectTemp);
					// capture data and set the project's cost
					projectTemp.addProjCost(projectTemp);
					// capture and set the amount paid
					projectTemp.addPaidAmount(projectTemp);
					// method to capture and set the project deadline
					projectTemp.addDeadline(projectTemp);

					// capture customer details and set as project property
					System.out.println();
					System.out.println("=== Adding customer details ===");
					customerObj.capturePersonInfo(customerObj, "customer");

					// call method to set project name if it was left blank 
					projectTemp.generateName(projectTemp, customerObj);

					// capture employees details and set to project 
					System.out.println("=== Adding contractor details ===");
					contractorObj.capturePersonInfo(contractorObj, "contractor");
					System.out.println("=== Adding architect details ===");
					architectObj.capturePersonInfo(architectObj, "architect");
					System.out.println("=== Adding engineer details ===");
					engineerObj.capturePersonInfo(engineerObj, "engineer");
					System.out.println("=== Adding manager details ===");
					managerObj.capturePersonInfo(managerObj, "manager");

					// === update project list and write that info to file ===
					getListFromDb.add(projectTemp);
					WriteToDatabase.addProjectToDatabase(projectTemp);
				}

				// ################ Search, Edit, Finalise #################
				case "s" -> {
					System.out.println("Enter the project number to search: ");
					String searchNum = userInput.nextLine();

					Project projectToView = SearchProjectList
							.searchProjects(getListFromDb, searchNum);

					// edit the selected project if a match was found e.g change cost
					if (projectToView != null) {
						// call editing submenu function 
						EditFinaliseMenu.edits(projectToView);
						// remove completed project from the current project list
						FinaliseProject.removeCompletedProj(getListFromDb, projectToView);	

					} else {
						System.out.println("Match not found. Please try again.");
					}

				}
				// print goodbye message
				case "e" -> 
				System.out.println("Thank you for using the project manager!");

				default -> 
				System.out.println("Incorrect selection. Please try again.");
				} 
				// exit loop when user enters "ex"
			} while (!(menuChoice.equals("e")));
		}  
	}
} 



