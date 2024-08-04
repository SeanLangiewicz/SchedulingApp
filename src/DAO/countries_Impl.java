package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.countries;

import java.sql.*;
import java.util.Date;

/**Class gets a list of all countries. */
public class countries_Impl {

    /** Gets all countries from the countries table.
      * @return Returns a list of countries to be used in the table.
     * @throws Exception Throws an error if a SQL error is found.
     */
    public static ObservableList<countries> getAllCountries() throws Exception {

        ObservableList<countries>allCountries =FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();

        String sqlStatement = "SELECT * FROM countries";

        DBQuery.setPreparedStatement(conn,sqlStatement);

        PreparedStatement ps = DBQuery.getPrepareStatement();
        ps.execute();

        ResultSet rs = ps.getResultSet();
        while (rs.next())
        {
            int country_id = rs.getInt("Country_ID");
            String country = rs.getString("Country");
            Date create_Date = rs.getDate("Create_Date");
            String created_By = rs.getString("Created_By");
            Time last_Update = rs.getTime("Last_Update");
            String last_Update_By = rs.getString("Last_Updated_By");

            countries addedCountries = new countries(country_id,country,create_Date,created_By,last_Update,last_Update_By);
            allCountries.add(addedCountries);

           // System.out.println("Country ID " + country_id + " Country Name " + country);
        }
        DBConnection.closeConnection();
        return allCountries;
    }


}
