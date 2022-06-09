
package poised;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This class defines, captures and edits information about projects
 * Project attributes are numerous and include one object of each of the Architect 
 * {@link Architect}, Customer {@link Customer} and 
 * Contractor {@link Contractor} classes. 
 * <p>
 * The "getters" and "setters" are used in this classes further defined methods
 * which capture user input, set these values and also edit them. Its overridden
 * <code>toString</code> method displays attribute values of the <code>Project</code>,
 * excluding those attributes of the <code>Person</code> data type.
 * 
 * @author Nadia
 * @Version 1.00
 * @see WriteTextFile.java {@link poised.Task 24.javadoc.WriteTextFile}
 * @see ReadTextFile.java {@link poised.Task 24.javadoc.ReadTextFile}
 * @see SearchProjectList.java {@link poised.Task 24.javadoc.WriteTextFile}
 * @see EditFinaliseMenu.java {@link poised.Task 24.javadoc.SearchProjectList}
 * @see	FinaliseProject.java {@link poised.Task 24.javadoc.FinaliseProject}
 */
public class Project {

	// ################### Constants and Input Scanner ######################
	/**
	 * The value of DATE_FORMAT is {@value #DATE_FORMAT}
	 */
	// constant to use in formatting dates
	protected static final String DATE_FORMAT = "dd/MM/yyyy";

	/**
	 * The value of MONEY_FORMAT is {@value #MONEY_FORMAT}
	 */
	// for formatting numbers to 2 decimal places
	static final NumberFormat MONEY_FORMAT = new DecimalFormat("#0.00");     

	// new scanner object to read user input
	static Scanner userText = new Scanner(System.in); 


	// ################### Attributes & Constructor ######################
	//initialised so null isn't displayed if the field wasn't captured
	private String projNumber = "none";
	private String projName = "none";
	private String projType = "none";
	private String projAddress = "none";
	private String projErf = "none";
	private double feeTotal = 0;
	private double feePaid = 0;
	private LocalDate projDeadline;

	// Person class attributes
	private Person customer;
	private Person contractor;
	private Person architect;
	private Person engineer;
	private Person manager;

	// Project finialisation attributes
	private boolean projFinalised = false;      
	private LocalDate dateCompleted;

	/**
	 * Public constructor without any parameters to allow for flexible 
	 * instantiating where the object is created before being captured.
	 *  
	 * @returns Project
	 */
	// constructor - no parameters
	public Project() {
	}

	// ################### Getters and setters ######################
	public String getProjNumber() {
		return projNumber;
	}


	public void setProjNumber(String projNumber) {
		this.projNumber = projNumber;
	}

	public String getProjName() {
		return projName;
	}


	public void setProjName(String projName) {
		this.projName = projName;
	}


	public String getProjType() {
		return projType;
	}

	public void setProjType(String projType) {
		this.projType = projType;
	}


	public String getProjErf() {
		return projErf;
	}

	public void setProjErf(String projErf) {
		this.projErf = projErf;
	}

	public double getFeeTotal() {
		return feeTotal;
	}

	public void setFeeTotal(double feeTotal) {
		this.feeTotal = feeTotal;
	}

	public double getFeePaid() {
		return feePaid;
	}

	public void setFeePaid(double feePaid) {
		this.feePaid = feePaid;
	}

	public LocalDate getProjDeadline() {
		return projDeadline;
	}

	public void setProjDeadline(LocalDate projDeadline) {
		this.projDeadline = projDeadline; 
	}

	public String getProjAddress() {
		return projAddress;
	}

	public void setProjAddress(String projAddress) {
		this.projAddress = projAddress;
	}
	// getters and setters for the customer, contractor and architect
	public Person getCustomer() {
		return customer;
	}

	public void setCustomer(Person customer) {
		this.customer = customer;
	}

	public Person getContractor() {
		return contractor;
	}

	public void setContractor(Person contractor) {
		this.contractor = contractor;
	}


	public Person getArchitect() {
		return architect;
	}

	public void setArchitect(Person architect) {
		this.architect = architect;
	}

	public Person getEngineer() {
		return engineer;
	}

