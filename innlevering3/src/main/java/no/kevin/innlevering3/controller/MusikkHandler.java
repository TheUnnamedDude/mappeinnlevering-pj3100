package no.kevin.innlevering3.controller;

import no.kevin.innlevering3.database.ConnectToDB;
import no.kevin.innlevering3.model.Album;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusikkHandler implements AutoCloseable
{
    Connection con;
    private ConnectToDB con2db;
    private PreparedStatement selectWhereGenre;
    private PreparedStatement insertAlbum;
    public MusikkHandler(ConnectToDB con)
    {
        this.con = con.getConnection();
        this.con2db = con;
    }

    public void initialize() throws SQLException
    {
        selectWhereGenre = con.prepareStatement("SELECT * FROM album_info WHERE genre LIKE ?;");
        insertAlbum = con.prepareStatement("INSERT INTO album_info VALUES(?, ?, ?, ?, ?);");
    }

    public List<Album> getAlbum(String genre) throws SQLException
    {
        ArrayList<Album> albums = new ArrayList<>();
        selectWhereGenre.setString(1, genre);
        ResultSet result = selectWhereGenre.executeQuery();
        while (result.next())
        {
            albums.add(new Album(result.getString("artist"), result.getString("title"), result.getInt("track"),
                    result.getInt("released"), result.getString("genre")));
        }
        return albums;
    }

    public void addAlbum(Album album) throws SQLException
    {
        insertAlbum.setString(1, album.getArtist());
        insertAlbum.setString(2, album.getTittel());
        insertAlbum.setInt(3, album.getSpor());
        insertAlbum.setInt(4, album.getUtgitt());
        insertAlbum.setString(5, album.getSjanger());
        insertAlbum.execute();
    }

    @Override
    public void close() throws Exception
    {
        selectWhereGenre.close();
        insertAlbum.close();
        con2db.close();
    }
}
