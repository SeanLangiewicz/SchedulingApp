package view_controllers.LoginScreen;


import DAO.appointmentsImpl;
import DAO.userImpl;
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
import model.user;
import util.*;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Login screen controller checks user credentials and provides alert for incoming appointment.
 * <p> Login Screen controller handles checking user credentials to enter into the program and checks for any
 * upcoming appointments for the user logging in.</p>
 * <p><b>Lambda expression used in login button call to export the user login activity to the activity log file.</b></p>
 */
public class LogInScreenController implements Initializable {

    /**
     * Observable list used to keep track of the logged in user.
     */
    public static ObservableList<user> signedInUser = FXCollections.observableArrayList();

    Stage stage;
    Parent scene;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private TextField logInTextField;
    @FXML
    private Button logInBtn;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button cancelBtn;

    @FXML
    private Label userLocation;
    @FXML
    private Label zoneIDLabel;

    @FXML
    private Label applicationNameLabel;

    @FXML
    private Label userNameLabel;
    @FXML
    private Label passwordLabel;


    //Cancel Function. Closes the program for the user.
    @FXML
    void onActionCancel(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will close the program, do you want to continue ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {


            System.exit(0);


        }
    }

    //Login function. Checks user input and allows user to login and provides login error message.
    @FXML
    void onActionLogin(ActionEvent event) throws Exception {
        //Lambda expression declaration 1
        StringInterface logMessage = s -> s;
        ObservableList<appointments> appointmentsList = FXCollections.observableArrayList();


        boolean loginSuccessful = false;
        String loginStatusString = null;
        String fileName = "src/util/logs/userlogin";
        String userActivityLog = "src/login_activity.txt";

        FileWriter loginActivityFW = new FileWriter(userActivityLog, true);
        PrintWriter loginActivityPW = new PrintWriter(loginActivityFW);

        File file = new File(fileName);


        LocalDate currentDate = LocalDate.now();

        LocalTime currentTime = LocalTime.now().withNano(0);


        //Login activity log
        Integer userID = null;
        String username = logInTextField.getText();
        String password = passwordTextField.getText();

        try {


            FileWriter fw = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(fw);

            pw.print(username);
            pw.close();


        } catch (FileNotFoundException ex) {
            Logger.getLogger(LogInScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }


        //Checks user login credentials
        if (userImpl.userSigninCheck(username, password) == true) {

            //Logs user login activity
            loginSuccessful = true;
            loginStatusString = " Was able to login ";
            signedInUser = userImpl.signedInUserList;

            user uID = signedInUser.get(0);
            Integer id = uID.getUserID();
            String name = uID.getUserName();

            String log = "The user " + " " + username + loginStatusString + " at  " + currentTime + " on the" +
                    " " +
                    "date " +
                    " of " + currentDate + "." + "\n";

            logMessage.output(log);

            loginActivityPW.print(log);
            loginActivityPW.close();

            //list of all appointments for the user by user ID
            appointmentsList = appointmentsImpl.appointmentsByID(id);

            int appointmentIndex = 0;
            boolean upComingAppointment = false;
            int apptID = 0;
            LocalTime time = null;
            LocalDate date = null;

            for (int i = 0; i < appointmentsList.size(); i++) {
                appointments selectedAppointment = appointmentsList.get(appointmentIndex);
                LocalDateTime ldt = selectedAppointment.getStartDate();
                apptID = selectedAppointment.getAppointment_ID();

                LocalDateTime convertedLDT = timeConvert.convertToLocal(ldt);
                String sTime = timeDateParse.ldtFormat(convertedLDT);

                date = timeDateParse.getDate(sTime);
                time = timeDateParse.getTime(sTime);

                DateTimeFormatter tFormat = DateTimeFormatter.ofPattern("HH:mm");


                //Checks if date from appointment matches today's date.
                if (alertCheck.dayCheck(date) == true)
                {


                    //Provides data to the method that checks for if the time is within 15min of the user login time.
                     if (alertCheck.intervalAlert(time) == true)
                    {

                        upComingAppointment = true;
                        AlertMessages.appointmentAlert(1, apptID, date, time);


                    }




                }



                appointmentIndex++;
            }


            if (upComingAppointment == false) {
                AlertMessages.appointmentAlert(2, null, null, null);

            }



              stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view_controllers/MainMenu/Menu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();






        }
        //If username or password are invalid, logs messages to the log file and provides appropriate message.
        if (userImpl.userSigninCheck(username, password) == false) {
            AlertMessages.loginErrors(1, null);

            String failedloginStatusString = " Was not able to login ";
            String log = "The user " + " " + username + failedloginStatusString + " at  " + currentTime + " on the" +
                    " " +
                    "date " +
                    " of " + currentDate + "." + "\n";

            logMessage.output(log);

            loginActivityPW.print(log);
            loginActivityPW.close();

            return;
        }

    }


    /**
     * Method used to set language for text and shows the users Zone ID.
     *
     * @param url Not used
     * @param rb  Resource bundle to select the correct language for the text.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ZoneId zID = ZoneId.systemDefault();
        String zoneID = String.valueOf(zID);
        userLocation.setText(zoneID);


        rb = ResourceBundle.getBundle("util/rb");

        logInBtn.setText(rb.getString("login"));
        cancelBtn.setText(rb.getString("cancel"));
        logInTextField.setPromptText(rb.getString("loginPromptText"));
        passwordTextField.setPromptText(rb.getString("passwordPromptText"));
        zoneIDLabel.setText(rb.getString("ZoneID"));
        applicationNameLabel.setText(rb.getString("ApplicationName"));
        userNameLabel.setText(rb.getString("UserNameLabel"));
        passwordLabel.setText(rb.getString("PasswordLabel"));

    }



}
