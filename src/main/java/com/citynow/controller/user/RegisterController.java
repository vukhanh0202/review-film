package com.citynow.controller.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller of register page
 */
@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get message to alert user
        String message = req.getParameter("message");
        if (message != null) {
            req.setAttribute("message", message);
        }
        req.getRequestDispatcher("/views/user/register.jsp").forward(req,resp);
    }
}
