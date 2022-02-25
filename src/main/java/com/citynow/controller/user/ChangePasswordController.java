package com.citynow.controller.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller page change password
 * @author VuKhanh
 */
@WebServlet(urlPatterns = {"/change-password"})
public class ChangePasswordController extends HttpServlet {

    /**
     * Return page change password
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Send message
        String message = req.getParameter("message");
        if (message != null) {
            req.setAttribute("message", message);
        }
        req.getRequestDispatcher("/views/user/changePass.jsp").forward(req, resp);
    }
}
