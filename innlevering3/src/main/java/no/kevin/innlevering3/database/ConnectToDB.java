package no.kevin.innlevering3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB implements AutoCloseable
{
    private Connection con;

    public void connect(String host, String database, String username, String password) throws SQLException
    {
        try
        {
            // Not always needed, better safe then sorry.
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        con = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, username, password);
    }

    public Connection getConnection()
    {
        return con;
    }

    @Override
    public void close() throws Exception
    {
        con.close();
    }
}
