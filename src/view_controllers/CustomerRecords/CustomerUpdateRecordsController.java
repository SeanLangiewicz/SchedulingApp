package view_controllers.CustomerRecords;

import DAO.countries_Impl;
import DAO.first_level_Divisons_Imp;
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
import model.customer;
import model.countries;
import model.first_level_divisions;
import DAO.customersImpl;
import model.user;
import util.AlertMessages;
import util.searches;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Class is takes selected record and displays it in the provided fields were the user can make updates if needed.
 */
public class CustomerUpdateRecordsController implements Initializable {

    @FXML
    private Label IDLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label addressLbl;

    @FXML
    private Label postalCodelbl;

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
    private ComboBox<first_level_divisions> divisionIDComboBox;

    @FXML
    private ComboBox<countries> countryIDComboBox;


    /**
     * Start of the adds record controller 123!@#123123123
     */


    ObservableList<first_level_divisions> divisionIDList;
    ObservableList<first_level_divisions>divisonNames;
    ObservableList<first_level_divisions>divisionList;
    private int selectedDivisionID;
    private String divisionName = null;
    private countries selectedCountry = null;
    private LocalTime selectedTime = null;
    first_level_divisions dID;
    countries returnedCountry;
    int countryID;
    int userID = 0;
    String loggedInUser = null;

    /**
     * Method translates text to correct language and sets fields.
     * @param url Not used
     * @param rb Language resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        //Translations of text for record update screen
        rb = ResourceBundle.getBundle("util/rb");
        IDLabel.setText(rb.getString("UpdateRecordCustomerID"));
        nameLabel.setText(rb.getString("UpdateRecordCustomerName"));
        addressLbl.setText(rb.getString("UpdateRecordAddress"));
        postalCodelbl.setText(rb.getString("UpdateRecordPostalCode"));
        phoneNumberLbl.setText(rb.getString("UpdateRecordPhoneNumber"));
        saveBtn.setText(rb.getString("UpdateRecordSaveButton"));
        backBtn.setText(rb.getString("UpdateRecordBackButton"));

        //Getting logged in user.
        try {
            String sUser = null;

            String fileName = "src/util/logs/userlogin";
            File file = new File(fileName);

            if(!file.exists())
            {

                AlertMessages.fileAlerts(1);
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


        try {
            ObservableList<countries> countryList = countries_Impl.getAllCountries();

            divisionList = first_level_Divisons_Imp.getAllDivisions();
            countryIDComboBox.setItems(countryList);

            countryIDComboBox.setVisibleRowCount(3);
            divisionIDComboBox.setVisibleRowCount(3);
            divisionIDComboBox.setDisable(true);





        } catch (Exception e) {
            e.printStackTrace();
        }
        custIDFld.setText("ID Automatically Generated");
        custIDFld.setEditable(false);
        custIDFld.setStyle("-fx-control-inner-background:gray");




    }

    @FXML
    void selectCountryID(ActionEvent event) throws Exception {

        divisionIDComboBox.setDisable(false);

        if(divisionIDComboBox != null)
        {

            selectedCountry = countryIDComboBox.getValue();
            int selectedCountryID = countryIDComboBox.getValue().getCountry_id();



            divisionIDList = first_level_Divisons_Imp.getAllDivisionsByCountryID(selectedCountryID);

            divisionIDComboBox.setItems(divisionIDList);



        }
        else
            //Should never receive this error
        {
            AlertMessages.recordAlerts(4,null);
        }

    }


    /**
     * Sending selected record to the update controller
     * @param record Record contains the country Name, Division Name, Customer ID, Address, Postal Code and Phone Number
     */
    public void sendRecord (customer record)  {




        int selectedDivision = record.getDivision_ID();



        try {
            dID= searches.getDivNameByID(selectedDivision);
            selectedDivisionID = dID.getDivision_ID();


            countryID = dID.getCountry_ID();
             returnedCountry = searches.getCountry(countryID);
             countryIDComboBox.setValue(returnedCountry);
            divisionIDComboBox.setValue(dID);
            custIDFld.setText(String.valueOf(record.getCustomer_ID()));
            customerNameTxtFld.setText(String.valueOf(record.getCustomer_Name()));
            addressTxtFld.setText(String.valueOf(record.getAddress()));
            postalCodeTxtFld.setText(String.valueOf(record.getPostal_Code()));
            phoneNumberTxtFld.setText(String.valueOf(record.getPhone()));


        } catch (Exception e) {
            e.printStackTrace();
        }






    }


    @FXML
    private void onActionDivison(ActionEvent event) {


        divisionIDComboBox.setItems(divisionIDList);

        selectedDivisionID = divisionIDComboBox.getValue().getDivision_ID();
        divisionName = divisionIDComboBox.getValue().getDivision();

        System.out.println(selectedDivisionID);




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
    void onActionSave(ActionEvent event) throws IOException {

        Parent scene;
        Stage stage;

        String name = null;
        String address = null;
        String postalCode = null;
        String phoneNumber = null;
        try {
            Integer customerID = Integer.parseInt(custIDFld.getText());

            System.out.println(customerID);
            name = customerNameTxtFld.getText();
            address = addressTxtFld.getText();
            postalCode = postalCodeTxtFld.getText();
            phoneNumber = phoneNumberTxtFld.getText();

          int divID = selectedDivisionID;



            if(name.isEmpty() && address.isEmpty() && postalCode.isEmpty() && phoneNumber.isEmpty())
            {
                AlertMessages.addRecordFieldErrors(1,customerNameTxtFld,addressTxtFld,postalCodeTxtFld,phoneNumberTxtFld);
                System.out.println("text fields empty");
                return;
            }
            if(name.isEmpty())
            {
                //Throw name error
                AlertMessages.updateRecordAlerts(1,customerNameTxtFld);
                return;
            }
            if(address.isEmpty())
            {
                //Throw address error
                AlertMessages.updateRecordAlerts(2,addressTxtFld);
                return;
            }
            if(postalCode.isEmpty())
            {
                //Throw postal code error
                AlertMessages.updateRecordAlerts(3,postalCodeTxtFld);
                return;
            }
            if (phoneNumber.isEmpty())
            {
                //throw phone error
                AlertMessages.updateRecordAlerts(4,phoneNumberTxtFld);
                return;

            }



           customersImpl.updateCustomerRecord(customerID,name,address,postalCode,phoneNumber,loggedInUser,divID);
           AlertMessages.recordAlerts(5,null);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view_controllers/MainMenu/Menu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }





}
