# PoisePMS
This application allows employees at a fictitious structural engineering company called "Poised" to capture information about building projects. Projects can be searched for, edited and marked as complete. All overdue projects can be viewed, as well as current projects.

## Description

Information about all projects, or specific projects, can be viewed. Specific projects can be searched for using either the project name or number, and then edited.
Projects can be marked as finalised and the date of completion is requested by the user. If the customer still has an outstanding amount to be paid, an invoice is generated that includes the customer's details.
In addition, a list of overdue projects can be viewed.

The following information about each project is stored in a MySQL database:
  - Project number (unique ID)
  - Project name
  - Building type (house, shopping mall, office block, apartment etc.)
  - Physical address
  - ERF number (zoning number)
  - Total project cost to the customer
  - Amount paid to date
  - Project deadline
  -The name, phone number, email address and work address of the following people:
    * Architect
    * Contractor
    * Customer
    * Project Manager
    * Structural Engineer

## Dependencies

* Java Database Connectivity MySQL API is used to communicate with the DB server.

Import the following libraries:
* java.sql*
* java.time.LocalDate
* java.util*

## Images
### Main menu options
![Main menus screenshot](https://github.com/Nadia-JSch/PoisePMS/blob/master/main%20menu.png)

### Sub menu options
![sub menu screenshot](https://github.com/Nadia-JSch/PoisePMS/blob/master/sub%20menu.png)

### Project search result
![Search result screenshot](https://github.com/Nadia-JSch/PoisePMS/blob/master/search%20example.png)

## Authors

Nadia Schmidtke [Email me](https://nadia-jsch.github.io/nadia-schmidtke-resume/Contact.html)

## License

This project is licensed under the [GNU GENERAL PUBLIC LICENSE](https://github.com/Nadia-JSch/PoisePMS/blob/master/license).

## Version History

* 2.0 (this version)
    - Data read and written to a local MySQL database
   
* [1.0](https://github.com/Nadia-JSch/Project-management-system)
    * first version
      - The core functionality was achieved.
      - Data was read and written to text files
