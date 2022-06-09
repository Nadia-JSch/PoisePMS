package poised;

/**
 * Creates Manager objects to represent and hold information about the person
 * in charge of managing a project for the firm "Poised". 
 * An objects of this class are set as an attribute of the Project 
 * object's <code>manager</code> attribute. Inherits all attributes and 
 * methods from the Person super class. 
 * 
 * @author Nadia 
 * @version 2.0
 * @see Person {@link poised.Task 24.javadoc.Person}
 */
public class Manager extends Person {

	/**
	 * An explicit constructor without parameters. Architect
	 * objects are instantiated before attribute data is set to 
	 * allow for flexibility in creating instances.
	 * @return Architect an object of the Person data type
	 */
	// 'empty' constructor
	public Manager() {
	}

	/**
	 * Displays each attribute of a Manager on it's own line. 
	 * 
	 * @return output 	a formatted string of the architect's name, surname, 
	 * 					phone number, email address and work address
	 * @since 1.0					
	 */
	@Override
	public String toString() {
		String output = "\n--- Manager's Information";
		output += "\nName: \t\t" + firstName;
		output += "\nSurname: \t" + lastName;
		output += "\nPhone number: \t" + phoneNumber;
		output += "\nEmail address: \t" + email;
		output += "\nWork address: \t" + workAddress;
		return output;
	}

}