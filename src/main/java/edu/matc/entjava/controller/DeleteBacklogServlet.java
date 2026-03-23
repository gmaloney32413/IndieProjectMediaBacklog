package edu.matc.entjava.controller;


import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        final long currentUserId = 1L; // hardcoded for now

        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            Long id = Long.parseLong(idParam);
            BacklogEntry entry = backlogDao.getById(id);

            if (entry != null && entry.getUser().getId().equals(currentUserId)) {
                backlogDao.delete(entry);
            }
        }

        response.sendRedirect("backlog");
    }
}
