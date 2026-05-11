package edu.matc.entjava.controller;

import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.User;
import edu.matc.entjava.persistence.BacklogEntryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * The type Account servlet.
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    private BacklogEntryDao backlogDao;

    @Override
    public void init() {
        backlogDao = new BacklogEntryDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("logIn");
            return;
        }

        User user = (User) session.getAttribute("user");

        List<BacklogEntry> backlog = backlogDao.getByUser(user);

        // ----------------------------
        // BASIC STATS
        // ----------------------------
        int total = backlog.size();

        long completed = backlog.stream()
                .filter(b -> b.getStatus().name().equals("COMPLETED"))
                .count();

        long planned = backlog.stream()
                .filter(b -> b.getStatus().name().equals("PLANNED"))
                .count();

        long inProgress = backlog.stream()
                .filter(b -> b.getStatus().name().equals("IN_PROGRESS"))
                .count();

        long dropped = backlog.stream()
                .filter(b -> b.getStatus().name().equals("DROPPED"))
                .count();

        long active = planned + inProgress;

        double completionRate = total == 0 ? 0 :
                (completed * 100.0) / total;

        // ----------------------------
        // MEDIA TYPE STATS
        // ----------------------------
        long movieCount = backlog.stream()
                .filter(b -> b.getMediaItem() != null
                        && "movie".equalsIgnoreCase(b.getMediaItem().getMediaType()))
                .count();

        long tvCount = backlog.stream()
                .filter(b -> b.getMediaItem() != null
                        && b.getMediaItem().getMediaType().toLowerCase().contains("tv"))
                .count();

        // ----------------------------
        // SET ATTRIBUTES
        // ----------------------------
        req.setAttribute("totalBacklog", total);
        req.setAttribute("completed", completed);
        req.setAttribute("planned", planned);
        req.setAttribute("inProgress", inProgress);
        req.setAttribute("dropped", dropped);
        req.setAttribute("active", active);
        req.setAttribute("completionRate", completionRate);

        req.setAttribute("movieCount", movieCount);
        req.setAttribute("tvCount", tvCount);

        req.getRequestDispatcher("/account.jsp").forward(req, resp);
    }
}