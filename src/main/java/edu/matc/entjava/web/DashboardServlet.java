package edu.matc.entjava.web;

import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.BacklogStatus;
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
    private final BacklogEntryDao backlogEntryDao = new BacklogEntryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long userId = 1L; // hardcoded for now (until Cognito)

        List<MediaItem> mediaItems = mediaItemDao.getAll();
        request.setAttribute("mediaItems", mediaItems);


        // Count backlog entries by status
        Long plannedCount = backlogEntryDao.countByStatusForUser(BacklogStatus.PLANNED, userId);
        Long inProgressCount = backlogEntryDao.countByStatusForUser(BacklogStatus.IN_PROGRESS, userId);
        Long completedCount = backlogEntryDao.countByStatusForUser(BacklogStatus.COMPLETED, userId);
        Long droppedCount = backlogEntryDao.countByStatusForUser(BacklogStatus.DROPPED, userId);

        request.setAttribute("plannedCount", plannedCount);
        request.setAttribute("inProgressCount", inProgressCount);
        request.setAttribute("completedCount", completedCount);
        request.setAttribute("droppedCount", droppedCount);

        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
}