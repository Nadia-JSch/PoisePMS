package poised;
/**
 * Creates Customer objects to represent and hold information about a client 
 * of "Poised". This instantiation is set as an attribute of the Project 
 * object's <code>customer</code> attribute. Inherits all attributes and 
 * methods from the Person super class. 
 * 
 * @author Nadia 
 * @version 2.0
 * @see Person {@link poised.Task 24.javadoc.Person}
 */
public class Customer extends Person {
	
	/**
	 * An explicit constructor without parameters. Customer 
	 * objects are instantiated before attribute data is set to 
	 * allow for flexibility in creating instances.
	 * @return Customer an object of the Person data type
	 */
	// 'empty' Person constructor
	public Customer() {
	}
	
	/**
	 * Displays each attribute on it's own line. 
	 * 
	 * @return output 	a formatted string of the customer's name, surname, 
	 * 					phone number, email address and work address
	 * @since 2.0					
	 */
	@Override
	public String toString() {
		String output = "\n--- Customer's Information ---";
		output += "\nName: \t\t" + firstName;
		output += "\nSurname: \t" + lastName;
		output += "\nPhone number: \t" + phoneNumber;
		output += "\nEmail address: \t" + email;
		output += "\nWork address: \t" + workAddress;
		return output;
	}
	
}
