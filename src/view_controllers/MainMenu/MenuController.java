package view_controllers.MainMenu;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.AlertMessages;


import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

/**
 * Main menu controller that provides an interface for the user to select where they would like to go in the program.
 */
public class MenuController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button recordsBtn;

    @FXML
    private Button appointmentsBtn;

    @FXML
    private Button closeBtn;


    @FXML
    private Button reportsBtn;

    @FXML
    private Label menulabel;


    /**
     * Shows a main menu for the user in French of English.
     * @param url Default parameter for initialize method, not in use.
     * @param rb Language resource bundle for translating button text.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        rb = ResourceBundle.getBundle("util/rb");

        recordsBtn.setText(rb.getString("Records"));
        reportsBtn.setText(rb.getString("Reports"));
        appointmentsBtn.setText(rb.getString("Appointments"));
        closeBtn.setText(rb.getString("Close"));
        menulabel.setText(rb.getString("Menu"));




    }

    @FXML
    void onActionCloseBtn(ActionEvent event) {


        AlertMessages.generalAlert(1);
            System.exit(0);



    }

    @FXML
    void onActionRecordsBtn(ActionEvent event) throws IOException{



        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view_controllers/CustomerRecords/CustomerRecords.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionAppointments(ActionEvent event) throws IOException{


        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view_controllers/Appointments/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    @FXML
    void onActionReports(ActionEvent event) throws IOException{

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view_controllers/Reports/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }



}
