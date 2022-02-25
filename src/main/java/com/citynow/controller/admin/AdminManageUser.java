package com.citynow.controller.admin;


import com.citynow.service.IUserService;
import com.citynow.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Management post under control ADMIN
 * @author VuKhanh
 */
@WebServlet(urlPatterns = {"/admin-manage-user"})
public class AdminManageUser extends HttpServlet {

    IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set default pagination
        int limit = 5;
        int page = 1;

        // Get total user -> Count total page
        int totalPage = (int) Math.ceil((double) userService.count() / limit);
        try {
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }
        } catch (Exception e) {
        }

        // Set attribute
        req.setAttribute("users", userService.findAll(page, limit));
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("currentPage", page);

        req.getRequestDispatcher("/views/admin/manageUser.jsp").forward(req,resp);
    }
}
