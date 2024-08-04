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

/** Add appointment class used for the user to add an appointment to the schedule
  <p><b>Lambda expression used in the back button call to speed up back button usage.</b></p>
   */
public class AddAppointmentController implements Initializable {


    @FXML
    private Label appointmentID;

    @FXML
    private Label title;


    @FXML
    private Label contact;

    @FXML
    private Label type;

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
    private TextField customerIDFld;

    @FXML
    private Label customerID;

    @FXML
    private Label addAppointmentsLbl;

    @FXML
    private ComboBox<contacts> contactCmbBx;

    @FXML
    private DatePicker datePcker;

    @FXML
    private ComboBox<String> hourComboBox;

    @FXML
    private ComboBox<customer> customerCmboBox;
    @FXML
    private ComboBox<String> minutesComboBox;

    @FXML
    private Button saveBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button setDateTime;

    @FXML
    private Button dateTest;



    @FXML
    private TextField startTime;

    @FXML
    private TextField endTime;

    @FXML
    private Label contactSelectionLbl;

    @FXML
    private Label customerSelectionLbl;

    @FXML
    private Label hourSelectionLbl;
    @FXML
    private Label minSelectionLbl;

    @FXML
    private Label dateSelectionLbl;


    @FXML
    private Label locationLbl;

    @FXML
    private Label descriptionLbl;





    Parent scene;
    Stage stage;

    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();



    private String strSelectedContact;
    private String contactEmail;
    private Integer contactID;

    private String selectedHour;
    private String selectedMin;
    private LocalDate selectedDate;


    private Integer selectedContactID = null;
    LocalDateTime ldtStart = null;
    LocalDateTime ldtEnd = null;
    String loggedInUser = null;
    Integer userID = null;


    DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("kk");
    DateTimeFormatter minFormat = DateTimeFormatter.ofPattern("mm");




