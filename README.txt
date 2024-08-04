Lessons Learned
- Connect to an external MYSQL database
- Add, update and delete data from the MYSQL database
- Login screen with logging to a text file on who tried to log into the application.
- Used an interface to export the log information
- Used DAO to query MYSQL and to implement classes for each needed object.
- Handled and created custom error messages to handle various errors within the application.
- Converted time in UTC format to local time
- Used resource bundle to change language from English to French and back.

Title : C195 Scheduling Application.
Purpose: The purpose of this application is to give the user the ability to add, update and delete customer records and appointments.

Author : Sean Langiewicz
Application Version : 1.0
Date :5/30/2021

IDE : IntelliJ Community 2020.3
JDK Used : 11.0.9
Java JDK Pointed to:C:\Program Files\Java\jdk-11.0.9\lib
JavaFX Version Used : 11.0.2
JavaFX point to: C:\Program Files\Java\jdk-11.0.9
Database Driver Version: 5.1.41
Database Drive pointed to: C:\Program Files\Java\mysql-connector-java-5.1.41.jar

Directions on how to run the program
- At the username / password screen. Please use the provided credentials to log into the application
Next screen you will be show in the main menu and you will be given a few options.

1 - Customer Records - This displays all customer records where you can add, update and delete a record.
	- Add a Customer Record
		- A customer ID will be automatically generated. Please fill out the customer name, address, postal code and phone number. Then please select a country and a division. Once done, click save to add the new record.
	- Update A Customer Record - Please click a record then click update record.
		- When updating a record, please update any fields you would like, but the Customer ID field can not be updated.
	- Delete A Customer Record - Please click a record then click Delete Record.
2 - Reports - This displays a total of 4 reports, with 2 reports combined into 1 screen.
	- 2 displays are shown
		- First Display shows a combination of month and each type of appointment for that month with a total number of appointments.
		- Use the 2 drop down belows to display appointments by a contact or a customer. When changing a contact to customer or customer to contact, the table will be cleared.
3 - Appointments - This displays all appointments, giving you options on how to view them, add, delete and update an appointment.
		- View Calendar Options
			- All - Select to display all appointments
			- Month - Select to display appointments for the current month
			- Week - Select to display appointments for the next 7 days.

		- Add Appointment
			- Enter in an appointment title, description, location and type. Select a contact and customer from the dropdown and the contact and customer ID will be automatically entered. Select a date, hour and minute. When 			selecting a time, please select a time within business hours.
		- Update Appointment - Make sure you select an appointment from the list before selecting update appointment. Select a date, hour and minute. When selecting a time, please select a time within business hours.
			- Make any needed changes within the information provided. Appointment ID will not be editable. 
		- Delete Appointment - Select an appointment before attempting to delete an appointment as you will get an error if you do so.
		- Back to Main Menu - This will take you back to the main appointment screen.
4- Close - This will close the application


Description of the additional report
- Additional report shows appointments by each customer.
