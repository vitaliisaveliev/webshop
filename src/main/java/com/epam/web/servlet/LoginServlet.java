package com.epam.web.servlet;

import com.epam.db.entity.User;
import com.epam.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.constant.ApplicationConstants.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service = (UserService) getServletContext().getAttribute(USER_SERVICE);
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        User user = service.getUser(email, password);
        if (user != null) {
            req.getSession().setAttribute(IS_AUTHORIZED, true);
            req.getSession().setAttribute(USER, user);
        }
        req.getRequestDispatcher(INDEX_JSP).forward(req, resp);
    }
}
