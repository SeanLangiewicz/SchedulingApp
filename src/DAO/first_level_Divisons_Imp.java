package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.first_level_divisions;

import java.sql.*;

/**Class gets first level division ID's and division names based off the country ID selected.*/
public class first_level_Divisons_Imp {

    /**List gets all divisions from the division database. */
    public static ObservableList<first_level_divisions> allDivisions = FXCollections.observableArrayList();
    /**List is all division's by the selected country ID. */
    public static ObservableList<first_level_divisions>divisionsByCountryID = FXCollections.observableArrayList();

    /** Method gets all divisions from the division table.
     * @return Returns all divisions into a list.
     * @throws SQLException SQL exception thrown if there is an error with the SQL query.
     */
    public static ObservableList<first_level_divisions>getAllDivisions() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sqlStatement ="SELECT * from first_level_divisions";

        DBQuery.setPreparedStatement(conn,sqlStatement);

        PreparedStatement ps = DBQuery.getPrepareStatement();
        ps.execute();
        ResultSet rs = ps.getResultSet();

        while (rs.next())
        {
            int division_ID = rs.getInt("Division_ID");
            String division = rs.getString("Division");

            int country_ID = rs.getInt("Country_ID");
            first_level_divisions addedDivisions = new first_level_divisions(division_ID,division,country_ID);
            allDivisions.add(addedDivisions);
        }

        DBConnection.closeConnection();
        return allDivisions;
    }

    /** Method gets all divisions by the selected country ID.
     * @param countryID Country ID selected when the user picks a country from the combo box.
     * @return Returns a list of alll divisons by country ID.
     * @throws SQLException SQL exception thrown if there is an issue with the SQL query.
     * @throws Exception Exception thrown if there is an error within the method call.
     */
    public static ObservableList<first_level_divisions> getAllDivisionsByCountryID(Integer countryID) throws SQLException, Exception {

        Connection conn = DBConnection.getConnection();
        String sqlStatement = "SELECT * from first_level_divisions WHERE Country_ID=?";

        DBQuery.setPreparedStatement(conn, sqlStatement);

        PreparedStatement ps = DBQuery.getPrepareStatement();
        ps.setInt(1,countryID);
        ps.execute();

        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int division_ID = rs.getInt("Division_ID");
            String division = rs.getString("Division");

            int country_ID = rs.getInt("Country_ID");

            first_level_divisions addedDivisions = new first_level_divisions(division_ID, division, country_ID);
            divisionsByCountryID.add(addedDivisions);

        }
        DBConnection.closeConnection();
        return divisionsByCountryID;
    }





}
