package com.citynow.controller.user;

import com.citynow.service.IUserService;
import com.citynow.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller profile of account
 */
@WebServlet(urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {

    IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get message to alert user
        String message = req.getParameter("message");
        if (message != null) {
            req.setAttribute("message", message);
        }
        Long idUser = Long.parseLong(req.getParameter("id"));
        // Find user from database with id
        req.setAttribute("profileuser", userService.findOne(idUser));
        req.getRequestDispatcher("/views/user/profile.jsp").forward(req,resp);
    }
}
