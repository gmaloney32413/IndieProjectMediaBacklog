package edu.matc.entjava.controller;

import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.BacklogStatus;
import edu.matc.entjava.entity.User;
import edu.matc.entjava.persistence.BacklogEntryDao;
import edu.matc.entjava.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Backlog servlet.
 */
@WebServlet("/backlog")
public class BacklogServlet extends HttpServlet {

    private BacklogEntryDao backlogDao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void init() {
        backlogDao = new BacklogEntryDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        try {
            // TODO: Replace with actual logged-in user ID from session
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("user") == null) {
                response.sendRedirect("logIn");
                return;
            }

            User user = (User) session.getAttribute("user");
            Long userId = user.getId();


            List<BacklogEntry> backlogEntries = backlogDao.getAll()
                    .stream()
                    .filter(entry -> entry.getUser() != null
                            && entry.getUser().getId().equals(userId))
                    .collect(Collectors.toList());

            // Get search query
            String searchQuery = request.getParameter("searchQuery");

            if (searchQuery != null && !searchQuery.isBlank()) {
                backlogEntries = backlogEntries.stream()
                        .filter(entry -> entry.getMediaItem().getTitle()
                                .toLowerCase()
                                .contains(searchQuery.toLowerCase()))
                        .collect(Collectors.toList());
            }

            // Count backlog entries by status
            Long plannedCount = backlogDao.countByStatusForUser(user, BacklogStatus.PLANNED);
            Long inProgressCount = backlogDao.countByStatusForUser(user, BacklogStatus.IN_PROGRESS);
            Long completedCount = backlogDao.countByStatusForUser(user, BacklogStatus.COMPLETED);
            Long droppedCount = backlogDao.countByStatusForUser(user, BacklogStatus.DROPPED);

            request.setAttribute("plannedCount", plannedCount);
            request.setAttribute("inProgressCount", inProgressCount);
            request.setAttribute("completedCount", completedCount);
            request.setAttribute("droppedCount", droppedCount);

            // Add the list to the request
            request.setAttribute("backlogEntries", backlogEntries);

            // Forward to JSP
            request.getRequestDispatcher("/backlog.jsp").forward(request, response);
        }catch (Exception e) {
            logger.error("Error loading backlog" +e); // FORCE visibility
            throw new ServletException(e);
        }
    }
}