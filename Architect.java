package poised;

/**
 * Creates Architect objects to represent and hold information about the person
 * in charge of designing a building for the firm "Poised". 
 * An objects of this class are set as an attribute of the Project 
 * object's <code>architect</code> attribute. Inherits all attributes and 
 * methods from the Person super class. 
 * 
 * @author Nadia 
 * @version 2.0
 * @see Person {@link poised.Task 24.javadoc.Person}
 */
public class Architect extends Person {

	/**
	 * An explicit constructor without parameters. Architect
	 * objects are instantiated before attribute data is set to 
	 * allow for flexibility in creating instances.
	 * @return Architect an object of the Person data type
	 */
	// 'empty' constructor
	public Architect() {
	}

	/**
	 * Displays each attribute of an Architect on it's own line. 
	 * 
	 * @return output 	a formatted string of the architect's name, surname, 
	 * 					phone number, email address and work address
	 * @since 2.0					
	 */
	@Override
	public String toString() {
		String output = "\n--- Architect's Information ---";
		output += "\nName: \t\t" + firstName;
		output += "\nSurname: \t" + lastName;
		output += "\nPhone number: \t" + phoneNumber;
		output += "\nEmail address: \t" + email;
		output += "\nWork address: \t" + workAddress;
		return output;
	}

}
