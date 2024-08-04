package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Class connects to the SQL database. */
public class DBConnection {

    //JDBC URL Parts
    private static final String protocal = "jdbc";
    private static final String venderName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com/WJ04ZXp";
    private static final String disableSSL = "?useSSL=false";

    //JDBC URL
    private static final String jdbcURL = protocal + venderName + ipAddress + disableSSL;

    //Driver and connect Interface Reference
    private static final String mySQLJDBCDriver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;

    private static final String userName = "U04ZXp";
    private static final String password = "53688389161";


    public static Connection getConnection() {
        try {
            Class.forName(mySQLJDBCDriver);
            conn = (Connection) DriverManager.getConnection(jdbcURL, userName, password);
           // System.out.println("Connection Successful " + conn);


        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }


    public static void closeConnection()
    {
        try {
            conn.close();
            //System.out.println("SQL Connection Closed");
        }
        catch (SQLException e )
        {
            System.out.println("Error:" + e.getMessage());

        }
    }

    //public static void makeQuery
}









