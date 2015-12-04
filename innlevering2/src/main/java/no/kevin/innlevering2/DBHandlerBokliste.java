package no.kevin.innlevering2;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Kevin Sillerud
 */
public class DBHandlerBokliste implements AutoCloseable
{
    private final ConnectToDB dbConnection;
    private final Connection con;

    private final String tableName;
    private final PreparedStatement pstmtUpdateTittel;
    private final PreparedStatement pstmtUpdateForfatter;
    private final PreparedStatement pstmtDeleteTittel;
    private final PreparedStatement pstmtDeleteForfatter;
    private final PreparedStatement pstmtInsertRow;
    private final PreparedStatement pstmtGetTable;
    private final PreparedStatement pstmtGetRow;

    public DBHandlerBokliste(String user, String pass) throws SQLException
    {
        tableName = "bookdb";
        dbConnection = new ConnectToDB("127.0.0.1", "unittest", user, pass); // Why are we hardcoding the host?
        con = dbConnection.getConnection();
        // Used to have NOT NULL behind the fields, but removed it to make the code more tidy
        con.createStatement().execute("CREATE TABLE IF NOT EXISTS " + tableName + "(isbn CHAR(255), author CHAR(255), title CHAR(255));");
        pstmtUpdateTittel = con.prepareStatement("UPDATE " + tableName + " SET title=? WHERE title=?;");
        pstmtUpdateForfatter = con.prepareStatement("UPDATE " + tableName + " SET author=? WHERE author=?;");
        pstmtDeleteTittel = con.prepareStatement("DELETE FROM " + tableName + " WHERE title=?;");
        pstmtDeleteForfatter = con.prepareStatement("DELETE FROM " + tableName + " WHERE author=?;");
        pstmtInsertRow = con.prepareStatement("INSERT INTO " + tableName + " VALUES(?, ?, ?);");
        pstmtGetTable = con.prepareStatement("SELECT * FROM " + tableName + ";");
        pstmtGetRow = con.prepareStatement("SELECT * FROM " + tableName + " WHERE author=? AND title=?;");
    }

    public int updateTittel(String newTitle, String currentTitle) throws SQLException
    {
        pstmtUpdateTittel.setString(1, newTitle);
        pstmtUpdateTittel.setString(2, currentTitle);
        return pstmtUpdateTittel.executeUpdate();
    }

    public int updateForfatter(String newAuthor, String currentAuthor) throws SQLException
    {
        pstmtUpdateForfatter.setString(1, newAuthor);
        pstmtUpdateForfatter.setString(2, currentAuthor);
        return pstmtUpdateForfatter.executeUpdate();
    }

    public int deleteForfatter(String author) throws SQLException
    {
        pstmtDeleteForfatter.setString(1, author);
        return pstmtDeleteForfatter.executeUpdate();
    }

    public int deleteTittel(String title) throws SQLException
    {
        pstmtDeleteTittel.setString(1, title);
        return pstmtDeleteTittel.executeUpdate();
    }

    public int insertRow(String isbn, String author, String title) throws SQLException
    {
        pstmtInsertRow.setString(1, isbn);
        pstmtInsertRow.setString(2, author);
        pstmtInsertRow.setString(3, title);
        return pstmtInsertRow.executeUpdate();
    }

    public ArrayList<String> getTable() throws SQLException
    {
        ArrayList<String> table = new ArrayList<>();
        table.add(mergeText("ISBN", "FORFATTER", "TITTEL"));
        try (ResultSet result = pstmtGetTable.executeQuery())
        {
            while (result.next())
            {
                table.add(mergeText(result.getString(1), result.getString(2), result.getString(3)));
            }
        }
        return table;
    }

    public String getRow(String author, String title) throws SQLException
    {
        pstmtGetRow.setString(1, author);
        pstmtGetRow.setString(2, title);
        try (ResultSet result = pstmtGetRow.executeQuery())
        {
            if (result.next())
            {
                return mergeText(result.getString(1), result.getString(2), result.getString(3));
            }
            else
            {
                return null;
            }
        }
    }

    /**
     * Helper method to merge the text into the result returned by getRow and getTable
     * @param str1 string #1
     * @param str2 string #2
     * @param str3 string #2
     * @return A merged formatted string
     */
    public String mergeText(String str1, String str2, String str3)
    {
        return String.format("%-20s%-20s%-20s", str1, str2, str3);
    }

    @Override
    public void close() throws Exception
    {
        if (dbConnection != null)
        {
            dbConnection.close();
        }
    }
}
