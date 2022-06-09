package poised;
/**
 * Creates Contractor objects to represent and hold information about the person
 * in charge of building a property for the firm "Poised". 
 * An objects fof this class are set as an attribute of the Project 
 * object's <code>contractor</code> attribute. Inherits all attributes and 
 * methods from the Person super class. 
 * 
 * @author Nadia 
 * @version 2.0
 * @see Person {@link poised.Task 24.javadoc.Person}
 */
public class Contractor extends Person {

	/**
	 * An explicit constructor without parameters. Contractor
	 * objects are instantiated before attribute data is set to 
	 * allow for flexibility in creating instances.
	 * @return Contractor an object of the Person data type
	 */
	// 'empty' constructor
	public Contractor() {
	}
	
	/**
	 * Displays each attribute of a Contractor on it's own line. 
	 * 
	 * @return output 	a formatted string of the contractor's name, surname, 
	 * 					phone number, email address and work address
	 * @since 1.0					
	 */
	@Override
	public String toString() {
		String output = "\n--- Contractor's Information ---";
		output += "\nName: \t\t" + firstName;
		output += "\nSurname: \t" + lastName;
		output += "\nPhone number: \t" + phoneNumber;
		output += "\nEmail address: \t" + email;
		output += "\nWork address: \t" + workAddress;
		return output;
	}

}
