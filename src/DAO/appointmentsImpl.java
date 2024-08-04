package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.appointments;
import model.month_Type;

import java.sql.*;
import java.time.LocalDateTime;



/**
 * Class provides has methods that provide appointment data from SQL and uses Observable Lists to store the data.
 */
public class appointmentsImpl {


    /**List stores all appointments for use*/
    public static ObservableList<appointments> appointmentsList = FXCollections.observableArrayList();
    /** List stores appointments by month*/
    public static ObservableList<appointments> monthAppointments = FXCollections.observableArrayList();
    /** List stores all appointments from 7 days of the user logging in*/
    public static ObservableList<appointments>sevenDayAppointments = FXCollections.observableArrayList();




    /** List stores all appointments for the current day*/
    public static ObservableList<appointments>dayAppointments = FXCollections.observableArrayList();



    /** Gets all appointments from the appointment tables.
     * @return Returns a list of all appointments in the appointments table.
     * @throws SQLException Throws SQL Exception error if there an issue with the SQL query.
     */
     public static ObservableList<appointments>getAllAppointments() throws SQLException {

        ObservableList<appointments>allAppointments = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        try {
            String sqlStatement ="Select * from appointments Order by Appointment_ID";
            DBQuery.setPreparedStatement(conn,sqlStatement);

            PreparedStatement ps = DBQuery.getPrepareStatement();

            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next())
            {
                int appointment_id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime startDateTS = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endDateTS = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDateTS = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customer_ID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                appointments addedAppointment = new appointments(appointment_id,title,description,location,type,startDateTS,
                        endDateTS,createDateTS,createdBy,lastUpdate,lastUpdatedBy,customer_ID,userID,contactID);
                allAppointments.add(addedAppointment);
                appointmentsList.addAll(allAppointments);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        DBConnection.closeConnection();
        return allAppointments;

    }

    /** Gets appointments from the appointment table by the user ID.
     * @param userID Logged in user's ID.
     * @return Returns a list of all appointments that have the user's ID.
     * @throws SQLException SQL Exception thrown if there is an error in SQL.
     */
    public static ObservableList<appointments> appointmentsByID(int userID) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "select * from appointments where User_ID=?;";

        DBQuery.setPreparedStatement(conn, sql);
        PreparedStatement pst = DBConnection.getConnection().prepareStatement(sql);
        pst.setInt(1, userID);
        pst.execute();
        ResultSet rs = pst.getResultSet();
        while (rs.next()) {


            int appointment_id = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime startDateTS = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endDateTS = rs.getTimestamp("End").toLocalDateTime();
            LocalDateTime createDateTS = rs.getTimestamp("Create_Date").toLocalDateTime();
            String createdBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customer_ID = rs.getInt("Customer_ID");
            int ID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            appointments addedAppointment = new appointments(appointment_id,title,description,location,type,
                    startDateTS,endDateTS,createDateTS,createdBy,lastUpdate,lastUpdatedBy,customer_ID,ID,contactID);

            dayAppointments.add(addedAppointment);




        }
        return dayAppointments;
    }

    /** Query's appointment table for appointments with the same date as the date.
     * @return Returns a list of appointments that have the same date as the current date.
     * @throws SQLException Throws a SQL error if there is an error with the SQL query.
     */
    public static ObservableList<appointments>monthAppointments() throws SQLException
    {
        Connection conn = DBConnection.getConnection();
        String sqlStatement = "Select * from appointments where month(Start ) = Month(current_date()) Order by Appointment_ID;";


        DBQuery.setPreparedStatement(conn,sqlStatement);
        PreparedStatement ps = DBQuery.getPrepareStatement();
        ps.execute();
        ResultSet rs = ps.getResultSet();

        while (rs.next())
        {
            int appointment_id = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime startDateTS = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endDateTS = rs.getTimestamp("End").toLocalDateTime();
            LocalDateTime createDateTS = rs.getTimestamp("Create_Date").toLocalDateTime();
            String createdBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customer_ID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            appointments addedAppointment = new appointments(appointment_id,title,description,location,type,startDateTS,
                    endDateTS,createDateTS,createdBy,lastUpdate,lastUpdatedBy,customer_ID,userID,contactID);
            monthAppointments.add(addedAppointment);

        }


        return monthAppointments;
    }




