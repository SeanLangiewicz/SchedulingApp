package DAO;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.contacts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class provides a contact list for the program. */
public class contactsImpl {

    /** Method gets all contacts for the program.
     * @return Returns a list of all contacts.
     * @throws SQLException SQL error thrown if there is an error with the SQL query.
     */
    public static ObservableList<contacts> getallContacts() throws SQLException {
        ObservableList<contacts> allContacts = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String sqlStatement = "SELECT * FROM contacts";

        DBQuery.setPreparedStatement(conn, sqlStatement);

        PreparedStatement ps = DBQuery.getPrepareStatement();
        ps.execute();

        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int contact_id = rs.getInt("Contact_ID");
            String contact_Name = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            contacts addedContacts = new contacts(contact_id, contact_Name, email);

            allContacts.add(addedContacts);

        }
        DBConnection.closeConnection();
        return allContacts;
    }
}
