package DAO;

import javafx.collections.FXCollections;
import model.customer;
import javafx.collections.ObservableList;

import java.sql.*;
import util.AlertMessages;

/**Class provides the ability to manipulate the customers database by adding, selecting, deleting and getting all
 * customers in a list. */
public class customersImpl {


    /**
     * List used to store all customers for the program
     */
    public static ObservableList<customer>customersList = FXCollections.observableArrayList();

    /** Method selects all customers from the customers database.
     * @return Returns a list of all customers from the customer table.
     * @throws SQLException Throws SQL error if there is an issue with getting data from SQL database.
     * @throws Exception Throws an exception if an error is found.
     */
    public static ObservableList<customer> selectAllCustomers() throws SQLException,Exception
    {
        ObservableList<customer> allCustomers = FXCollections.observableArrayList();

        String sqlStatement = "SELECT * from customers";


        Connection conn = DBConnection.getConnection();

            DBQuery.setPreparedStatement(conn, sqlStatement);



        PreparedStatement ps = DBQuery.getPrepareStatement();
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                  int customer_ID = rs.getInt("Customer_ID");
                String customer_Name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postal_Code = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                Date create_Date = rs.getDate("Create_Date");
                String created_By = rs.getString("Created_By");
                Time last_Updated = rs.getTime("Last_Update");
                String last_Updated_By = rs.getString("Last_Updated_By");
                int division_ID = rs.getInt("Division_ID");

                customer addCustomer = new customer(customer_ID, customer_Name, address, postal_Code, phone, create_Date, created_By, last_Updated,
                        last_Updated_By, division_ID);


                allCustomers.add(addCustomer);
                customersList.addAll(allCustomers);


                //System.out.println(customer_ID+""+customer_Name+""+address+""+postal_Code+""+phone+""+create_Date+""+created_By+
                  //      ""+last_Updated+""+last_Updated+""+last_Updated_By+""+division_ID);
            }
        DBConnection.closeConnection();
        return allCustomers;
    }


    /** Method is called when the user is updating the customer fields, which get updated to the customer's table.
     * @param Customer_ID Customer ID from the customer ID field in the update customer method.
     * @param customer_Name Customer name provided by the user in the update customer method.
     * @param customerAddress Customer address provided by the user in the update customer method.
     * @param customerPostalCode Customer Postal code provided by the user in the update customer method.
     * @param customerPhone Customer Phone number provided by the user in the update customer method.
     * @param last_Updated_By User name provided by the login class.
     * @param divisionID Division ID provided by the division combo box in the update customer method.
     */
   public static void updateCustomerRecord (Integer Customer_ID, String customer_Name, String customerAddress, String customerPostalCode,
                                            String customerPhone, String last_Updated_By, Integer divisionID)
   {
       try {
           //SQL Statement
           String sqlUpdateRecord = "UPDATE customers set Customer_Name =? ,Address =?, Postal_Code =?, Phone =?, " +
                   "Last_Updated_By =?, Division_ID =? WHERE Customer_ID =?";


           PreparedStatement pst = DBConnection.getConnection().prepareStatement(sqlUpdateRecord);


           Connection conn = DBConnection.getConnection();

           DBQuery.setPreparedStatement(conn, sqlUpdateRecord);

           pst.setString(1,customer_Name);
           pst.setString(2,customerAddress);
           pst.setString(3,customerPostalCode);
           pst.setString(4,customerPhone);
           pst.setString(5,last_Updated_By);
           pst.setInt(6,divisionID);
           pst.setInt(7,Customer_ID);



           pst.execute();



       } catch (SQLException ex) {
           ex.printStackTrace();
       }
   }

    /** Method deletes the selected customer from the customer table.
     * @param customerID Customer ID provided by the user selecting a record then clicking delete record.
     * @param selectedCustomer Selected customer deleted from the customer list.
     * @throws SQLException SQL exception error thrown if an error is found.
     */
   public static void deleteCustomer(int customerID, customer selectedCustomer) throws SQLException {

       try{
           customersList.remove(selectedCustomer);
           String sqlt = "DELETE FROM customers WHERE Customer_ID = ?";

           PreparedStatement pst = DBConnection.getConnection().prepareStatement(sqlt);
           pst.setInt(1,customerID);
           pst.execute();
            AlertMessages.recordAlerts(3,null);
           return;
       }
       catch (SQLException e)
       {

           AlertMessages.recordAlerts(1,null);
            throw new SQLException("Customer Deletion Failed");

       }
   }

    /** Method takes parameters provided by the user and adds a record to the customer table.
     * @param customer_Name Customer Name provided by the user and added to the customer table.
     * @param customerAddress Customer Address provided by the user to be added to the customer table.
     * @param customerPostalCode Customer Postal Coded provided by the user to be added to the customer table.
     * @param customerPhone Customer phone number provided by the user to be added to the customer table.
     * @param createdBy Created by field provided by the login method to track which user adds the record.
     * @param lastUpdatedBy Last updated field added when the login method provides the user's name.
     * @param divisionID Division ID number provided when the user selects the country and division in the add
     *                   customer record table.
     * @return Returns a customer ID number as a string to track the new user's customer number.
     */
   public static String addCustomerRecord (String customer_Name, String customerAddress, String customerPostalCode,
                                         String customerPhone, String createdBy,
                                           String lastUpdatedBy, Integer divisionID)
   {
       String sql = "INSERT INTO customers (Customer_Name,Address,Postal_Code,Phone,Create_Date,Created_By," +
               "Last_Update, Last_Updated_By,Division_ID) Values(?,?,?,?,now(),?,now(),?,?)";

       String customerID = null;
       try {

           PreparedStatement pst = DBConnection.getConnection().prepareStatement(sql);


           Connection conn = DBConnection.getConnection();

           pst.setString(1,customer_Name);
           pst.setString(2,customerAddress);
           pst.setString(3,customerPostalCode);
           pst.setString(4,customerPhone);
           pst.setString(5,createdBy);
           pst.setString(6,lastUpdatedBy);
           pst.setInt(7,divisionID);

           pst.execute();
           System.out.println("SQL Statement" + sql);
           pst = conn.prepareStatement("SELECT LAST_INSERT_ID() FROM customers");
           ResultSet rs = pst.executeQuery();
           rs.next();
           customerID = rs.getString(1);



       }catch (SQLException ex)
       {
           System.out.println(ex);
       }

       return customerID;
   }


}
