package ua.nure.kn.kukhtin.usermanagment.web;

import ua.nure.kn.kukhtin.usermanagment.User;
import ua.nure.kn.kukhtin.usermanagment.db.DaoFactory;
import ua.nure.kn.kukhtin.usermanagment.db.DatabaseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("okButton") != null) {
            doOk(req, resp);
        } else if (req.getParameter("cancelButton") != null) {
            doCancel(req, resp);
        } else {
            showPage(req, resp);
        }
    }


    private void doCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/browse.jsp").forward(req, resp);
    }

    private void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        try {
            processUser(user);
        } catch (DatabaseException e) {
            throw new ServletException(e.getMessage());
        }
        req.getRequestDispatcher("/browse").forward(req, resp);
    }

    private void processUser(User user) throws DatabaseException {
        DaoFactory.getInstance().getUserDAO().delete(user);
    }


    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/delete.jsp").forward(req, resp);
    }
}
