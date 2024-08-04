package util;

import DAO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * All methods in this class search in a list and return the requested object.
 */
public class searches {


    /**
     * Method searches list of all users and returns selected username with their ID.
     * @param userName User to search through the list to find matching username and ID.
     * @return Returns user name and their ID.
     * @throws Exception Exception through if an error is detected when searching.
     */
    public static ObservableList<user> getuserName(String userName) throws Exception {
        ObservableList<user> returnedUser = FXCollections.observableArrayList();
        ObservableList<user> allUsers = userImpl.getAllUsers();
        Integer userID = null;

        for (model.user user : allUsers) {

            if (user.getUserName().contains(userName)) {
                userID = user.getUserID();
                model.user newUser = new user(userID, userName);
                returnedUser.add(newUser);
            }
        }

        return returnedUser;


    }


    /**
     * Method searches for the division using the division ID number.
     * @param divID Division ID number to find the the full division information for the ID number.
     * @return Returns a list of the division list that contains the provided division ID.
     * @throws SQLException Throws SQL exception if an error is detected in SQL.
     */
    public static first_level_divisions getDivNameByID(int divID) throws SQLException {
        ObservableList<first_level_divisions> allDivisions = first_level_Divisons_Imp.getAllDivisions();

        first_level_divisions returnedDiv = null;

        for (int i = 0; i < allDivisions.size(); i++) {
            first_level_divisions id = allDivisions.get(i);
            if (id.getDivision_ID() != divID) {
                continue;
            } else {
                returnedDiv = id;
                break;
            }
        }


        System.out.println(returnedDiv);
        return returnedDiv;
    }

    /**
     * Method searches for a country with the selected country ID.
     * @param countryID Country ID used to search all countries with the selected ID.
     * @return Returns country from country ID
     * @throws Exception Throws exception if an error is found when searching.
     */
    public static countries getCountry(int countryID) throws Exception {

        ObservableList<countries> countriesList = countries_Impl.getAllCountries();
        countries returnedCountry = null;

        for (int i = 0; i < countriesList.size(); i++) {
            countries selectedCountry = countriesList.get(i);
            if (selectedCountry.getCountry_id() != countryID) {
                continue;
            } else {
                returnedCountry = selectedCountry;
            }
        }
        System.out.println(returnedCountry);
        return returnedCountry;
    }

    /**
     * Method used to search for a full customer record using the customer ID variable.
     * @param customerID Customer ID varaible used to search for the full customer record.
     * @return Returns customer from customer ID
     * @throws Exception Throws Exception if an error is found.
     */
    public static customer getCustomer(int customerID) throws Exception {
        customer returnedCustomer = null;

        ObservableList<customer> customerList = customersImpl.selectAllCustomers();

        for (int i = 0; i < customerList.size(); i++) {
            customer selectedCustomer = customerList.get(i);

            if (selectedCustomer.getCustomer_ID() != customerID) {

                continue;
            } else {

                returnedCustomer = selectedCustomer;

            }
        }

        return returnedCustomer;
    }


    /**
     * Method used to search all records to find the one with the matching record ID.
     * @param contactID Contact ID used to find the full contact record.
     * @return Returns the contact record in a list.
     * @throws Exception Throws an exception if an error is found during runtime.
     */
    public static contacts getContact(int contactID) throws Exception {
        contacts returnedContact = null;
        ObservableList<contacts> allContacts = contactsImpl.getallContacts();

        for (int i = 0; i < allContacts.size(); i++)
        {
            contacts selectedContact = allContacts.get(i);

            if(selectedContact.getContact_id() != contactID)
            {
                continue;
            }
            else
            {
                returnedContact = selectedContact;
            }

        }

        /* for (contacts contact : allContacts) {

            if (contact.getContact_id() != contactID) {
                System.out.println("Returned Contact in search " + contact);
                returnedContact = contact;
            }
        }

         */



        return returnedContact;

    }

    /**
     * Method used to search for the user's record using the user name.
     * @param userName Username used to find user's ID.
     * @return Returns a list with the full user's record.
     * @throws Exception Exception thrown if an error is thrown and would crash the program.
     */
    public static ObservableList<user> searchByUserName(String userName) throws Exception {
        ObservableList<user> returnedUser = FXCollections.observableArrayList();
        ObservableList<user> allUsers = userImpl.getAllUsers();

        for (user user : allUsers) {
            if (user.getUserName().contains(userName)) {
                returnedUser.add(user);
            }
        }


        return returnedUser;
    }

    /**
     * Method used to search for customer's appointment using their customer ID.
     * @param custID Customer ID used to search for an appointment.
     * @return Returns a list of the user's appointment(s) using the customer ID.
     * @throws Exception Throws a SQL exception if an error is thrown.
     */
    public static ObservableList<appointments> appointmentsByCustID(Integer custID) throws Exception {
        ObservableList<appointments> returnedAppointment = FXCollections.observableArrayList();
        ObservableList<appointments> allAppointments = appointmentsImpl.getAllAppointments();



        for (appointments appointment : allAppointments) {
            if (appointment.getCustomerID() == custID) {
                returnedAppointment.add(appointment);

            }
        }

        return returnedAppointment;

    }

    /**
     * Method used to search for an appointment using the contact ID for report.
     * @param contactID Contact ID used to search for an appointment using contact ID.
     * @return Returns a list of appointments searched by contactID.
     * @throws SQLException Throws SQL exception if an error is thrown.
     */
    public static ObservableList<appointments> searchByContactID (Integer contactID) throws SQLException {
        ObservableList<appointments> returnedAppointment = FXCollections.observableArrayList();
        ObservableList<appointments> allAppointments = appointmentsImpl.getAllAppointments();

        for (appointments ap : allAppointments)

        {
            if(ap.getContactID() == contactID)
            {
                returnedAppointment.add(ap);
            }

        }



        return returnedAppointment;
    }

    /**
     * Method searches for appoointments by customer ID.
     * @param customerID Customer ID used to find appointments using this ID.
     * @return Returns a list of appointments with the customer ID.
     * @throws SQLException Throws a SQL error if an error is detected.
     */
    public static ObservableList<appointments>searchByCustomerID (Integer customerID) throws SQLException
    {
        ObservableList<appointments> returnedAppointment = FXCollections.observableArrayList();
        ObservableList<appointments> allAppointmets = appointmentsImpl.getAllAppointments();

        for (appointments ap : allAppointmets)
        {
            if (ap.getCustomerID() == customerID)
            {
                returnedAppointment.add(ap);
            }
        }


        return returnedAppointment;
    }



    public static boolean appointmentMatch(Integer appointmentID) throws SQLException {
        ObservableList<appointments> allAppointments = appointmentsImpl.getAllAppointments();

        for(appointments ap : allAppointments)
        {
            if(ap.getAppointment_ID() == appointmentID)
            {
                return true;
            }
        }

        return false;
    }

    public static boolean startEndTimeMatch (LocalDateTime selectedStart, LocalDateTime selectedEnd) throws SQLException {
        ObservableList<appointments> allAppointments = appointmentsImpl.getAllAppointments();
        appointments appointment = null;
        LocalDateTime start = null;
        LocalDateTime end = null;

        for (int i = 0; i < allAppointments.size(); i++)
        {
            appointment = allAppointments.get(i);
            start = appointment.getStartDate();
            end = appointment.getEndDate();

            if (start == selectedStart && end == selectedEnd)
            {
                return true;
            }
        }


        return false;
    }






}

