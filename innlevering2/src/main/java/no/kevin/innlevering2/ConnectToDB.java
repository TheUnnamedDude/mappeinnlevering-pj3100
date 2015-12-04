package no.kevin.innlevering2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Kevin Sillerud
 * Lots of black magics in this class
 */
public class ConnectToDB implements AutoCloseable
{

    private Connection con;

    public ConnectToDB(String serverHost, String databaseName, String user, String pass) throws SQLException
    {
        con = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s", serverHost, databaseName), user, pass);
    }

    @Override
    public void close() throws Exception
    {
        if (con != null)
        {
            con.close();
        }
    }

    public Connection getConnection()
    {
        return con;
    }
}
