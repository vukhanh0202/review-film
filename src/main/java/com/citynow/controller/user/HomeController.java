package com.citynow.controller.user;

import com.citynow.constant.Constant;
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
import java.util.List;

/**
 * Controller hompage
 * @author VuKhanh
 */
@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    IPostService postService = new PostServiceImpl();

    IUserService userService = new UserServiceImpl();

    /**
     * Return home page
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String message = req.getParameter("message");
        if (message != null) {
            req.setAttribute("message", message);
        }

        // Pagination
        int page = 1;
        int limit = 5;
        String search = "";
        try {
            // Get page value from parameter url
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }
            // Get limit value from parameter url
            if (req.getParameter("limit") != null) {
                limit = Integer.parseInt(req.getParameter("limit"));
            }
        } catch (Exception e) {
        }

        // Get search value from parameter url
        try {
            if (req.getParameter("search") != null) {
                search = req.getParameter("search");
            }

        } catch (Exception e) {
        }
        int totalPage;
        // Get sort value from parameter url
        String sort = req.getParameter("sort");
        if (sort == null) {
            sort = "date-desc";
        }

        // Get list post by search text, page, limit and sort
        List<PostModel> listResult = postService.findAllByStatus(Constant.POST_APPROVE_STATUS, search, page, limit, sort);
        // Get top 3 user who have quantity like highest
        List<UserModel> TopReviewers = userService.findTopByQuantityLike(3);
        // Get top 3 post have quantity like highest
        List<PostModel> TopPosts = postService.findAllTopByStatus(3, Constant.POST_APPROVE_STATUS);
        totalPage = (int) Math.ceil((double) postService.countByStatusAndSearch(Constant.POST_APPROVE_STATUS, search) / limit);

        // Set list result
        req.setAttribute("post", listResult);
        req.setAttribute("topReviewers", TopReviewers);
        req.setAttribute("topPosts", TopPosts);

        // Set value pagination
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("currentPage", page);

        //Set value sort and search
        req.setAttribute("sort", sort);
        req.setAttribute("search", search);

        req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
    }
}
