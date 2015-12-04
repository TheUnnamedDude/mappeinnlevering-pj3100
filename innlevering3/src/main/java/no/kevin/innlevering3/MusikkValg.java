package no.kevin.innlevering3;

import no.kevin.innlevering3.database.ConnectToDB;
import no.kevin.innlevering3.controller.MusikkHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class MusikkValg extends HttpServlet
{
    MusikkHandler musikkHandler;

    @Override
    public void init() throws ServletException
    {
        super.init();
        try
        {
            /*
            * So in a real production environment I would never put db username and passwords
            * in the class files. But in this task there was no reason for me to put it in a
            * configuration file. My MariaDB server only accepts connections from 127.0.0.1 when
            * using the user unittest, so nobody will get access to the database without physical
            * access to it anyway(linux).
             */
            ConnectToDB connection = new ConnectToDB();
            connection.connect("127.0.0.1", "unittest", "unittest", "unittestpassword");
            musikkHandler = new MusikkHandler(connection);
            musikkHandler.initialize();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String genre = req.getParameter("genre");
        try
        {
            req.setAttribute("albumList", musikkHandler.getAlbum(genre));
        }
        catch (SQLException e)
        {
            req.setAttribute("exception", e);
        }
        req.getRequestDispatcher("/musikkvalg.jsp").forward(req, resp);
    }
}