    @FXML
    void onActionBackBtn(ActionEvent event) throws IOException {


        ResourceBundle rb  = ResourceBundle.getBundle("util/rb");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(rb.getString("FieldClearConfirmation"));
        alert.setTitle(rb.getString("FieldClearTitle"));
        alert.setHeaderText(rb.getString("FieldClearHeader"));

        //Lambda expression used to quickly provide information to the user and to go back to the main appointments
        // window.
        alert.showAndWait().ifPresent((backButtonResponse ->
        {
            try
            {
                if (backButtonResponse == ButtonType.OK) {
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setContentText(rb.getString("FieldClearMessage"));
                    infoAlert.setTitle(rb.getString("FieldClearTitle"));
                    infoAlert.setHeaderText(rb.getString("FieldClearHeader"));
                    infoAlert.showAndWait();

                    stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view_controllers/Appointments/Appointments.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                    System.out.println();

                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

        }));



    }

    @FXML
    void onActionContactComboBox(ActionEvent event) {
        contacts selectedContact = contactCmbBx.getValue();
        System.out.println(selectedContact);

        contactFld.setText(selectedContact.getContact_Name());

    }


    @FXML
    void onActionSaveBtn(ActionEvent event) throws IOException {


        String title = titleFld.getText();
        String description = descriptionFld.getText();
        String location = locationFld.getText();
        String contact = contactFld.getText();
        String type = typeFld.getText();
        String custID = customerIDFld.getText();
        String hour = hourComboBox.getValue();
        String min = minutesComboBox.getValue();
        LocalDate selectedDate = datePcker.getValue();
        Integer customerID = null;
        Timestamp startTime = null;
        Timestamp endTime = null;


        try
        {




            //Validation checks
            if(title.isEmpty() && description.isEmpty() && location.isEmpty() && contact.isEmpty() && type.isEmpty() &&
                    custID.isEmpty() && selectedDate == null && hour == null && min == null)
            {
                System.out.println("Everything is empty");
                AlertMessages.addAllAppointmentErrors(1,titleFld,descriptionFld,locationFld,typeFld);
                return;
            }

            if(title.isEmpty())
            {
                System.out.println("No Title");
                AlertMessages.appointmentErrors(1,titleFld);
                return;
            }
            if(description.isEmpty())
            {
                System.out.println("No Description");
                AlertMessages.appointmentErrors(2,descriptionFld);
                return;
            }
            if(location.isEmpty())
            {
                System.out.println("No location");
                AlertMessages.appointmentErrors(3,locationFld);
                return;
            }
            if(contact.isEmpty())
            {
                System.out.println("No Contact Selected");
                AlertMessages.appointmentErrors(8,contactFld);
                return;
            }
            if (type.isEmpty())
            {
                System.out.println("No Type Selected");
                AlertMessages.appointmentErrors(4,typeFld);
                return;
            }
            if (custID.isEmpty())
            {
                System.out.println("No Customer Selected");
                AlertMessages.appointmentErrors(5,customerIDFld);
                return;
            }

            if(selectedDate == null)
            {
                System.out.println("No Date");
                AlertMessages.appointmentErrors(9,null);
                return;
            }


            if (hour == null)
            {
                System.out.println("No Hour selected");
                AlertMessages.appointmentErrors(11,null);
                return;
            }
            if (min == null)
            {
                System.out.println("No min selected");
                AlertMessages.appointmentErrors(10,null);
                return;
            }





            contactID = contactCmbBx.getValue().getContact_id();

            ldtStart = null;
            ldtEnd = null;
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


            customerID = Integer.parseInt(custID);
            alertCheck.timeOverLapCheck(sTS,endTS,customerID,null,1);



              if(  alertCheck.timeOverLapCheck(sTS,endTS,customerID,null,1) == true)
           {
               System.out.println("Appointment conflict");
               AlertMessages.appointmentOverLapError(1);
               return;
           }

              else
                  {
                      if(  alertCheck.timeOverLapCheck(sTS,endTS,customerID,null,1) == false)
                      {
                          System.out.println("No Appointment conflict");

                      }

                  }







            if (alertCheck.businessHourCheck(sTime) == false)
           {

               AlertMessages.timeAlerts(2);
               return;
           }





        }
        catch (Exception e)
        {

            System.out.println(e);
            e.printStackTrace();
            AlertMessages.addAllAppointmentErrors(1,titleFld,descriptionFld,locationFld,typeFld);
            return;
        }

        defaultColorFields();




          appointmentsImpl.addAppointment(title,description,location,type,startTime,endTime,loggedInUser,
                loggedInUser, customerID,userID,contactID);

           stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view_controllers/Appointments/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();










        }




    @FXML
    void onActionSelectDate(ActionEvent event) {



        selectedDate = datePcker.getValue();

    }

    @FXML
    void onActionSelectHour(ActionEvent event) {

        selectedHour = hourComboBox.getValue();

    }

    @FXML
    void onActionSelectMinutes(ActionEvent event) {
        selectedMin = minutesComboBox.getValue();
        System.out.println(selectedMin);


    }

    /**
     * Method used to set the language of the text, sets data into combo boxes.
     * @param url Not used
     * @param rb Language resource bundle used to translate the text in the window.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<contacts> contactList = null;
        ObservableList<customer> customerList = null;


        rb = ResourceBundle.getBundle("util/rb");
        addAppointmentsLbl.setText(rb.getString("AddAppointmentMain"));
        appointmentIDFld.setPromptText(rb.getString("AddAppointmentIDPrompt"));
        appointmentID.setText(rb.getString("AddAppointmentID"));
        title.setText(rb.getString("AddAppointmentTitle"));
        descriptionLbl.setText(rb.getString("AddAppointmentDescription"));
        locationLbl.setText(rb.getString("AddAppointmentLocation"));
        contact.setText(rb.getString("AddAppointmentContact"));
        type.setText(rb.getString("AddAppointmentType"));
        customerID.setText(rb.getString("AddAppointmentCustomerID"));
        contactSelectionLbl.setText(rb.getString("AddAppointmentContactSelection"));
        contactCmbBx.setPromptText(rb.getString("AddAppointmentContactBox"));
        customerSelectionLbl.setText(rb.getString("AddAppointmentCustomerList"));
        customerCmboBox.setPromptText(rb.getString("AddAppointmentCustomerBox"));
        dateSelectionLbl.setText(rb.getString("AddAppointmentDateSelect"));
        hourSelectionLbl.setText(rb.getString("AddAppointmentHourSelection"));
        minSelectionLbl.setText(rb.getString("AddAppointmentMinSelect"));
        datePcker.setPromptText(rb.getString("AddAppointmentDatePicker"));
        hourComboBox.setPromptText(rb.getString("AddAppointmentSelectHours"));
        minutesComboBox.setPromptText(rb.getString("AddAppointmentSelectMin"));
        saveBtn.setText(rb.getString("AddAppointmentSave"));
        backBtn.setText(rb.getString("AddAppointmentBack"));
        contactFld.setPromptText(rb.getString("ContactPromptBox"));
        customerIDFld.setPromptText(rb.getString("CustomerIDPromptBox"));


       

        appointmentIDFld.setEditable(false);


        hours.addAll("00","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minutes.addAll("00","15","30","45");
        minutesComboBox.setItems(minutes);
        hourComboBox.setItems(hours);
        contactFld.setEditable(false);


        customerIDFld.setEditable(false);

        try {
            //Set contact Combo Box
            contactList = contactsImpl.getallContacts();
            contactCmbBx.setItems(contactList);

            //Set Customer Combo Box
            customerList = customersImpl.selectAllCustomers();
            customerCmboBox.setItems(customerList);
            String sUser = null;




            String fileName = "src/util/logs/userlogin";
            File file = new File(fileName);

            if(!file.exists())
            {
                System.out.println("file not found");
            }
            Scanner inputFile = new Scanner(file);

            while(inputFile.hasNext())
            {
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
    void onActionCustomerID(ActionEvent event) {
        Integer selectedCustomer = customerCmboBox.getValue().getCustomer_ID();
        customerIDFld.setText(selectedCustomer.toString());



    }

    /** Method used to reset fields when the back button is selected */
    public void resetFields() {
        titleFld.setText("");
        descriptionFld.setText("");
        locationFld.setText("");
        type.setText("");
    }

    /**
     * Method used to set fields to white after errors are cleared.
     */
    void defaultColorFields() {
        titleFld.setStyle("-fx-border-color: white");
        descriptionFld.setStyle("-fx-border-color: white");
        locationFld.setStyle("-fx-border-color: white");
        type.setStyle("-fx-border-color: white");

    }
}


