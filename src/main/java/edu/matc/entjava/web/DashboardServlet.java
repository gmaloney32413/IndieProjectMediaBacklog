package edu.matc.entjava.web;

import edu.matc.entjava.entity.BacklogStatus;
import edu.matc.entjava.entity.MediaItem;
import edu.matc.entjava.entity.User;
import edu.matc.entjava.persistence.BacklogEntryDao;
import edu.matc.entjava.persistence.GenericDao;
import edu.matc.entjava.persistence.MediaItemDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long userId = 1L; // hardcoded for now (until Cognito)

        GenericDao<User> userDao = new GenericDao<>(User.class);
        User currentUser = userDao.getById(userId);
        // Get search parameters (if present)
        String searchQuery = request.getParameter("searchQuery");
        String searchType = request.getParameter("searchType");

        logger.debug("Search parameters received - query: " + searchQuery + ", type: " + searchType);
        List<MediaItem> mediaItems;

        if (searchQuery != null && !searchQuery.isBlank()) {
            mediaItems = mediaItemDao.searchByTitleAndType(searchQuery, searchType);
        } else {
            mediaItems = mediaItemDao.getAll();
            logger.debug("No search query provided, returning all media items, total: " + mediaItems.size());
        }

        request.setAttribute("mediaItems", mediaItems);


        // Count backlog entries by status
        Long plannedCount = backlogEntryDao.countByStatusForUser(currentUser, BacklogStatus.PLANNED);
        Long inProgressCount = backlogEntryDao.countByStatusForUser(currentUser, BacklogStatus.IN_PROGRESS);
        Long completedCount = backlogEntryDao.countByStatusForUser(currentUser, BacklogStatus.COMPLETED);
        Long droppedCount = backlogEntryDao.countByStatusForUser(currentUser, BacklogStatus.DROPPED);

        request.setAttribute("plannedCount", plannedCount);
        request.setAttribute("inProgressCount", inProgressCount);
        request.setAttribute("completedCount", completedCount);
        request.setAttribute("droppedCount", droppedCount);

        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
}