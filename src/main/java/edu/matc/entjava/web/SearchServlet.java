package edu.matc.entjava.web;

import edu.matc.entjava.entity.MediaItem;
import edu.matc.entjava.persistence.MediaItemDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

    private final MediaItemDao mediaItemDao = new MediaItemDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get search term from query parameter
        String searchTerm = request.getParameter("search");

        List<MediaItem> mediaItems;

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            // No search term → get all media items
            mediaItems = mediaItemDao.getAll();
        } else {
            // Search term entered → filter by title
            mediaItems = mediaItemDao.searchByTitle(searchTerm.trim());
        }

        // Add the list to request scope
        request.setAttribute("mediaItems", mediaItems);

        // Forward to search.jsp
        request.getRequestDispatcher("/search.jsp").forward(request, response);
    }
}