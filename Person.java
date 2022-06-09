package poised;

import java.util.Scanner;
/**
 * This class defines, captures and edits the personal details 
 * of people set as properties to Project objects.<code>Person</code> 
 * is the non-instantiable abstract parent class of the 
 * instantiable Architect {@link Architect}, Customer {@link Customer} and 
 * Contractor {@link Contractor} classes.  
 * <p>
 * Its "getters" and "setters" are used to define additional methods
 * for getting user input to set values and also for editing properties. 
 * All of its methods are defined except for the abstract <code>toString</code> 
 * method, which is uniquely defined by each of its subclasses.
 * 
 * @author Nadia
 * @Version 1.00
 * @see Architect.java {@link poised.Task 24.javadoc.Architect}
 * @see Customer.java {@link poised.Task 24.javadoc.Customer}
 * @see Contractor.java {@link poised.Task 24.javadoc.Contractor}
 */
public abstract class Person {

	Scanner userInput = new Scanner(System.in);
	protected String firstName = "none";
	protected String lastName = "none";
	protected String phoneNumber = "none";
	protected String email = "none";
	protected String workAddress = "none";

	/**
	 * Sole constructor without any parameters. Not to be used to create Person
	 * objects but to be inherited and overridden by the subclasses' own 
	 * constructors. 
	 */
	protected Person() {
	}

	/**
	 * Gets the value of the <code>firstName</code> field  
	 * for {@link Architect}, {@link Customer}, {@link Contractor} objects.
	 * 
	 * @return a person's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets a String value to the <code>firstName</code> field
	 * 
	 * @param firstName a string of the name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Gets the String value of the <code>LastName</code> field
	 * 
	 * @return a person's surname
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Sets a String value to the <code>lastName</code> field
	 * 
	 * @param lastName a string of the surname
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Gets the String value of the <code>phoneNumber</code> field
	 * 
	 * @return a person's contact number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * Sets a String value to the <code>phoneNumber</code> field
	 * 
	 * @param phoneNumber a string of the contact number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * Gets the String value of the <code>email</code> field
	 * 
	 * @return a person's email address
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Sets a String value to the <code>email</code> field
	 * 
	 * @param email a string email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Gets the String value of the <code>workAddress</code> field
	 * 
	 * @return the work address of a person
	 */
	public String getWorkAddress() {
		return workAddress;
	}
	/**
	 * Sets a String value to the <code>workAddress</code> field
	 * 
	 * @param workAddress a string of the work address 
	 */
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	
	
