package edu.matc.entjava.web;


import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.BacklogStatus;
import edu.matc.entjava.persistence.BacklogEntryDao;
import edu.matc.entjava.persistence.MediaItemDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.servlet.http.*;

@WebServlet("/deleteBacklog")
public class DeleteBacklogServlet extends HttpServlet {

    private BacklogEntryDao backlogEntryDao;

    @Override
    public void init() {
        backlogEntryDao = new BacklogEntryDao();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        final long currentUserId = 1L; // hardcoded for now

        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            Long id = Long.parseLong(idParam);
            BacklogEntry entry = backlogEntryDao.getById(id);

            if (entry != null && entry.getUser().getId().equals(currentUserId)) {
                backlogEntryDao.delete(entry);
            }
        }

        response.sendRedirect("backlog");
    }
}
