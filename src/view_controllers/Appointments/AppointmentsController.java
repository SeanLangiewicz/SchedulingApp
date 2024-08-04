package view_controllers.Appointments;

import DAO.appointmentsImpl;
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
import model.appointments;
import util.AlertMessages;
import util.timeConvert;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Method shows all current appointments.
 */
public class AppointmentsController implements Initializable {


    Parent scene;
    Stage stage;
    ObservableList<appointments> appointmentList = FXCollections.observableArrayList();
    ObservableList<appointments> sevenDayAppointmentList = FXCollections.observableArrayList();
    ObservableList<appointments> monthAppointmentList = FXCollections.observableArrayList();
    @FXML
    private TableView<appointments> appointmentTblView;
    @FXML
    private TableColumn<?, ?> appointment;
    @FXML
    private TableColumn<?, ?> title;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> location;
    @FXML
    private TableColumn<?, ?> contact;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> startDate;
    @FXML
    private TableColumn<?, ?> endDate;
    @FXML
    private TableColumn<?, ?> customerID;
    @FXML
    private Label scheduledAppointmentsTitle;
    @FXML
    private Label viewLabel;
    @FXML
    private Label timeZoneLbl;
    @FXML
    private Button addAppointment;
    @FXML
    private Button updateAppointment;
    @FXML
    private Button deleteAppointment;
    @FXML
    private Button backBtn;
    @FXML
    private RadioButton allRadioBtn;
    @FXML
    private ToggleGroup calendarOptions;
    @FXML
    private RadioButton monthRadioBtn;
    @FXML
    private RadioButton weekRadioBtn;
    @FXML
    private Label calendarOptionsLbl;
    @FXML
    private Label calEditWarning;

