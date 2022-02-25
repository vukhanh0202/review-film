package com.citynow.controller.user;

import com.citynow.model.PostModel;
import com.citynow.service.IPostService;
import com.citynow.service.impl.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller page create post
 * @author VuKhanh
 */
@WebServlet(urlPatterns = {"/post"})
public class CreatePostController extends HttpServlet {

    IPostService postService = new PostServiceImpl();

    /**
     * Return page create new post
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        if (message != null) {
            req.setAttribute("message", message);
        }
        Long id = -1L;
        PostModel postModel = new PostModel();
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (Exception e) {
        }
        if (id != -1) {
            postModel = postService.findOne(id);
        }
        req.setAttribute("post", postModel);
        req.getRequestDispatcher("/views/user/post.jsp").forward(req, resp);
    }
}
