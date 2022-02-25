package com.citynow.controller.user;

import com.citynow.constant.Constant;
import com.citynow.model.UserModel;
import com.citynow.service.IUserService;
import com.citynow.service.impl.UserServiceImpl;
import com.citynow.utils.SessionUtil;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller of login age
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get message to alert for user
        String message = req.getParameter("message");
        if (message != null) {
            req.setAttribute("message", message);
        }

        req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        // Get info account from ajax
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Find user from database
        UserModel model = userService.fineOneByUsername(username);

        // Check password wrong
        if (model != null && !BCrypt.checkpw(password, model.getPassword())) {
            model = null;
        }

        // Check status account not was banned
        if (model != null && model.getStatus() != Constant.USER_BAN_STATUS) {
            SessionUtil.getInstance().putValue(req, "LOGIN", model);
            if (model.getRole().getCode().equals("USER")) {
                resp.sendRedirect(req.getContextPath() + "/home");
            } else if (model.getRole().getCode().equals("ADMIN")) {
                resp.sendRedirect(req.getContextPath() + "/admin-manage-post");
            }
        } else if (model != null && model.getStatus() == Constant.USER_BAN_STATUS) {
            // If status account is banned -> Alert to user
            req.setAttribute("message", "account-banned");
            req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
        } else {
            // Alert to user account not exist
            req.setAttribute("message", "wrong-account");
            req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
        }
    }
}