    /**
     * Method used to set text language and to show the current list of appointments.
     *
     * @param url Not used.
     * @param rb  Language resource bundle.
     */
    public void initialize(URL url, ResourceBundle rb) {

        try {

            rb = ResourceBundle.getBundle("util/rb");

            scheduledAppointmentsTitle.setText(rb.getString("ScheduledAppointments"));
            viewLabel.setText(rb.getString("AppointmentsView"));
            timeZoneLbl.setText(rb.getString("AppointmentsTime"));
            appointment.setText(rb.getString("AppointmentsAppointmentID"));
            title.setText(rb.getString("AppointmentsTitle"));
            description.setText(rb.getString("AppointmentsDescription"));
            location.setText(rb.getString("AppointmentsLocation"));
            type.setText(rb.getString("AppointmentsType"));
            contact.setText(rb.getString("AppointmentsContact"));
            startDate.setText(rb.getString("AppointmentsStartTime"));
            endDate.setText(rb.getString("AppointmentsEndTime"));
            customerID.setText(rb.getString("AppointmentsCustomerID"));
            calendarOptionsLbl.setText(rb.getString("AppointmentsCalendarOptions"));
            allRadioBtn.setText(rb.getString("AppointmentsCalendarOptionsAll"));
            monthRadioBtn.setText(rb.getString("AppointmentsCalendarOptionsMonth"));
            weekRadioBtn.setText(rb.getString("AppointmentsCalendarOptionsWeek"));
            addAppointment.setText(rb.getString("AppointmentsAddAppointment"));
            updateAppointment.setText(rb.getString("AppointmentsUpdateAppointment"));
            deleteAppointment.setText(rb.getString("AppointmentsDeleteAppointment"));
            backBtn.setText(rb.getString("AppointmentsBack"));
            calEditWarning.setText(rb.getString("CalEditWarning"));


            allRadioBtn.setSelected(true);

            //Local Date Time to hold date time object for converting and setting converted time.
            LocalDateTime newStartLDT = null;
            LocalDateTime newEndLDT = null;


            appointmentList.addAll(appointmentsImpl.getAllAppointments());

            for (int i = 0; i < appointmentList.size(); i++) {

                appointments apt = appointmentList.get(i);
                LocalDateTime startLDT = apt.getStartDate();
                LocalDateTime endLDT = apt.getEndDate();


                newStartLDT = timeConvert.convertToEST(startLDT);
                newEndLDT = timeConvert.convertToEST(endLDT);


                apt.setStartDate(newStartLDT);
                apt.setEndDate(newEndLDT);


            }
            appointmentTblView.setItems(appointmentList);


            appointment.setCellValueFactory(new PropertyValueFactory<>("appointment_ID"));
            title.setCellValueFactory(new PropertyValueFactory<>("title"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            location.setCellValueFactory(new PropertyValueFactory<>("location"));
            contact.setCellValueFactory(new PropertyValueFactory<>("contactID"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));

            endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view_controllers/Appointments/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionBackButton(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view_controllers/MainMenu/Menu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) throws SQLException {

        try {
            ResourceBundle rb = ResourceBundle.getBundle("util/rb");

            String appointmentType = appointmentTblView.getSelectionModel().getSelectedItem().getType();
            int appointmentID = appointmentTblView.getSelectionModel().getSelectedItem().getAppointment_ID();

            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle(rb.getString("DeleteAppointmentTitle"));
            deleteAlert.setHeaderText(rb.getString("DeleteAppointmentHeader"));
            deleteAlert.setContentText(rb.getString("DeleteAppointmentPrompt"));
            Optional<ButtonType> result = deleteAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                appointmentsImpl.deleteAppointmentByAppointmentID(appointmentID);
                AlertMessages.appointmentDeleteMessage(1, appointmentID, appointmentType);

                System.out.println("OK Selected");

            } else {
                System.out.println("Cancel");
                return;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        appointmentList = appointmentsImpl.getAllAppointments();
        appointmentTblView.setItems(appointmentList);

        appointmentTblView.refresh();

    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event) throws IOException {

        try {

            appointments selectedAppointment = appointmentTblView.getSelectionModel().getSelectedItem();
            if (selectedAppointment == null) {
                System.out.println("no appointment selected");
                AlertMessages.appointmentSelectionErrors(1, null);
                return;
            }


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view_controllers/Appointments/UpdateAppointments.fxml"));
            loader.load();

            UpdateAppointmentsController updateController = loader.getController();
            updateController.sendAppointment(appointmentTblView.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }

    }


    @FXML
    void onActonAllRadioBtn(ActionEvent event) throws SQLException {
        clearTable();

        appointmentList = appointmentsImpl.getAllAppointments();

        appointmentTblView.setItems(appointmentList);

        monthAppointmentList.removeAll();

        deleteAppointment.setDisable(false);
        updateAppointment.setDisable(false);
        addAppointment.setDisable(false);

    }

    //Clears table and list content then displays appointments for the current month.
    @FXML
    void onActonMonthRadio(ActionEvent event) throws SQLException {
        clearTable();
        monthAppointmentList = appointmentsImpl.monthAppointments();
        appointmentTblView.setItems(monthAppointmentList);
        deleteAppointment.setDisable(true);
        updateAppointment.setDisable(true);
        addAppointment.setDisable(true);

    }

    //Clears table and list content then displays appointment for 1 week.
    @FXML
    void onActonWeekRadioBtn(ActionEvent event) throws SQLException {
        clearTable();
        sevenDayAppointmentList = appointmentsImpl.sevenDayAppointments();
        appointmentTblView.setItems(sevenDayAppointmentList);
        deleteAppointment.setDisable(true);
        updateAppointment.setDisable(true);
        addAppointment.setDisable(true);

    }

    /**
     * Method to clear table content within the month, week and all display options
     */
    private void clearTable() {
        for (int i = 0; i < appointmentTblView.getItems().size(); i++) {

            appointmentTblView.getItems().removeAll(appointmentList);
            appointmentTblView.getItems().removeAll(monthAppointmentList);
            appointmentTblView.getItems().removeAll(sevenDayAppointmentList);


        }

    }

}
