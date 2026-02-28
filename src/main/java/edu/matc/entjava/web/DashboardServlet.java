package edu.matc.entjava.web;

import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.MediaItem;
import edu.matc.entjava.persistence.BacklogEntryDao;
import edu.matc.entjava.persistence.MediaItemDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private final MediaItemDao mediaItemDao = new MediaItemDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<MediaItem> mediaItems = mediaItemDao.getAll();

        // Add the list to the request
        request.setAttribute("mediaItems", mediaItems);

        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
}