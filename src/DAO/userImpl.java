package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.user;


import java.sql.*;

/** Clas used to get all users and check if the username and password provided match any users within the database.
 */
public class userImpl {

    /** Used to store a list of all users and their password.
     */
    public static ObservableList<user> allUsers = FXCollections.observableArrayList();
    /**
     * Used to hold the user's login username and password to be checked with the credentials in the database.
     */
    public static ObservableList<user>signedInUserList = FXCollections.observableArrayList();


    /** Method used to check if the provided username and password match with the credentials within the database.
     * @param userName Username provided by the login user text field.
     * @param password Password by the user in the password text field
     * @return Returns true if the credentials match with a record in the database. Returns false if provided
     * credentials do not match with credentials in the database.
     * @throws SQLException SQL exception thrown if there is an issue with the SQL query.
     */
    public static boolean userSigninCheck(String userName, String password) throws SQLException

 {

     Connection conn = null;
     PreparedStatement pst = null;
     ResultSet  rs = null;

     conn = DBConnection.getConnection();
     String sqlStatement = "SELECT User_Id,User_Name from users WHERE User_Name =? and Password =?";

     pst = conn.prepareStatement(sqlStatement);
     pst.setString(1,userName);
     pst.setString(2,password);

     rs = pst.executeQuery();
     if(rs.next())
     {

         Integer uID = rs.getInt("User_ID");
         String uName = rs.getString("User_Name");
         user loggedInUser = new user(uID,uName);
         signedInUserList.add(loggedInUser);
         //Nulls out password value
         password=null;

     }

     else
     {

         return false;

     }


   return true;
 }




public static ObservableList<user> getAllUsers () throws SQLException, Exception
    {

        Connection conn = DBConnection.getConnection();
        String sqlStatement = "select user_ID, user_name from users;";
            DBQuery.setPreparedStatement(conn,sqlStatement);

            PreparedStatement ps = DBQuery.getPrepareStatement();
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next())
            {
                int userID = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");

                user addedUser = new user(userID,userName);
                allUsers.add(addedUser);



            }
            DBConnection.closeConnection();
            return allUsers;
    }



}
