package edu.matc.entjava.controller;


import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.User;
import edu.matc.entjava.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Delete backlog servlet.
 */
@WebServlet("/deleteBacklog")
public class DeleteBacklogServlet extends HttpServlet {

    private GenericDao<BacklogEntry> backlogDao;

    @Override
    public void init() {
        backlogDao = new GenericDao<>(BacklogEntry.class);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("logIn");
            return;
        }

        User currentUser = (User) session.getAttribute("user");

        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            Long id = Long.parseLong(idParam);
            BacklogEntry entry = backlogDao.getById(id);

            if (entry != null && entry.getUser().getId().equals(currentUser.getId())) {
                backlogDao.delete(entry);
            }
        }

        response.sendRedirect("backlog");
    }
}
