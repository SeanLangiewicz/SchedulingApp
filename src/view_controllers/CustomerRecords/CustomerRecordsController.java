package view_controllers.CustomerRecords;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.customer;
import DAO.customersImpl;
import util.AlertMessages;

/**
 * Method used to show current records and gives the user an option to delete, add or update a record.
 */
public class CustomerRecordsController implements Initializable {

    Parent scene;
    Stage stage;

    @FXML
    private Button addRecordBtn;

    @FXML
    private Button updateRecordBtn;


    @FXML
    private TableView<customer> recordsTableView;

    @FXML
    private TableColumn<?, ?> ID;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> address;

    @FXML
    private TableColumn<?, ?> postal;

    @FXML
    private TableColumn<?, ?> phone;

    @FXML
    private TableColumn<?, ?> createDate;

    @FXML
    private TableColumn<?, ?> createdBy;

    @FXML
    private TableColumn<?, ?> lastUpdated;

    @FXML
    private TableColumn<?, ?> lastUpdatedBy;

    @FXML
    private TableColumn<?, ?> DivID;

    @FXML
    private Button backBtn;

    @FXML
    private Button deleteRecordBtn;

    @FXML
    private Label recordTitleLbl;



    ObservableList<customer> customersList = FXCollections.observableArrayList();

    /**
     * Method used to set text to the correct language, combo boxes and lists.
     * @param url Not used.
     * @param rb Parameter used for the language resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rb = ResourceBundle.getBundle("util/rb");

        recordTitleLbl.setText(rb.getString("RecordScreen"));
        addRecordBtn.setText(rb.getString("AddRecord"));
        updateRecordBtn.setText(rb.getString("UpdateRecord"));
        deleteRecordBtn.setText(rb.getString("DeleteRecord"));
        backBtn.setText(rb.getString("Back"));
        ID.setText(rb.getString("ID"));
        name.setText(rb.getString("CustomerName"));
        address.setText(rb.getString("Address"));
        postal.setText(rb.getString("PostalCode"));
        phone.setText(rb.getString("Phone"));
        createDate.setText(rb.getString("CreateDate"));
        createdBy.setText(rb.getString("CreatedBy"));
        lastUpdated.setText(rb.getString("LastUpdate"));
        lastUpdatedBy.setText(rb.getString("LastUpdatedBy"));
        DivID.setText(rb.getString("DivisionID"));




        try {
            //Loads Records Table View
            customersList.addAll(customersImpl.selectAllCustomers());
            recordsTableView.setItems(customersList);

            ID.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
            name.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
            address.setCellValueFactory(new PropertyValueFactory<>("address"));
            postal.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
            phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            createDate.setCellValueFactory(new PropertyValueFactory<>("create_Date"));
            createdBy.setCellValueFactory(new PropertyValueFactory<>("created_By"));
            lastUpdated.setCellValueFactory(new PropertyValueFactory<>("last_Updated"));
            lastUpdatedBy.setCellValueFactory(new PropertyValueFactory<>("last_Updated_By"));
            DivID.setCellValueFactory(new PropertyValueFactory<>("division_ID"));

        } catch (Exception e) {

            }


    }




    @FXML
    void onActionUpdateRecord(ActionEvent event) throws IOException {

        try {

            customer selectedRecord = recordsTableView.getSelectionModel().getSelectedItem();

            if (selectedRecord == null)
            {

                AlertMessages.recordAlerts(2,null);
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view_controllers/CustomerRecords/CustomerUpdateRecord.fxml"));
            loader.load();

            CustomerUpdateRecordsController updateController = loader.getController();
            updateController.sendRecord(recordsTableView.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (Exception e) {


            throw new IOException(e);


        }

    }


    @FXML
    void onActionAddRecord(ActionEvent event) throws IOException{

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view_controllers/CustomerRecords/AddCustomerRecord.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionBack(ActionEvent event) throws IOException {



        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view_controllers/MainMenu/Menu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDeleteRecord(ActionEvent event) {



        try {
            customer selectedCustomer = recordsTableView.getSelectionModel().getSelectedItem();
            int selectedCustomerID = recordsTableView.getSelectionModel().getSelectedItem().getCustomer_ID();

            customersImpl.deleteCustomer(selectedCustomerID,selectedCustomer);
            customersList.remove(selectedCustomer);


        }
        catch (Exception e)
        {
            System.out.println(e);

        }
        recordsTableView.refresh();


    }



}
