package util;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * Method used to provided alert messages for any errors.
 */
public class AlertMessages


{



    /**
     * Method used to provide error messages for the alerts.
     *
     * @param code Code used to call correct error message.
     * @param appointmentID Appointment ID of the selected appointment.
     * @param date Date from the selected appointment.
     * @param time Time from the selected appointment.
     */
    public static void appointmentAlert (int code, Integer appointmentID, LocalDate date, LocalTime time)
    {
        ResourceBundle rb;
        rb = ResourceBundle.getBundle("util/rb");


        if(code == 1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setContentText(rb.getString("AppointmentAlertPart1")+" "+appointmentID + " " + rb.getString(
                    "AppointmentAlertPart2") + " " + date+" "
                    + rb.getString("AppointmentAlertPart3")+" "+time +".");
            alert.setTitle(rb.getString("AppointmentNotificationTitle"));
           alert.setHeaderText(rb.getString("AppointmentNotificationSoon"));

            alert.showAndWait();

        }
        if(code ==2)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(rb.getString("AppointmentNotificationNoUpcomingAppointment"));
            alert.setTitle(rb.getString("AppointmentNotificationTitle"));
            alert.setHeaderText(rb.getString("AppointmentNotificationNotSoon"));
            alert.showAndWait();
        }
        if (code ==3)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("AppointmentInvalidTime"));
            alert.setHeaderText(rb.getString("AppointmentTimeSelectionError"));
            alert.setContentText(rb.getString("AppointmentTimeBeforeBusinessHours"));
            alert.showAndWait();
        }
        if (code ==4)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("AppointmentInvalidTime"));
            alert.setHeaderText(rb.getString("AppointmentTimeSelectionError"));
            alert.setContentText(rb.getString("AppointmentTimeAfterBusinessHours"));
            alert.showAndWait();
        }


    }

    /**
     *  Method used for login errors.
     *
     * @param code Code to call the correct error message.
     * @param field Field parameter used to highlight field error.
     */
    public static void loginErrors (int code, TextField field)
    {
        ResourceBundle rb;
        rb = ResourceBundle.getBundle("util/rb");

        fieldError(field);
        if (code == 1)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("LoginAlert"));
            alert.setHeaderText(rb.getString("LoginAlertInvalidUsernamePasswordHeader"));
            alert.setContentText(rb.getString("LoginAlertInvalidUsernamePasswordMessage"));
            alert.showAndWait();
        }
        if (code == 2)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(rb.getString("LoginSuccessfulTitle"));
            alert.setHeaderText(rb.getString("LoginSuccessfulHeader"));
            alert.setContentText(rb.getString("LoginSuccessful"));
            alert.showAndWait();
        }
    }

    /** Method provides confirmation when the user deletes an appointment.
     * @param code Integer to select the correct error code.
     * @param appointmentID Appointment ID of the appointment getting deleteed.
     * @param appointmentType Type of appointment being deleted.
     */
    public static void appointmentDeleteMessage (int code, int appointmentID, String appointmentType)
    {
        ResourceBundle rb;
        rb = ResourceBundle.getBundle("util/rb");
        if(code ==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setContentText(rb.getString("DeletionConfirmationPart1")+" "+appointmentID + " " + rb.getString(
                    "DeletionConfirmationPart2") + " " + appointmentType+" "
                    + rb.getString("DeletionPart3"));
            alert.setTitle(rb.getString("DeletionConfirmationTitle"));
            alert.setHeaderText(rb.getString("DeletionConfirmationHeader"));

            alert.showAndWait();
        }




    }

    /** Method provides context for appointment overlaping with another appointment
     * @param code Code call the correct error message.
        */
    public static void appointmentOverLapError (int code)
    {
        ResourceBundle rb;
        rb = ResourceBundle.getBundle("util/rb");

        if(code ==1)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("AppointmentTimeOverlap"));
            alert.setHeaderText(rb.getString("AppointmentTimeOverLapHeader"));
            alert.setContentText(rb.getString("AppointTimeOverLapContext"));
            alert.showAndWait();
        }
    }
    /**
     * Method used for appointment errors.
     * @param code Code to call the correct error message.
     * @param field Field parameter used to highlight field error.
     */
    public static void appointmentErrors(int code, TextField field)
    {
        ResourceBundle rb;
        rb = ResourceBundle.getBundle("util/rb");

        fieldError(field);
        //Name Record Alert
        if(code ==1)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("AppointmentNoTitle"));
            alert.setHeaderText(rb.getString("AppointmentNoTitleHeader"));
            alert.setContentText(rb.getString("AppointmentNoTitlePrompt"));
            alert.showAndWait();
        }
        if(code ==2)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("AppointmentNoDescription"));
            alert.setHeaderText(rb.getString("AppointmentNoDescriptionHeader"));
            alert.setContentText(rb.getString("AppointmentNoDescriptionPrompt"));
            alert.showAndWait();
        }
        if(code ==3)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("AppointmentNoLocation"));
            alert.setHeaderText(rb.getString("AppointmentNoLocationHeader"));
            alert.setContentText(rb.getString("AppointmentNoLocationPrompt"));
            alert.showAndWait();
        }
        if(code ==4)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("AppointmentNoType"));
            alert.setHeaderText(rb.getString("AppointmentNoTypeHeader"));
            alert.setContentText(rb.getString("AppointmentNoHeaderPrompt"));
            alert.showAndWait();
        }
        if(code ==5)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("AppointmentNoCustomer"));
            alert.setHeaderText(rb.getString("AppointmentNoCustomerHeader"));
            alert.setContentText(rb.getString("AppointmentNoCustomerPrompt"));
            alert.showAndWait();
        }


        if(code ==6)
         {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("AppointmentTimeOverlap"));
            alert.setHeaderText(rb.getString("AppointmentTimeOverLapHeader"));
            alert.setContentText(rb.getString("AppointTimeOverLapContext"));
            alert.showAndWait();
        }

        /*
             if(code ==7)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("AppointmentNoCustomer"));
            alert.setHeaderText("AppointmentNoCustomerHeader");
            alert.setContentText("AppointmentNoCustomerPrompt");
            alert.showAndWait();
        }
         */




        if(code ==8)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("AppointmentNoContact"));
            alert.setHeaderText(rb.getString("AppointmentNoContactHeader"));
            alert.setContentText(rb.getString("AppointmentNoContactPrompt"));
            alert.showAndWait();
        }
        if(code ==9)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("AppointmentNoDate"));
            alert.setHeaderText(rb.getString("AppointmentNoDateHeader"));
            alert.setContentText(rb.getString("AppointmentNoDatePrompt"));
            alert.showAndWait();
        }
        if(code ==10)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("AppointmentNoMin"));
            alert.setHeaderText(rb.getString("AppointmentNoMinHeader"));
            alert.setContentText(rb.getString("AppointmentNoMinPrompt"));
            alert.showAndWait();
        }
        if(code ==11)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("AppointmentNoHour"));
            alert.setHeaderText(rb.getString("AppointmentNoHourHeader"));
            alert.setContentText(rb.getString("AppointmentNoHourPrompt"));
            alert.showAndWait();
        }

    }

    /**
     * Method used to highlight all errors in the window.
     * @param code Error code used to call the correct error message.
     * @param title Title text field to highlight.
     * @param description Description text field to highlight.
     * @param location Location text field to highlight.
     * @param type Location text field to highlight.
     */
    public static void addAllAppointmentErrors(int code, TextField title, TextField description, TextField location,
                                           TextField type)
    {
        ResourceBundle rb;
        rb = ResourceBundle.getBundle("util/rb");
        appointmentAllFieldError(title,description,location,type);
        if (code == 1)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("InvalidInput"));
            alert.setHeaderText(rb.getString("InvalidInputHeader"));
            alert.setContentText(rb.getString("InvalidInputPrompt"));
            alert.showAndWait();
        }

    }

    /**
     * Method used to provide error selections in the appointment screen.
     * @param code Code used to select correct error messages.
     * @param field Field used to highlight error field.
     */
    public static void appointmentSelectionErrors(int code, TextField field)
    {
        fieldError(field);

        ResourceBundle rb;
        rb = ResourceBundle.getBundle("util/rb");
        if(code == 1)
        {

            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("NoAppointmentSelected"));
                alert.setHeaderText(rb.getString("NoAppointmentSelectedHeader"));
                alert.setContentText(rb.getString("NoAppointmentSelectedPrompt"));
                alert.showAndWait();
            }
        }
        if(code == 2)
        {
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("DeletionFailed"));
                alert.setHeaderText(rb.getString("DeletionFailedHeader"));
                alert.setContentText(rb.getString("DeletionFailedPrompt"));
                alert.showAndWait();
            }
        }
    }

    /**
     * Method used to highlight record alerts.
     * @param code Code used to select correct error messages.
     * @param field Field used to highlight error field.
     */
    public static void recordAlerts(int code, TextField field)
    {
        ResourceBundle rb;
        rb = ResourceBundle.getBundle("util/rb");
        fieldError(field);

        if(code ==1)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("DeletionFailed"));
            alert.setHeaderText(rb.getString("DeletionFailedHeader"));
            alert.setContentText(rb.getString("DeletionFailedRecordPrompt"));
            alert.showAndWait();
        }
        if (code ==2)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("UpdateCustomerFailed"));
            alert.setHeaderText(rb.getString("UpdateCustomerFailedHeader"));
            alert.setContentText(rb.getString("UpdateCustomerFailedPrompt"));
            alert.showAndWait();
        }
        if(code ==3)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(rb.getString("SuccessfulDeletion"));
            alert.setTitle(rb.getString("DeletionTitle"));
            alert.setHeaderText(rb.getString("DeletionHeader"));
            alert.showAndWait();
        }
        if(code == 4)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("UpdateRecordGeneralError"));
            alert.setHeaderText(rb.getString("UpdateRecordGeneralErrorHeader"));
            alert.setContentText(rb.getString("UpdateRecordGeneralErrorPrompt"));
            alert.showAndWait();
        }
        if (code ==5)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(rb.getString("RecordDeletionSuccessful"));
            alert.setHeaderText(rb.getString("RecordDeletionSuccessfulHeader"));
            alert.setContentText(rb.getString("RecordDeletionSuccessfulPrompt"));
            alert.showAndWait();
        }

    }

    /**
     * Method used to highlight add record alerts.
     * @param code Code used to select correct error messages.
     * @param field Field used to highlight error field.
     */
    public static void addRecordErrors (int code, TextField field)
    {
        fieldError(field);
        ResourceBundle rb;
        rb = ResourceBundle.getBundle("util/rb");


        //Name Record Alert
        if(code ==1)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("RecordNoName"));
            alert.setHeaderText(rb.getString("RecordNoNameHeader"));
            alert.setContentText(rb.getString("RecordNoNamePrompt"));
            alert.showAndWait();
        }
        if(code ==2)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("RecordNoAddress"));
            alert.setHeaderText(rb.getString("RecordNoAddressHeader"));
            alert.setContentText(rb.getString("RecordNoAddressPrompt"));
            alert.showAndWait();
        }
        if(code ==3)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("RecordNoPostal"));
            alert.setHeaderText(rb.getString("RecordNoPostalHeader"));
            alert.setContentText(rb.getString("RecordNoPostalPrompt"));
            alert.showAndWait();
        }
        if(code ==4)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("RecordNoPhoneNumber"));
            alert.setHeaderText(rb.getString("RecordNoPhoneNumberHeader"));
            alert.setContentText(rb.getString("RecordNoPhonePrompt"));
            alert.showAndWait();
        }
        if(code ==5)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("RecordNoCountry"));
            alert.setHeaderText(rb.getString("RecordNoCountryHeader"));
            alert.setContentText(rb.getString("RecordNoCountryPrompt"));
            alert.showAndWait();
        }
        if(code ==6)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("RecordNoDivision"));
            alert.setHeaderText(rb.getString("RecordNoDivisionHeader"));
            alert.setContentText(rb.getString("RecordNoDivisionPrompt"));
            alert.showAndWait();
        }


    }

    /** Method takes code and field as parameters to through the correct error message and to highlight the text field.
     * @param code Code provided by the update customer method to indicate what is missing and to through what error
     *             message.
     * @param field Field to highlight when a field is empty.
     */
    public static void updateRecordAlerts (int code, TextField field)
    {
        fieldError(field);
        ResourceBundle rb;
        rb = ResourceBundle.getBundle("util/rb");

      //Customer Name Error
        if(code ==1)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("RecordNoName"));
            alert.setHeaderText(rb.getString("RecordNoNameHeader"));
            alert.setContentText(rb.getString("RecordNoNamePrompt"));
            alert.showAndWait();
        }
        //Customer Address Error
        if(code ==2)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("RecordNoAddress"));
            alert.setHeaderText(rb.getString("RecordNoAddressHeader"));
            alert.setContentText(rb.getString("RecordNoAddressPrompt"));
            alert.showAndWait();
        }
        //Customer Postal Code Error
        if(code ==3)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("RecordNoPostal"));
            alert.setHeaderText(rb.getString("RecordNoPostalHeader"));
            alert.setContentText(rb.getString("RecordNoPostalPrompt"));
            alert.showAndWait();
        }

        //Customer Phone number error.
        if(code ==4)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("RecordNoPhoneNumber"));
            alert.setHeaderText(rb.getString("RecordNoPhoneNumberHeader"));
            alert.setContentText(rb.getString("RecordNoPhonePrompt"));
            alert.showAndWait();
        }
    }

    /**
     * Method used to highlight all error fields.
     * @param code Code to call the correct error message.
     * @param name Used to highlight the error on the name field.
     * @param address Used to highlight the error on the address field.
     * @param postalCode Used to highlight the error on the postal code field.
     * @param phoneNumber Used to highlight the error on the phone number field.
     */
    public static void addRecordFieldErrors(int code, TextField name, TextField address, TextField postalCode,
                                            TextField phoneNumber)
    {
        ResourceBundle rb;
        rb = ResourceBundle.getBundle("util/rb");
        recordAllFieldError(name, address, postalCode, phoneNumber);
        if (code == 1)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("RecordAllError"));
            alert.setHeaderText(rb.getString("RecordAllErrorHeader"));
            alert.setContentText(rb.getString("RecordAllErrorPrompt"));
            alert.showAndWait();
        }
    }


    /**
     * Called in other methods to highlight text fields in red.
     * @param field Used to highlight text field as red.
     */
    private static void fieldError(TextField field)
    {
        if(field !=null)
        {
            field.setStyle("-fx-border-color: red");
        }
    }

    /**
     * Method to highlight all text fields.
     * @param title Title text field passed through.
     * @param description Description text field passed through.
     * @param location Location text field passed through.
     * @param type Type field passed through.
     */
    private static void appointmentAllFieldError(TextField title, TextField description, TextField location,
                                                 TextField type)
    {
        if (title != null)
        {
            title.setStyle("-fx-border-color: red");
        }
        if (description != null)
        {
            description.setStyle("-fx-border-color: red");
        }
        if (location != null)
        {
            location.setStyle("-fx-border-color: red");
        }
        if (type != null)
        {
            type.setStyle("-fx-border-color: red");
        }
    }

    /**
     * Method to highlight call record text fields.
     * @param name Name text field passed through.
     * @param address Address text field passed through.
     * @param postalCode Postal code text field passed through.
     * @param phoneNumber Phone number text field passed through.
     */
    private static void recordAllFieldError(TextField name, TextField address, TextField postalCode,
                                                  TextField phoneNumber)
    {

        if (name != null)
        {
            name.setStyle("-fx-border-color: red");
        }
        if (name != null)
        {
            address.setStyle("-fx-border-color: red");
        }
        if (postalCode != null)
        {
            postalCode.setStyle("-fx-border-color: red");
        }
        if (phoneNumber != null)
        {
            phoneNumber.setStyle("-fx-border-color: red");
        }

    }

    /**
     * Method used to inform user of time selection errors.
     * @param code Error code selection
     */
    public static void timeAlerts (int code)
    {
        ResourceBundle rb;
        rb = ResourceBundle.getBundle("util/rb");
        if (code == 1)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("TimeAlert"));
            alert.setHeaderText(rb.getString("TimeAlertHeader"));
            alert.setContentText(rb.getString("TimeAlertPrompt"));
            alert.showAndWait();
        }
        if (code == 2)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("BusinessTime"));
            alert.setHeaderText(rb.getString("BusinessTimeHeader"));
            alert.setContentText(rb.getString("BusinessTimePrompt"));
            alert.showAndWait();

        }

    }

    /** Method to provide an error if an external file is not found.
     * @param code code provided to selected the correct error message.
     */
    public static void fileAlerts (int code)
    {
        ResourceBundle rb;
        rb = ResourceBundle.getBundle("util/rb");
        if(code ==1)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(rb.getString("FileNotFound"));
            alert.showAndWait();
        }

    }

    /** Method to inform the user that they are closing the application.
     * @param code Code used to selected the correct error message.
     */
    public static void generalAlert (int code)
    {
        ResourceBundle rb;
        rb = ResourceBundle.getBundle("util/rb");

        if(code ==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(rb.getString("CloseApplicationHeader"));
            alert.setTitle(rb.getString("CloseApplicationTitle"));
            alert.setContentText(rb.getString("CloseApplication"));
            alert.showAndWait();
        }

    }
}
