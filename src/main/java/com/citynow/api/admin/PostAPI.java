package com.citynow.api.admin;

import com.citynow.model.PostModel;
import com.citynow.model.UserModel;
import com.citynow.service.IPostService;
import com.citynow.service.IUserService;
import com.citynow.service.impl.PostServiceImpl;
import com.citynow.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Represent API post under control of ADMIN
 * @author VuKhanh
 */
@WebServlet(urlPatterns = {"/admin-api-post"})
public class PostAPI extends HttpServlet {

    IPostService postService = new PostServiceImpl();

    IUserService userService = new UserServiceImpl();

    /**
     * Change status of post
     * Admin change status of post (Pending -> Approve)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        int status = Integer.parseInt(req.getParameter("status"));

        // Find post from database
        PostModel postModel = postService.findOne(id);
        postModel.setStatus(status);
        // Save status of post to database
        postService.update(postModel);

        // Find user who created post
        Long userId = postModel.getUser().getId();
        UserModel userModel = userService.findOne(userId);
        // Increase quantity post of user created post
        Long quantityPostPlus = userModel.getQuantityPost() + 1L;
        userModel.setQuantityPost(quantityPostPlus);
        // Save change user to database
        userService.update(userModel);
    }
}