    /** Method gets appointments for the next 7 days as of the current date.
     * @return Returns a list of appointments that are within 7 days of the current date.
     * @throws SQLException SQL Error thrown if there is an issue with the query.
     */
    public static ObservableList<appointments>sevenDayAppointments() throws SQLException
    {

        try {
            Connection conn = DBConnection.getConnection();
            String aptSql = "select * from appointments where Start between Current_Date() and (Current_Date() + " +
                    "Interval 7 day) order by appointment_ID;";
            DBQuery.setPreparedStatement(conn,aptSql);
            PreparedStatement ps = DBQuery.getPrepareStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next())
            {
                int appointment_id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime startDateTS = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endDateTS = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDateTS = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customer_ID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                appointments addedAppointment = new appointments(appointment_id,title,description,location,type,startDateTS,
                        endDateTS,createDateTS,createdBy,lastUpdate,lastUpdatedBy,customer_ID,userID,contactID);
                sevenDayAppointments.add(addedAppointment);

            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }


        DBConnection.closeConnection();
        return  sevenDayAppointments;
    }

    /** Delete's appointment by appointment ID
     * @param appointmentID Provided appointment ID that will be used to delete the appointment record with the same ID.
     * @throws SQLException Throws a SQL error if there is an issue with the delete query.
     */
   public static void deleteAppointmentByAppointmentID(int appointmentID) throws SQLException
   {


       String sqlt = "DELETE FROM appointments WHERE Appointment_ID = ?";

       PreparedStatement pst = DBConnection.getConnection().prepareStatement(sqlt);
       pst.setInt(1, appointmentID);
       pst.execute();
       return;
     }







    /** Method adds an appointment to the appointment table with the parameters provided.
     * @param title Title provided by the user to be added to the appointment table.
     * @param description Description provided by the user to be added to the appointment table.
     * @param location Location provided by the user to be added to the appointment table.
     * @param type Type provided by the user to be added to the appointment table.
     * @param startTime Start time provided by the user and converted to UTC time then added to the appointment table.
     * @param endTime End time provided by the startTime and 1 added hour to the appointment. Appointment is then
     *                added to the appointment table.
     * @param createdBy Created time provided by the login function when the user logs in. To be added to the
     *                  appointment table.
     * @param lastUpdatedBy Last Updated By name provided by the login function. Gets added to the appointment table.
     * @param customerID Customer ID selected by the user and added to the appointment table.
     * @param user_ID User ID provided by the user once logged in.
     * @param contactID Contacted ID provided by the user when selecting a contact in the drop down list.
     * @return Returns an appointment object so it can be added into the appointment table.
     */
   public static String addAppointment ( String title, String description, String location, String type, Timestamp startTime,
                                         Timestamp endTime,String createdBy,String lastUpdatedBy,Integer customerID,
                                         Integer user_ID, Integer contactID)

   {
        String appointmentIDNumb = null;
        String sql = "INSERT INTO appointments (Title,Description,Location,Type,Start,End,Create_Date,Created_By," +
                "Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID) Values(?,?,?,?,?,?,now(),?,now(),?," +
                "?,?,?)";

       try {

           PreparedStatement pst = DBConnection.getConnection().prepareStatement(sql);


           Connection conn = DBConnection.getConnection();

            pst.setString(1,title);
            pst.setString(2,description);
            pst.setString(3,location);
            pst.setString(4,type);
            pst.setTimestamp(5,startTime);
            pst.setTimestamp(6,endTime);
           pst.setString(7,createdBy);
           pst.setString(8,lastUpdatedBy);
            pst.setInt(9,customerID);
            pst.setInt(10,user_ID);
            pst.setInt(11,contactID);

           pst.execute();
          pst = conn.prepareStatement("SELECT LAST_INSERT_ID() FROM appointments");
           ResultSet rs = pst.executeQuery();
           rs.next();
           appointmentIDNumb = rs.getString(1);



       }catch (SQLException ex)
       {
           System.out.println(ex);
       }

       return appointmentIDNumb;

   }

