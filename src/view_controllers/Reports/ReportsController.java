package view_controllers.Reports;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.contacts;
import model.customer;
import DAO.*;
import model.*;
import util.searches;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class shows appointments by month / type and groups them together and shows a total number of that type of
 * appointment.
 * <p>Class also shows appointments by customer and by contact in the lower table.</p>
 */
public class ReportsController implements Initializable {




    @FXML
    private TableColumn<?, ?> appointmentType;

    @FXML
    private TableColumn<?, ?> appointmentTotal;



    @FXML
    private TableColumn<?, ?> monthName;

    @FXML
    private TableColumn<?, ?> monthTotal;

    @FXML
    private TableView<appointments> scheduleTable;

    @FXML
    private TableColumn<?, ?> appointmentID;

    @FXML
    private TableColumn<?, ?> title;

    @FXML
    private TableColumn<?, ?> type;

    @FXML
    private TableColumn<?, ?> description;

    @FXML
    private TableColumn<?, ?> start;

    @FXML
    private TableColumn<?, ?> end;

    @FXML
    private TableColumn<?, ?> customerID;
    @FXML
    private ComboBox<contacts> contactComboBox;

    @FXML
    private ComboBox<customer> customerComboBox;
    @FXML
    private Button backBtn;

    @FXML
    private Button clearTable;

    @FXML
    private Label reportsTitleLbl;

    @FXML
    private Label reportMonthlbl;

    @FXML
    private Label reportTypeLbl;

    @FXML
    private Label scheduleLbl;

    @FXML
    private Label scheduleDirectionsLbl;


    @FXML
    private TableView<month_Type> monthTypeTable;

    @FXML
    private TableColumn<?, ?> monthCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private TableColumn<?, ?> countCol;



    ObservableList<contacts>contactList;
    ObservableList<customer> customerList;


    ObservableList<appointments> contactAppointmentList = FXCollections.observableArrayList();
    ObservableList<appointments>customerReportList = FXCollections.observableArrayList();
    ObservableList<month_Type>monthTypeTableList = FXCollections.observableArrayList();



    Parent scene;
    Stage stage;



    /**
     * Method used to set the text to English or French
     * @param url Not used
     * @param rb Resource Bundle to set the language to French or English.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        boolean typeListFull = true;

        try {


            rb = ResourceBundle.getBundle("util/rb");
            reportsTitleLbl.setText(rb.getString("ReportsLabel"));
            reportTypeLbl.setText(rb.getString("ReportsByType"));
            monthCol.setText(rb.getString("ReportsMonthCol"));
            typeCol.setText(rb.getString("ReportsTypeCol"));
            countCol.setText(rb.getString("ReportsCountCol"));

            scheduleLbl.setText(rb.getString("ScheduleView"));
            appointmentID.setText(rb.getString("ScheduleID"));
            title.setText(rb.getString("ScheduleTitle"));
            type.setText(rb.getString("ScheduleType"));
            description.setText(rb.getString("ScheduleDescription"));
            start.setText(rb.getString("ScheduleStartTime"));
            end.setText(rb.getString("ScheduleEndTime"));
            customerID.setText(rb.getString("ScheduleCustomerID"));
            scheduleDirectionsLbl.setText(rb.getString("ScheduleDirections"));
            contactComboBox.setPromptText(rb.getString("ReportContactComboBox"));
            customerComboBox.setPromptText(rb.getString("ReportCustomerComboBox"));
            backBtn.setText(rb.getString("ReportsBackButton"));




            //Set Contact Combobox
            contactList = contactsImpl.getallContacts();
            contactComboBox.setItems(contactList);

            //Set Customer Combobox
            customerList = customersImpl.selectAllCustomers();
            customerComboBox.setItems(customerList);

            //Set Schedule table

            appointmentID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
            title.setCellValueFactory(new PropertyValueFactory<>("title"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            description.setCellValueFactory(new PropertyValueFactory<>("Description"));
            start.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            end.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));






                //Set Month Type Table
                monthTypeTableList.addAll(appointmentsImpl.monthTypeAppointmentsQuery());
                monthTypeTable.setItems(monthTypeTableList);
                monthCol.setCellValueFactory(new PropertyValueFactory<>("Month"));
                typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
                countCol.setCellValueFactory(new PropertyValueFactory<>("Count"));





        } catch (Exception e) {
            e.printStackTrace();
        }


    }




    @FXML
    void onActionBackBtn(ActionEvent event) throws IOException {

       // clearMonthTypeTables();

          stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view_controllers/MainMenu/Menu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();



    }

    @FXML
    void onActionSelectContact(ActionEvent event) throws Exception {

        ResourceBundle rb;
        rb = ResourceBundle.getBundle("util/rb");

        Integer selectedContact = contactComboBox.getSelectionModel().getSelectedItem().getContact_id();
          contactAppointmentList= searches.searchByContactID(selectedContact);
        scheduleTable.getItems().removeAll(contactAppointmentList);
        scheduleTable.getItems().removeAll(customerReportList);
         scheduleTable.setItems(contactAppointmentList);
         scheduleLbl.setText(rb.getString("ScheduleViewContact"));



       }



    @FXML
    void onActionSelectCustomer(ActionEvent event) throws SQLException {
        ResourceBundle rb;
        rb = ResourceBundle.getBundle("util/rb");



        Integer selectedCustomer = customerComboBox.getSelectionModel().getSelectedItem().getCustomer_ID();


        customerReportList = searches.searchByCustomerID(selectedCustomer);
        scheduleTable.getItems().removeAll(contactAppointmentList);
        scheduleTable.getItems().removeAll(customerReportList);
        scheduleLbl.setText(rb.getString("ScheduleViewCustomer"));
        scheduleTable.setItems(customerReportList);





    }

















}