	public void setEngineer(Person engineer) {
		this.engineer = engineer;
	}

	public Person getManager() {
		return manager;
	}

	public void setManager(Person manager) {
		this.manager = manager;
	}

	// getters and setters for finalisation attributes
	public boolean isProjFinalised() {
		return projFinalised;
	}

	public void setProjFinalised() {
		this.projFinalised = true;
	}

	public LocalDate getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(LocalDate dateComplete) {
		this.dateCompleted = dateComplete;
	}


	// ################### Methods ######################
	/**
	 * Displays each <code>Project</code> attribute on it's own line. 
	 * Checks if the project has been marked complete and displays "Yes" or "No" 
	 * accordingly. Contains Windows' <code>\r</code> to display information 
	 * on new lines when written to <code>Completed projects.txt</code>.
	 * 
	 * @return output 	A formatted string of the projects's number, name, type, 
	 * 					address, ERF number, cost for the customer to pay, 
	 * 					the amount the customer has paid, deadline, "Yes" or "No" 
	 * 					about the project's completion status.
	 * @since 1.0					
	 */
	@Override
	public String toString() {
		String output = "\n--- Project Information ---\r";
		output += "\nProject number: \t" + projNumber + "\r";
		output += "\nProject name: \t\t" + projName + "\r"; 
		output += "\nProperty type: \t\t" + projType + "\r";
		output += "\nProperty address: \t" + projAddress + "\r"; 
		output += "\nProperty ERF number: \t" + projErf + "\r"; 
		output += "\nProject total cost: \tR" + MONEY_FORMAT.format(feeTotal) +"\r"; 
		output += "\nProject amount paid: \tR" +  MONEY_FORMAT.format(feePaid) +"\r";
		output += "\nProject deadline: \t" + projDeadline + "\r";

		// print "no" if project is not yet finalised, and "yes" if it is
		if (!this.isProjFinalised()) {
			output += "\nProject finalised: \tNo";
		}
		else {
			// mark project as finalised and display date of completion
			output += "\nProject finalised: \tYes\r";
		}
		return output;
	}

	/**
	 * Captures values for the Project's String type attributes.
	 * Asks the user to enter a project number, name, type, address, and ERF
	 * number. Setters assign the entered values to their respective fields.
	 * <p>
	 * <code>projType</code> cannot be empty. It is required by the <code>
	 * Project.generateName></code> method which sets a blank project name
	 * to the property type followed by the customer's surname. For example 
	 * "house Smith". Further, <code>projType</code> along with <code>projAddress</code>, 
	 * cannot contain commas as the regular expression groups used for reading 
	 * the <code>Current projects.txt</code> uses comma delimiters.
	 * 
	 * @param projectObjName A newly created Project from the 'empty' constructor
	 * @throws SQLException 
	 */
	public void addProjectInfo(Project projectObjName) throws SQLException {
		
		/* check if the number is already in the DB, and prompt user for a 
		different new number if so (proj_num is a primary key of project in 
		and throws exception if it's not unique)*/
		boolean isUnique = false;
		while (!isUnique) {
			// request project number
			System.out.println("Please enter a unique project number: ");
			String userProjNum = userText.nextLine();
		
			if (CheckProjNums.isProjNumInDatabase(userProjNum)) {
				System.out.println("Please choose another project number.");
			} else {
				// set project number to project object
				projectObjName.setProjNumber(userProjNum);
				// exit loop
				isUnique = true;
			}
		}
		
		// set project name
		System.out.println("Please enter the project's name: ");
		String userProjName = userText.nextLine();
		projectObjName.setProjName(userProjName);
		// set property type if it doesn't include a comma or isn't blank
		boolean isValid = false;
		while (!isValid) {
			System.out.println("Enter the building type (house, apartment, "
					+ "office etc.): ");
			String userProjType = userText.nextLine();
			if (userProjType.isBlank() || userProjType.contains(",")) {
				System.out.println("Building type cannot be blank or contain commas"); 
			} else {
				projectObjName.setProjType(userProjType);
				isValid = true;
			}
		}
		// set building address as long as its not blank nor contain commas
		boolean isOK = false;
		while (!isOK) {
			System.out.println("Enter the building's street address: ");
			String userProjAddress = userText.nextLine();

			if (userProjAddress.isBlank() || userProjAddress.contains(",")) {
				System.out.println("Building type cannot be blank or contain commas"); 
			} else {
				projectObjName.setProjAddress(userProjAddress);
				isOK = true;
			}
		}
		// set ERF number
		System.out.println("Enter the property's ERF number: ");
		String userProjErf = userText.nextLine();
		projectObjName.setProjErf(userProjErf);
	}