   //Method runs SQL query to find month appointments with the same type of appointment for a total. Provides an obj
   // for a table.

    /** Method gets a list of month and type of appointments and provides a total number of type of appointments for
     * a given month.
     * @return Returns a list of report objects.
     */
   public static ObservableList<month_Type>monthTypeAppointmentsQuery()  {

       /**List stores all month type appointments*/
     ObservableList<month_Type> monthTypeAppointments = FXCollections.observableArrayList();

       String sqlStatement ="select distinct (monthname(Start))as Month,type as Type, count(monthname(Start)) as " +
               "Count from appointments group by monthname(Start), type;";

       Connection conn = DBConnection.getConnection();

       try
       {
           DBQuery.setPreparedStatement(conn,sqlStatement);
           PreparedStatement ps = DBQuery.getPrepareStatement();
           ps.execute();
           ResultSet rs = ps.getResultSet();
           while (rs.next())
           {
               String month = rs.getString("Month");
               String type = rs.getString("Type");
               Integer count = rs.getInt("Count");
               month_Type newReport = new month_Type(month,type,count);

               monthTypeAppointments.add(newReport);

           }


       }

       catch (Exception e)
       {
           System.out.println(e);
       }


       DBConnection.closeConnection();

       return monthTypeAppointments;
   }

    /**
     * Method takes data from the update appointment screen.
     * @param title Updated Title provided by the user to update the title field in the appointment table.
     * @param description Description provided by the user to update the description field in the appointment table.
     * @param location Location provided by the user to update the location field in the appointment table.
     * @param type Type provided by the user to update the type field in the appointment table.
     * @param start Start provided from StartTime Local Date Time field.
     * @param end End provided from the End Time Local Date Time Field.
     * @param lastUpdatedBy Last updated by provided by the login field once the user logs in.
     * @param customerID Customer ID provided from the customer combo box.
     * @param userID User ID that is provided by the login screen once the user logs in.
     * @param contactID Provided by the contact combo box from the user's selection.
     * @param appointmentID Appointment ID that is not editable by the user.
     */
   public static void updateAppointment (String title, String description,String location,String type, Timestamp start,
                                         Timestamp end,String lastUpdatedBy,Integer customerID,
                                         Integer userID, Integer contactID,Integer appointmentID)
       {
           String appointmentIDNumb = null;
           String sqlUpdate = "UPDATE appointments set Title =?, Description=?, Location=?,Type=?,Start =?, End =?, " +
                   "Last_Update = now(),Last_Updated_By =?,Customer_ID=?, User_ID=?, Contact_ID =? WHERE Appointment_ID =?";


           try {

               PreparedStatement pst = DBConnection.getConnection().prepareStatement(sqlUpdate);


               Connection conn = DBConnection.getConnection();


                    pst.setString(1, title);
                    pst.setString(2,description);
                    pst.setString(3,location);
                    pst.setString(4,type);
                    pst.setTimestamp(5,start);
                    pst.setTimestamp(6,end);
                    pst.setString(7,lastUpdatedBy);
                    pst.setInt(8,customerID);
                    pst.setInt(9,userID);
                    pst.setInt(10,contactID);
                    pst.setInt(11,appointmentID);

               pst.execute();



               pst = conn.prepareStatement("SELECT LAST_INSERT_ID() FROM appointments");
               ResultSet rs = pst.executeQuery();
               rs.next();
               appointmentIDNumb = rs.getString(1);


           } catch (SQLException ex) {
               System.out.println(ex);
           }
           System.out.println(appointmentIDNumb);

       }
}

