package no.kevin.innlevering2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * @author Kevin Sillerud
 */
public class DBHandlerBooklisteTest
{
    private DBHandlerBokliste bokliste;
    private Connection con;
    @Before
    public void setUp()
    {
        Properties properties = new Properties();
        try
        {
            // Should probably have a better solution for this...
            properties.load(new FileInputStream("/home/kevin/programming/testdb.properties"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String host = properties.getProperty("host");
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://" + host, username, password);
            bokliste = new DBHandlerBokliste(username, password);
            bokliste.insertRow("linux-isbn", "Linus Torvalds", "Linux");
            bokliste.insertRow("darwin-isbn", "Apple", "Darwin");
            bokliste.insertRow("windows-nt-isbn", "Microsoft", "Windows NT");
            bokliste.insertRow("garbage-isbn", "Some Random Guy", "Garbage");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @After
    public void destroy()
    {
        try
        {
            con.prepareStatement("DROP TABLE bookdb;").execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateTittel() throws Exception
    {
        assertEquals("Failed to update title", 1, bokliste.updateTittel("GNU/Linux", "Linux"));
        assertEquals("Number of rows mismatch", 5, bokliste.getTable().size());
    }

    @Test
    public void testUpdateForfatter() throws Exception
    {
        assertEquals("Failed to update author", 1, bokliste.updateForfatter("The Linux Foundation", "Linus Torvalds"));
        assertEquals("Number of rows mismatch", 5, bokliste.getTable().size());
    }

    @Test
    public void testDeleteForfatter() throws Exception
    {
        assertEquals("Failed to delete author", 1, bokliste.deleteForfatter("Some Random Guy"));
        assertEquals("Number of rows mismatch", 4, bokliste.getTable().size());
    }

    @Test
    public void testDeleteTittel() throws Exception
    {
        assertEquals("Failed to delete title", 1, bokliste.deleteTittel("Windows NT")); // Get rid of the garbage
        assertEquals("Number of rows mismatch", 4, bokliste.getTable().size());
    }

    @Test
    public void testInsertRow() throws Exception
    {
        assertEquals("Failed to insert a row", 1, bokliste.insertRow("gnu-isbn", "GNU project", "Richard Stallman"));
        assertEquals("Number of rows mismatch", 6, bokliste.getTable().size());
    }

    @Test
    public void testGetTable() throws Exception
    {
        assertTrue("getTable hadde ikke innhold", !bokliste.getTable().isEmpty());
    }

    @Test
    public void testGetRow() throws Exception
    {
        assertTrue("getRow returned null", bokliste.getRow("Apple", "Darwin") != null);
    }
}