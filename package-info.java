/**
 * The poised package contains the source code of a project management system.
 * It is created for a fictitious structural engineering firm; "Poised".
 * <p>
 * Details about a project and its customer, contractor, architect, structural 
 * engineer and manager are captured and written to the PoisePMS database. 
 * Projects and information of its associated people can be edited. Projects 
 * can be marked as finalised when a date of completetion is given. Invoices
 * of the completed projects are generated if the customer still has an amount
 * outstanding to pay.
 * <p>
 * The poised package structure:<br>
 * <code>Person</code> is the abstract parent class of 
 * <code>Customer</code>, <code>Contractor.java</code> and 
 * <code>Architect</code>. <code>Project</code> is the parent class
 * of the abstract <code>FinaliseProject</code> class.
 * Therefore, the following are abstract classes dependent on 
 * <code>Project</code> and <code>Person.java</code> and 
 * <code>Person</code>'s subclasses:
 * <ul>
 * <li><code>WriteToDatabase</code>
 * <li><code>ReadDatabase</code>
 * <li><code>SearchProjectList</code>
 * <li><code>EditFinaliseMenu</code>
 * <li><code>FinaliseProject</code>
 * </ul>
 * <p>
 * The program is run from <code>MainMenu</code>.<br>
 * The user chooses various options from the main menu 
 * and it's sub menu <code>EditFinaliseMenu</code>.<br>
 * Main menu functionalities:<br>
 * <ul>
 * <li> View Current Projects
 * <li> View Overdue Projects
 * <li> Create a New Project
 * <li> Search / Edit / Finalise projects
 * <li> Exit 
 * </ul>
 * Sub menu functionalities:<br>
 * <ul>
 * <li>Edit project cost
 * <li>Edit project fee paid
 * <li>Edit project deadline
 * <li>Edit customer phone number
 * <li>Edit customer email address
 * <li>Edit contractor phone number
 * <li>Edit contractor email address
 * <li>Edit architect phone number
 * <li>Edit architect email address
 * <li>Edit engineer phone number
 * <li>Edit engineer email address
 * <li>Edit manager phone number
 * <li>Edit manager email address
 * </ul>
 * Currencies are in ZAR (South African Rand) written with a period before 
 * two decimal places (cents).<br>Dates are input by the user as "dd/MM/yyyy" 
 * and written to the database as "yyyy-MM-DD".
 * 
 * @author Nadia-Jasmine Schmidtke
 * @version 2.00, 1 June 2022
 * @see Architect
 * @see Customer
 * @see Contractor
 * @see Engineer
 * @see Manager
 * @see WriteToDatabase
 * @see ReadDatabase
 * @see SearchProjectList
 * @see EditFinaliseMenu
 * @see	FinaliseProject
 */
package poised;
