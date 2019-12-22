package ua.nure.shuba.usermanagement.web;

import ua.nure.shuba.usermanagement.db.DAOFactory;
import ua.nure.shuba.usermanagement.db.DatabaseException;
import ua.nure.shuba.usermanagement.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends EditServlet {

    @Override
    protected void processUser(User user) throws DatabaseException {
        DAOFactory.getInstance().getUserDao().create(user);
    }

    @Override
    protected void showPage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }
}
