/**
 * @author Matthew Boyette (N00868808@ospreys.unf.edu)
 * @version 1.0
 * 
 *          This helper class contains useful utility methods.
 */

package dml.team5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class Utility
{
    /**
     * Executes a SQL statement on the Oracle database server and returns the corresponding {@link java.sql.ResultSet}.
     * 
     * @param connection
     *            a {@link java.sql.Connection} to the database server.
     * @param input
     *            a {@link java.lang.String} containing the SQL query.
     * @return the {@link java.sql.ResultSet}.
     * @throws SQLException
     *             if a database access error occurs, or the given SQL statement produces anything other than a single {@link java.sql.ResultSet}.
     * @since 1.0
     */
    public static final ResultSet executeSQLStatement(final Connection connection, final String input) throws SQLException
    {
        ResultSet results = null;

        if ( connection != null )
        {
            results = connection.createStatement().executeQuery(input);
        }

        return results;
    }

    /**
     * Establishes a connection to the Oracle database server.
     * 
     * @return the {@link java.sql.Connection}.
     * @since 1.0
     */
    public static final Connection getConnection()
    {
        Connection connection = null;

        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            try
            {
                connection = DriverManager.getConnection("jdbc:oracle:thin:@" + OracleServerSettings.SERVER + ":" + OracleServerSettings.PORT + ":" + OracleServerSettings.DATABASE, OracleServerSettings.USERNAME, OracleServerSettings.PASSWORD);
            }
            catch ( final SQLException sqle )
            {
                sqle.printStackTrace();
            }
        }
        catch ( final ClassNotFoundException cnfe )
        {
            cnfe.printStackTrace();
        }

        return connection;
    }
}