	/**
	 * * Abstract method to be overridden by subclass toString methods.
	 */
	public abstract String toString();

	
	/**
	 * Captures the first name, last name, contact number, email and work 
	 * address of an object from one of the subclasses. Prints instructions and 
	 * uses the Scanner class to read user input. Input for the last name
	 * is requested until the user enters something besides an empty string. 
	 * The input is then set to the <code>personObj</code> using the attributes'
	 * setter methods. 
	 * <p> 
	 * A string matching the object's class is passed to the displayed
	 * instructions. This way the subclasses needn't have their own
	 * overridden methods as all subclasses can customise this inherited 
	 * method's printed messages.
	 * 
	 * @param personObj 	a Person object from the Architect, Customer or
	 * 						Contractor class
	 * @param personType 	a lower case string of personObj's class type; 
	 * 						"architect", "customer" or "contractor"
	 * @since 1.0
	 */
	public void capturePersonInfo(Person personObj, String personType) {
		// add name
		System.out.printf("Please enter the %s's first name: %n", personType);
		String userFirstName = userInput.nextLine();
		personObj.setFirstName(userFirstName);
		
		// while loop ends when input isn't blank
		boolean isValid = false;
		while (!isValid) {
			System.out.printf("Please enter the %s's last name: %n", personType);
			// set surname if input is not and empty string
			String userLastName = userInput.nextLine();
			// Display error message
			if (userLastName.isBlank()) {
				System.out.println("Surname cannot be blank."); 
			} else {
				personObj.setLastName(userLastName);
				isValid = true;
			}
		}
		// add phone number
		System.out.printf("Please enter the %s's contact number: %n", personType);
		String userPersNum = userInput.nextLine();
		personObj.setPhoneNumber(userPersNum);
		// add their email address
		System.out.printf("Please enter the %s's email address: %n", personType);
		String userEmail = userInput.nextLine();
		personObj.setEmail(userEmail);
		// add the work address
		System.out.printf("Please enter the %s's work address: %n", personType);
		String userAddress = userInput.nextLine();
		personObj.setWorkAddress(userAddress);
	}

	
	/**
	 * Changes the value set to an object's <code>phoneNumber</code> attribute.
	 * Displays the current value and asks for the user to input the new value.
	 * The new value is set, and a confirmation message it displayed to the 
	 * console.
	 *  <p>
	 * A string matching the object's class is passed to the displayed
	 * instructions. This way the subclasses needn't have their own
	 * overridden methods as all subclasses can customise this inherited 
	 * method's printed messages.
	 * 
	 * @param personObjName		a Person object from the Architect, Customer or
	 * 							Contractor class
	 * @param personType		an upper case string of personObj's class type; 
	 * 							"Architect", "Customer" or "Contractor"
	 * @since 1.0
	 */
	public void editPersonPhone(Person personObjName, String personType) {
		// show the current number
		System.out.printf("%s's current phone number: %s%n", personType,
				personObjName.getPhoneNumber());
		// capture new number
		System.out.println("Please enter the new phone number: ");
		String userPhoneNum = userInput.nextLine();
		personObjName.setPhoneNumber(userPhoneNum);
		// show updated user profile
		System.out.println("Phone number updated\n" + personObjName.toString());
	}

	
	/**
	 * Changes the value set to an object's <code>email</code> attribute.
	 * Displays the current value and asks for the user to input the new value.
	 * The new value is set, and a confirmation message it displayed to the 
	 * console.
	 * <p>
	 * A string matching the object's class is passed to the displayed
	 * instructions. This way the subclasses needn't have their own re-defined
	 * overridden methods.
	 * 
	 * @param personObjName		a Person object from the Architect, Customer or
	 * 							Contractor class
	 * @param personType		an upper case string of personObj's class type; 
	 * 							"Architect", "Customer" or "Contractor"
	 * @since 1.0
	 */
	public void editPersonEmail(Person personObjName, String personType) {
		// show current email address
		System.out.printf("%s's current email address: %s%n", personType, 
				personObjName.getEmail());
		// capture new number
		System.out.println("Please enter the new email address: ");
		String userEmail = userInput.nextLine();
		personObjName.setEmail(userEmail);
		// show updated user profile
		System.out.println("Email address updated\n" + personObjName.toString());
	}
	/**
	 * Changes the value set to an object's <code>workAddress</code> attribute.
	 * Displays the current value and asks for the user to input the new value.
	 * The new value is set, and a confirmation message it displayed to the 
	 * console.
	 * <p>
	 * A string matching the object's class is passed to the displayed
	 * instructions. This way the subclasses needn't have their own
	 * overridden methods defined.
	 * 
	 * @param personObjName		a Person object from the Architect, Customer or
	 * 							Contractor class
	 * @param personType		an upper case string of personObj's class type; 
	 * 							"Architect", "Customer" or "Contractor"
	 * @since 1.0
	 */
	public void editPersonAddress(Person personObjName, String personType) {
		// show current address
		System.out.printf("%s's current work address: %s%n", personType, 
				personObjName.getWorkAddress());
		// capture new number
		System.out.println("Please enter the new email address: ");
		String userAddress = userInput.nextLine();
		personObjName.setWorkAddress(userAddress);
		// show updated user profile
		System.out.println("Work address updated\n" + personObjName.toString());
	}

}