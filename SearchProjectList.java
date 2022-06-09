package poised;

import java.util.List;

/**
 * Abstract class with a method to search a List of projects by name or number,
 * print its information to console and return the Project.
 * 
 * @author Nadia
 * @version 1.0
 */
public abstract class SearchProjectList {

	/**
	 * Constructor made explicit and private so the class can't be instantiated
	 * by the implicit public one.
	 */
	private SearchProjectList() {
	}

	/**
	 * Search and return a Project in a List based on user input of either the 
	 * the project's name or number. Returns the first match and prints its 
	 * <code>toString</code> information, along with its Customer, Contractor
	 * and Architect attributes.
	 * 
	 * @param projList
	 * @param searchTerm
	 * @return first Project that matches the input text
	 */
	public static Project searchProjects(List<Project> projList, String searchTerm) {

		Project project = null;

		for (Project projObj : projList) {
			if (searchTerm.equals(projObj.getProjNumber()) || 
					searchTerm.equals(projObj.getProjName())) {
				project = projObj;
				System.out.println(projObj);
				System.out.println(projObj.getCustomer());
				System.out.println(projObj.getContractor());
				System.out.println(projObj.getArchitect());
				System.out.println(projObj.getEngineer());
				System.out.println(projObj.getManager());
				// exit the loop once a matching project is found
				break;
			} 
		}
		return project;
	}
}

