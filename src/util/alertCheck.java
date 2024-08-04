package util;

import DAO.DBConnection;
import DAO.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.appointments;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


/**
 * Method contains classes for checking time overlap, if an appointment is within 15min of the user logging into the
 * program.Also contains a method that checks if an appointment time is between opening and closing times.
 */
public class alertCheck {


    /**
     * Method takes selected start, end times and the customer ID and checks if there are any conflicting times for
     * that user.
     *
     * @param selectedStart Selected start time from the appointment selected by the user when updating an appointment.
     * @param selectedEnd   Selected end time from the appointment selected by the user when updating an appointment.
     * @param customerID    Selected Customer ID from the appointment that is selected by the user when updating the
     *                      appointment.\
     * @param appointmentID Appointment ID provided from the update method to compare against another appointment in
     *                      the table.
     * @param switchCase    Number provided by the add and update method to be used the correct switch statement to add
     *                      or update an appointment record.
     * @return Returns a false if a time overlap is not detected and true if an overlap is detected.
     * @throws SQLException SQL error when an error is found within the SQL query.
     */
    public static boolean timeOverLapCheck(LocalDateTime selectedStart, LocalDateTime selectedEnd, Integer customerID,
                                           Integer appointmentID, Integer switchCase) throws SQLException {

        /** List used to store all appointments by the customer ID
         */
        ObservableList<appointments> returnedAppointment = FXCollections.observableArrayList();
        returnedAppointment = searches.searchByCustomerID(customerID);
        int numbCount = 0;
        int aptID = 0;
        int custID = 0;
        LocalDateTime start = null;
        LocalDateTime end = null;


        for (int i = 0; i < returnedAppointment.size(); i++) {

            Timestamp startTS = Timestamp.valueOf(selectedStart);
            Timestamp endTS = Timestamp.valueOf(selectedEnd);


            //SQL Statement takes the start and end times and checks to see if they overlap with any existing
            // appointments in the appointments table. If an overlap is detected, the number 1 or greater is returned.
            try {
                Connection conn = DBConnection.getConnection();
                String sql = "SELECT count(*) as count, Customer_ID as custID, Appointment_ID as ID FROM appointments" +
                        " " +
                        "WHERE (? " +
                        "BETWEEN " +
                        "start" +
                        " AND end OR ? BETWEEN start AND end OR ? < start AND ? > end) group by Appointment_ID;";


                DBQuery.setPreparedStatement(conn, sql);
                PreparedStatement pst = DBConnection.getConnection().prepareStatement(sql);
                pst.setTimestamp(1, startTS);
                pst.setTimestamp(2, endTS);
                pst.setTimestamp(3, startTS);
                pst.setTimestamp(4, endTS);
                pst.execute();
                ResultSet rs = pst.getResultSet();
                while (rs.next()) {
                    numbCount = rs.getInt("count");
                    custID = rs.getInt("custID");
                    aptID = rs.getInt("ID");

                }

            } catch (Exception e) {

                System.out.println("Error in time over lap check" + e);
            }


        }

        //Switch case used to check update or add appointment call.
        switch (switchCase) {


            //When numbcount = 0, no overlap with another appointment is detected. When Numbcount is greater than or
            // equal to 1, an overlap is detected.
            case 1:

                if (numbCount == 0) {
                    appointmentID = null;


                    return false;
                }
                if (numbCount >= 1) {
                    appointmentID = null;

                    return true;
                }
                break;

            //True for not matching appointment and overlaps, false for same appointment
            case 2:


                // Numbcount detected an overlap with another appointment, appointment ID provided and appointment ID
                // detected are the same.

                if (numbCount >= 1 && appointmentID == aptID) {
                    System.out.println("same appointment");
                    return false;
                }
                //Conflicting appointment detected with numbcount, appointmentID provided from the update method
                // and the appointment ID fount from the loop are not the same.
                if (numbCount >= 1 && appointmentID != aptID) {
                    System.out.println("Not matching appointments");
                    return true;
                }
                //No conflicting appointment
                if (numbCount == 0) {
                    System.out.println("Not a conflicting appointment in the list");
                    return false;
                }


                break;

            default:
                System.out.println("end switch");
        }


        return false;

    }


    /**
     * Method takes a local date time and checks if it is within 15min of the user logging into the application.
     *
     * @param startTime Start time of appointments to check if login time is within 15min of the user logging in.
     * @return Returns true if time logging in and start time are within 15min of each other. Returns false if start
     * and loggin time are not within 15min.
     */
    //Method is called at login.
    public static boolean intervalAlert(LocalTime startTime) {
        DateTimeFormatter tFormat = DateTimeFormatter.ofPattern("HH:mm");

        int timeCheck = 15;
        String nowFormatted = null;

        LocalTime now = LocalTime.parse(LocalTime.now().format(tFormat));


        long timeDifference = ChronoUnit.MINUTES.between(now, startTime);
        long interval = timeDifference;



        if (interval >= 0 && interval <= timeCheck) {
            //Start time and login time are within 15min of each other and boolean returns true;
            return true;
        }
        if (interval <= 1) {

            return false;
        }
        if (interval >= 1) {
           
            return false;
        }


        return false;
    }

    /**
     * Method takes in a time parameter and checks if the time is between the opening and closing time.
     *
     * @param time Time varaiable that is checked between the start and end times.
     * @return Returns a boolean if the time is between the start and end time. Returns true if time is between start
     * and end time. Returns false if time is before start or after end time.
     */
    public static boolean businessHourCheck(LocalTime time) {
        boolean businessHourCheck = false;
        DateTimeFormatter tFormat = DateTimeFormatter.ofPattern("HH:mm");

        //Open and close times
        String sSTartTime = "08:00";
        String sEndTime = "22:00";

        LocalTime startTime = LocalTime.parse(sSTartTime, tFormat);
        LocalTime endTime = LocalTime.parse(sEndTime, tFormat);


        if (time.isBefore(startTime)) {

            AlertMessages.appointmentAlert(3, null, null, null);
            return false;
        }
        if (time.isAfter(endTime)) {

            AlertMessages.appointmentAlert(4, null, null, null);
            return false;
        } else {

        }


        return true;


    }

    /**
     * Method checks if the day for any appointment is today and if it matches with the current date, then returns
     * true.
     *
     * @param date Date Parameter from the date of the appointments.
     * @return Returns True if the date matches todays date and if returns false, then no match is found.
     */
    public static boolean dayCheck(LocalDate date) {
        DateTimeFormatter tFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean dayMatch = false;


        LocalDate today = LocalDate.now();

        boolean isEqual = today.isEqual(date);

        if (isEqual == true) {
            dayMatch = true;


        }
        if (isEqual == false) {
            dayMatch = false;


        }


        return dayMatch;
    }

}
