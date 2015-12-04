<%@ page import="no.kevin.innlevering3.model.Album" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Innlevering 3 - musikkvalg</title>
</head>
<body>
<h1>Musikk-anbefalinger</h1><br>
<h3>Da burde du kanskje prøve en av disse:</h3>
<%
    if (request.getAttribute("exception") == null)
    {
        List<Album> albums = (List<Album>) request.getAttribute("albumList");
        for (Album album : albums)
        {
            out.println("<p>" + album + "</p>");
        }
    }
    else
    {
        // Display a little message if a exception occurs, should not do it this in a real environment
        out.println(request.getAttribute("exception"));
    }
%>
</body>
</html>