	/**
	 * Sets the cost of the Project to the amount entered if user input 
	 * consists of one or more digits, excluding letters 
	 * and special characters. Keeps requesting input until it complies.
	 * 
	 * @param projectObjName a Project object
	 */
	public void addProjCost(Project projectObjName) {
		// flag to check if the user input is valid
		boolean isValid = false;
		while (!isValid) {
			// total cost to the customer
			System.out.println("Enter the total cost to the customer: ");
			String userProjCost = userText.nextLine();

			// input has to be one or more digits for accurate parsing later
			if (userProjCost.matches("\\d+")) {
				Double feeDouble = Double.parseDouble(userProjCost);
				projectObjName.setFeeTotal(feeDouble);
				isValid = true;
			} else {
				System.out.println("Please enter digits only - no spaces, dots,"
						+ " commas etc.");
			}
		}
	}
	
	/**
	 * Sets the amount that the customer has paid to the Project after 
	 * validating it. Checks if user input consists of one or more digits 
	 * and is free from any letters or special characters. 
	 * Keeps requesting input until it's valid.
	 * 
	 *  
	 * @param projectObjName a Project object
	 */
	// Refactor: method to validate and set the amount paid by the customer
	public void addPaidAmount(Project projectObjName) {
		// new scanner object to read user input
		//		Scanner userInput = new Scanner(System.in);
		// flag to check if the user input is valid
		boolean isValid = false;
		while (!isValid) {
			System.out.println("Enter the amount the customer has paid: ");
			String userPaid = userText.nextLine();
			// input has to be one or more digits for accurate parsing later
			if (userPaid.matches("\\d+")) {
					Double paidDouble = Double.parseDouble(userPaid);
					projectObjName.setFeePaid(paidDouble);
					// set the flag to true to exit the loop
					isValid = true;
			} else {
				System.out.println("Please enter digits only - no spaces, dots, "
						+ "commas etc.");
			}
		}
	}
	
	/*
	 * Sets the deadline to the Project after 
	 * validating that it is entered in dd/MM/YYYY format.
	 * Keeps requesting input until it's valid.
	 * 
	 * @param projectObjName  a Project object
	 */
	// Refactor: method to validate input and set the project deadline
	public void addDeadline(Project projectObjName) {
		// declare input date's format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

		// boolean to break the while loop when input is correct
		boolean isValid = false;

		// request input until it's valid
		while (!isValid) {
			System.out.println("Enter the project deadline (dd/MM/yyyy)");
			String userDeadline = userText.nextLine();
			// try change string date input into LocalDate class type
			try {
				LocalDate formattedDate = LocalDate.parse(userDeadline, formatter);
				projectObjName.setProjDeadline(formattedDate);
				isValid = true;
			} catch (Exception e) {
				System.out.println(userDeadline + " is not a valid date format");
			}
		}
	}

	/**
	 * Checks if the <code>projName</code> attribute contains an empty string
	 * and creates, sets, a project name by combining the property 
	 * type with the customer surname if so. For example, "House Tyson". 
	 * 
	 * @param projObj   	Project object
	 * @param customerObj	Customer object with surname captured
	 */
	public void generateName(Project projObj, Person customerObj) {
		// set new name if it doesn't equal one or more letters or digits
		if (!projObj.getProjName().matches("\\w+")) {
			String newName = projObj.getProjType() 
					+ " " + customerObj.getLastName();
			projObj.setProjName(newName);
			System.out.printf("Project name set as \'%s\'%n", newName);
		}
	}
}
