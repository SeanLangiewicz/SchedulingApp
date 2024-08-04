package view_controllers.CustomerRecords;

import DAO.countries_Impl;
import DAO.customersImpl;
import DAO.first_level_Divisons_Imp;
import DAO.userImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.countries;
import model.first_level_divisions;
import model.user;
import util.AlertMessages;
import util.searches;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.Scanner;


/**
 * Class contains methods so the user can add a new record.
 */
public class AddCustomerRecordsController implements Initializable {

    @FXML
    private Label IDLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label postalCodeLbl;

    @FXML
    private Label phoneNumberLbl;

    @FXML
    private TextField custIDFld;

    @FXML
    private TextField customerNameTxtFld;

    @FXML
    private TextField addressTxtFld;

    @FXML
    private TextField postalCodeTxtFld;

    @FXML
    private TextField phoneNumberTxtFld;

    @FXML
    private Button saveBtn;

    @FXML
    private Button backBtn;


    @FXML
    private ComboBox<first_level_divisions> divisionID;

    @FXML
    private ComboBox<countries> countryID;

    @FXML
    private Label addRecordTitlelbl;


    ObservableList<first_level_divisions> divisionIDList = FXCollections.observableArrayList();

    private String divisionName = null;
    private countries selectedCountry = null;
    private LocalTime selectedTime = null;
    private user loggedinUser = null;
    private Integer selectedDivisionID =null;
    Integer userID;
    String loggedInUser = null;

    /**
     * Method used to set the text, combo boxes and lists.
     * @param url Not used
     * @param rb Resource bundle used to translate the text to French or English
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rb = ResourceBundle.getBundle("util/rb");
        addRecordTitlelbl.setText(rb.getString("AddRecordTitle"));
        IDLabel.setText(rb.getString("AddRecordCustomerID"));
        nameLabel.setText(rb.getString("AddRecordCustomerName"));
        addressLabel.setText(rb.getString("AddRecordAddress"));
        postalCodeLbl.setText(rb.getString("AddRecordPostalCode"));
        phoneNumberLbl.setText(rb.getString("AddRecordPhoneNumber"));
        countryID.setPromptText(rb.getString("SelectCountry"));
        divisionID.setPromptText(rb.getString("SelectDivision"));
        saveBtn.setText(rb.getString("AddRecordSaveButton"));
        backBtn.setText(rb.getString("AddRecordBackButton"));
        customerNameTxtFld.setPromptText(rb.getString("AddRecordNamePrompt"));
        addressTxtFld.setPromptText(rb.getString("AddRecordAddressPrompt"));
        postalCodeTxtFld.setPromptText(rb.getString("AddRecordPostalPrompt"));
        phoneNumberTxtFld.setPromptText(rb.getString("AddRecordPhonePrompt"));
        custIDFld.setText(rb.getString("AddRecordIDField"));
try {

            ObservableList<user> userList = userImpl.getAllUsers();

            divisionID.setDisable(true);


            //Setting Combo Boxes
            ObservableList<countries> countryList = countries_Impl.getAllCountries();
            countryID.setItems(countryList);
            countryID.setPromptText(rb.getString("RecordCountrySelection"));

            divisionID.setPromptText(rb.getString("RecordSelectCountryFirst"));

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

        custIDFld.setEditable(false);
        custIDFld.setStyle("-fx-control-inner-background:gray");




    }


    @FXML
    void selectCountryID(ActionEvent event) throws Exception {



        selectedCountry = countryID.getValue();
        int selectedCountryID = countryID.getValue().getCountry_id();

        divisionID.setDisable(false);

        divisionIDList = first_level_Divisons_Imp.getAllDivisionsByCountryID(selectedCountryID);

        divisionID.setItems(divisionIDList);

        System.out.println(selectedCountry);




        divisionID.getSelectionModel().clearSelection();


    }





    @FXML
    private void onActionDivison(ActionEvent event) {

        divisionID.setItems(divisionIDList);
        selectedDivisionID = divisionID.getValue().getDivision_ID();
        divisionName = divisionID.getValue().getDivision();



    }

    @FXML
    void onActionBack(ActionEvent event) throws IOException {
        Parent scene;
        Stage stage;

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view_controllers/CustomerRecords/CustomerRecords.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }




    @FXML
    void onActionSave(ActionEvent event) throws IOException, SQLException {



        Parent scene;
        Stage stage;

        String name = customerNameTxtFld.getText();
        String address = addressTxtFld.getText();
        String postalCode = postalCodeTxtFld.getText();
        String phoneNumber = phoneNumberTxtFld.getText();
        countries countryCheck = countryID.getValue();

        try {

            if(name.isEmpty() && address.isEmpty() && postalCode.isEmpty() && phoneNumber.isEmpty() && countryID.getSelectionModel().isEmpty()
                && divisionID.getSelectionModel().isEmpty())
            {
                AlertMessages.addRecordFieldErrors(1,customerNameTxtFld,addressTxtFld,postalCodeTxtFld,phoneNumberTxtFld);
                return;
            }

            if(name.isEmpty())
            {
                //Throw name error
                AlertMessages.addRecordErrors(1,customerNameTxtFld);
                return;
            }
            if(address.isEmpty())
            {
                //Throw address error
                AlertMessages.addRecordErrors(2,addressTxtFld);
                return;
            }
            if(postalCode.isEmpty())
            {
                //Throw postal code error
                AlertMessages.addRecordErrors(3,postalCodeTxtFld);
                return;
            }
            if (phoneNumber.isEmpty())
            {
                //throw phone error
                AlertMessages.addRecordErrors(4,phoneNumberTxtFld);
                return;

            }
            if(countryID.getSelectionModel().isEmpty())
            {
                //Throw country ID error
                AlertMessages.addRecordErrors(5,null);
                return;
            }
            if(divisionID.getSelectionModel().isEmpty())
            {
                //Throw div id empty error
                AlertMessages.addRecordErrors(6,null);
                return;
            }



        } catch (Exception e)
        {
            System.out.println(e);


            return;
        }

        defaultColorFields();
        resetFields();
        customersImpl.addCustomerRecord(name,address,postalCode,phoneNumber,loggedInUser,loggedInUser,selectedDivisionID);
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view_controllers/CustomerRecords/CustomerRecords.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }


    /**
     * Method used to remove text from the fields.
     */
    public void resetFields() {
        customerNameTxtFld.setText("");
        addressTxtFld.setText("");
        postalCodeTxtFld.setText("");
        phoneNumberTxtFld.setText("");
         }

    /**
     * Method used to remove red from the text fields.
     */
    void defaultColorFields() {
        customerNameTxtFld.setStyle("-fx-border-color: white");
        addressTxtFld.setStyle("-fx-border-color: white");
        postalCodeTxtFld.setStyle("-fx-border-color: white");
        phoneNumberTxtFld.setStyle("-fx-border-color: white");

    }






}
