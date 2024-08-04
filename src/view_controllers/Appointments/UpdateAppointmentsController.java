package view_controllers.Appointments;

import DAO.appointmentsImpl;
import DAO.contactsImpl;
import DAO.customersImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.appointments;
import model.contacts;
import model.customer;
import model.user;
import util.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Method takes in selected appointment information and provides data for the appointment to be updated by the user.
 */
public class UpdateAppointmentsController implements Initializable {

    Parent scene;
    Stage stage;
    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
    DateTimeFormatter tf = DateTimeFormatter.ofPattern("kk:mm:ss");
    DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("kk");
    LocalDateTime startDateLTD;
    LocalDateTime endDateLDT;
    String loggedInUser = null;
    ObservableList<customer> customerList = FXCollections.observableArrayList();
    LocalDateTime ldtStart = null;
    LocalDateTime ldtEnd = null;
    Integer custID = null;
    @FXML
    private Label appointmentID;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label location;
    @FXML
    private Label contact;
    @FXML
    private Label type;
    @FXML
    private Label updateAppointmentMain;
    @FXML
    private Label startTimeLbl;
    @FXML
    private Label endTimeLbl;
    @FXML
    private Label customerIDLbl;
    @FXML
    private Label contactPrompt;
    @FXML
    private Label timeZoneDisplayPrompt;
    @FXML
    private Label customerPromptLbl;
    @FXML
    private TextField appointmentIDFld;
    @FXML
    private TextField titleFld;
    @FXML
    private TextField descriptionFld;
    @FXML
    private TextField locationFld;
    @FXML
    private TextField contactFld;
    @FXML
    private TextField typeFld;
    @FXML
    private TextField startDateTimeFld;
    @FXML
    private TextField endDateTimeFld;
    @FXML
    private TextField customerIDFld;
    @FXML
    private ComboBox<contacts> contactCmbBx;
    @FXML
    private DatePicker datePcker;
    @FXML
    private ComboBox<String> hourComboBox;
    @FXML
    private ComboBox<String> minutesComboBox;
    @FXML
    private ComboBox<customer> customerComboBox;
    @FXML
    private Button saveBtn;
    @FXML
    private Button backBtn;
    private String strSelectedContact;
    private String contactEmail;
    private Integer contactID;
    private LocalDate selectedDate;
    //  private String lastUpdateBy = null;
    private String selectedHour;
    private String selectedMin;
    private Integer customerNumb;
    private Integer selectedCustomerID;
    private Integer userID;
    private final Integer selectedContactID = null;

