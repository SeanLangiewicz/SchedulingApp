package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * Methods used to set and return a prepared statement object.
 */
public class DBQuery
{
    //Statement reference
    private static PreparedStatement statement;

    //Create Prepare Statement Object
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {

        statement = conn.prepareStatement(sqlStatement);
    }

    //Return Statement Object
    public static PreparedStatement getPrepareStatement()
    {
        return statement;
    }
}
