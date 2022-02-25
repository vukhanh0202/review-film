package com.citynow.controller.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  Controller of log out page
 */
@WebServlet(urlPatterns = {"/logout"})
public class LogOutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // If session not null (User was login)
        if (session != null) {
            // Invalidate session
            session.invalidate();
            // Redirect home page
            resp.sendRedirect("/home");
        }
    }
}