    /**
     * Sets all text to the correct language, sets list and combo boxes.
     * Lambda expression used in back button to speed up the reset of the fields.
     *
     * @param url Parameter not used.
     * @param rb  Language resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rb = ResourceBundle.getBundle("util/rb");

        updateAppointmentMain.setText(rb.getString("UpdateAppointmentMain"));
        timeZoneDisplayPrompt.setText(rb.getString("UpdateAppointmentTimeDisplay"));
        appointmentID.setText(rb.getString("UpdateAppointmentID"));
        title.setText(rb.getString("UpdateAppointmentTitle"));
        description.setText(rb.getString("UpdateAppointmentDescription"));
        location.setText(rb.getString("UpdateAppointmentLocation"));
        contact.setText(rb.getString("UpdateAppointmentContact"));
        type.setText(rb.getString("UpdateAppointmentType"));
        startTimeLbl.setText(rb.getString("UpdateAppointmentStartTime"));
        endDateTimeFld.setText(rb.getString("UpdateAppointmentEndTime"));
        customerIDLbl.setText(rb.getString("UpdateAppointmentCustomerID"));
        contactPrompt.setText(rb.getString("UpdateAppointmentContactPrompt"));
        contactCmbBx.setPromptText(rb.getString("UpdateAppointmentContactComboBox"));
        customerPromptLbl.setText(rb.getString("UpdateAppointmentCustomerPrompt"));
        customerComboBox.setPromptText(rb.getString("UpdateAppointmentCustomerComboBox"));
        datePcker.setPromptText(rb.getString("UpdateAppointmentDatePicker"));
        hourComboBox.setPromptText(rb.getString("UpdateAppointmentHourComboBox"));
        minutesComboBox.setPromptText(rb.getString("UpdateAppointmentMinComboBox"));
        saveBtn.setText(rb.getString("UpdateAppointmentSave"));
        backBtn.setText(rb.getString("UpdateAppointmentBack"));
        endTimeLbl.setText(rb.getString("UpdateAppointmentEndTime"));


        ObservableList<contacts> contactList = null;
        appointmentIDFld.setEditable(false);
        appointmentIDFld.setStyle("-fx-control-inner-background:gray");
        customerIDFld.setEditable(false);
        selectedDate = datePcker.getValue();
        startDateTimeFld.setEditable(false);
        endDateTimeFld.setEditable(false);


        hours.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
                "18",
                "19",
                "20",
                "21"
                , "22", "23");
        minutes.addAll("00", "15", "30", "45");
        minutesComboBox.setItems(minutes);
        hourComboBox.setItems(hours);


        try {
            contactList = contactsImpl.getallContacts();
            customerList = customersImpl.selectAllCustomers();
            contactCmbBx.setItems(contactList);
            customerComboBox.setItems(customerList);

            String sUser = null;

            String fileName = "src/util/logs/userlogin";
            File file = new File(fileName);

            if (!file.exists()) {
                System.out.println("file not found");
            }
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNext()) {
                sUser = inputFile.nextLine();
                System.out.println(sUser);
            }
            inputFile.close();

            user user = searches.searchByUserName(sUser).get(0);
            userID = user.getUserID();
            loggedInUser = user.getUserName();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void onActionSaveBtn(ActionEvent event) throws IOException {

        try {


            int selectedContactID = contactCmbBx.getValue().getContact_id();
            selectedDate = datePcker.getValue();

            String hour = selectedHour;
            String min = selectedMin;

            Integer appointmentID = Integer.valueOf(appointmentIDFld.getText());
            String title = titleFld.getText();
            String description = descriptionFld.getText();
            String location = locationFld.getText();

            String type = typeFld.getText();
            custID = Integer.parseInt(customerIDFld.getText());
            Integer contactID = contactCmbBx.getValue().getContact_id();

            Timestamp startTime = null;
            Timestamp endTime = null;

            LocalDateTime startDateTime;
            LocalDateTime endDateTime;


            if (title.isEmpty()) {
                AlertMessages.appointmentErrors(1, titleFld);
                return;
            }
            if (description.isEmpty()) {

                AlertMessages.appointmentErrors(2, descriptionFld);
                return;
            }
            if (location.isEmpty()) {
                AlertMessages.appointmentErrors(3, locationFld);
                return;
            }
            if (type.isEmpty()) {
                AlertMessages.appointmentErrors(4, typeFld);
                return;
            }
            if (contactFld == null) {
                AlertMessages.appointmentErrors(8, null);
                return;
            }

            if (hour.isEmpty()) {
                AlertMessages.appointmentErrors(11, null);

                return;
            }
            if (min.isEmpty()) {
                AlertMessages.appointmentErrors(10, null);

                return;
            }
            if (selectedDate == null) {

                System.out.println("Date picker error");
                AlertMessages.appointmentErrors(9, null);
                return;
            }


            defaultColorFields();
            ldtStart = LocalDateTime.of(selectedDate.getYear(), selectedDate.getMonthValue(),
                    selectedDate.getDayOfMonth(), Integer.parseInt(selectedHour), Integer.parseInt(selectedMin));
            ldtEnd = ldtStart.plusHours(1);


            startTime = timeDateParse.getTimeStamp(timeConvert.convertToUTC(ldtStart));
            endTime = timeDateParse.getTimeStamp(timeConvert.convertToUTC(ldtEnd));

            LocalDateTime sTS = startTime.toLocalDateTime();
            LocalDateTime endTS = endTime.toLocalDateTime();

            String sLDTFormat = timeDateParse.ldtFormat(ldtStart);
            String endLDTFormat = timeDateParse.ldtFormat(ldtEnd);

            LocalTime sTime = timeDateParse.getTime(sLDTFormat);
            LocalTime eTime = timeDateParse.getTime(endLDTFormat);


            if (alertCheck.businessHourCheck(sTime) == false) {
                AlertMessages.timeAlerts(1);
                return;
            }


            if (alertCheck.timeOverLapCheck(sTS, endTS, custID, appointmentID, 2) == true) {
                AlertMessages.appointmentOverLapError(1);
                return;

            } else {
                if (alertCheck.timeOverLapCheck(sTS, endTS, custID, appointmentID, 2) == false) {
                    //No conflict detected, appointment to be updated. Nothing to do.

                }
            }


            appointmentsImpl.updateAppointment(title, description, location, type, startTime, endTime, loggedInUser, custID,
                    userID, contactID, appointmentID);
        } catch (Exception e) {
            e.printStackTrace();
        }


        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view_controllers/Appointments/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }


    @FXML
    void onActionBackBtn(ActionEvent event) throws IOException {

        ResourceBundle rb = ResourceBundle.getBundle("util/rb");


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(rb.getString("BackBtnHeader"));
        alert.setTitle(rb.getString("BackBtnTitle"));
        alert.setContentText(rb.getString("BackBtnConfirmation"));


        //Lambda expression used to speed up back button

        alert.showAndWait().ifPresent((response -> {
            if (response == ButtonType.OK) {

                Parent main = null;
                try {
                    resetFields();
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view_controllers/Appointments/Appointments.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }));


    }

    @FXML
    void onActionContactComboBox(ActionEvent event) throws Exception {
        strSelectedContact = contactCmbBx.getValue().getContact_Name();
        contactEmail = contactCmbBx.getValue().getEmail();
        contactID = contactCmbBx.getValue().getContact_id();

        contactFld.setText(strSelectedContact);
        System.out.println("Contact ID" + contactID);

    }


    @FXML
    void onActionSelectDate(ActionEvent event) {

        // System.out.println("Selected Date " + selectedDate);
        selectedDate = datePcker.getValue();

    }


    @FXML
    void onActionSelectHour(ActionEvent event) {

        selectedHour = hourComboBox.getValue();
    }

    @FXML
    void onActionSelectMinutes(ActionEvent event) {
        selectedMin = minutesComboBox.getValue();

    }


    /**
     * Method used to send all appointment information to the corresponding fields from the selected appointment.
     *
     * @param appointment Parameter used to get information from the selected appointment to send over to fields in
     *                    this window.
     */
    public void sendAppointment(appointments appointment) {
        customer returnedCustomer = null;
        contacts returnedContact = null;


        try {


            String formattedStartLDT = null;
            String formattedEndLDT = null;

            Integer selectedContact = appointment.getContactID();


            startDateLTD = appointment.getStartDate();
            endDateLDT = appointment.getEndDate();


            formattedStartLDT = timeDateParse.ldtFormat(startDateLTD);
            formattedEndLDT = timeDateParse.ldtFormat(endDateLDT);


            LocalDateTime startLDT = timeDateParse.stringToLD(formattedStartLDT);

            LocalDateTime endLDT = timeDateParse.stringToLD(formattedEndLDT);


            LocalDateTime convertedStartLDT = timeConvert.estToLocal(startLDT);
            LocalDateTime convertedEndLDT = timeConvert.estToLocal(endLDT);

            appointment.setStartDate(convertedStartLDT);
            appointment.setEndDate(convertedEndLDT);


            selectedCustomerID = appointment.getCustomerID();
            returnedCustomer = searches.getCustomer(selectedCustomerID);

            returnedContact = searches.getContact(selectedContact);

            customerComboBox.setValue(returnedCustomer);
            contactCmbBx.setValue(returnedContact);

            selectedHour = String.valueOf(appointment.getStartDate().getHour());
            selectedMin = String.valueOf(appointment.getStartDate().getMinute());
            selectedDate = LocalDate.from(appointment.getStartDate());
            appointmentIDFld.setText(String.valueOf(appointment.getAppointment_ID()));
            titleFld.setText(String.valueOf(appointment.getTitle()));
            descriptionFld.setText(String.valueOf(appointment.getDescription()));
            locationFld.setText(String.valueOf(appointment.getLocation()));
            contactFld.setText(String.valueOf(appointment.getContactID()));
            typeFld.setText(String.valueOf(appointment.getType()));
            startDateTimeFld.setText(String.valueOf(appointment.getStartDate()));
            endDateTimeFld.setText(String.valueOf(appointment.getEndDate()));
            customerIDFld.setText(String.valueOf(appointment.getCustomerID()));
            datePcker.setValue(selectedDate);
            hourComboBox.setValue(selectedHour);
            minutesComboBox.setValue(selectedMin);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void onActionsSelectCustomer(ActionEvent event) {


        customerNumb = customerComboBox.getValue().getCustomer_ID();
        customerIDFld.setText(String.valueOf(customerNumb));


    }

    /**
     * Used to remove text from the fields.
     */
    public void resetFields() {
        titleFld.setText("");
        descriptionFld.setText("");
        locationFld.setText("");
        type.setText("");
    }

    /**
     * Method to remove red color from the fields.
     */
    void defaultColorFields() {
        titleFld.setStyle("-fx-border-color: white");
        descriptionFld.setStyle("-fx-border-color: white");
        locationFld.setStyle("-fx-border-color: white");
        type.setStyle("-fx-border-color: white");

    }
}