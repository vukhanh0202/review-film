package com.citynow.controller.user;

import com.citynow.model.UserModel;
import com.citynow.service.IVoteService;
import com.citynow.service.impl.VoteServiceImpl;
import com.citynow.utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller list post which user interacted
 * @author VuKhanh
 */
@WebServlet(urlPatterns = {"/list-interacted"})
public class ListInteractedController extends HttpServlet {

    IVoteService voteService = new VoteServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get user who was login
        UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "LOGIN");

        // Pagination
        int limit = 5;

        int page = 1;

        // Count total post user interacted -> total Page
        int totalPage = (int) Math.ceil((double) voteService.countByUserId(model.getId()) / limit);
        try {
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }
        } catch (Exception e) {
        }

        // Find all post which user interacted
        req.setAttribute("postsinteract", voteService.findAllByUserId(model.getId(), page, limit));
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("currentPage", page);

        req.getRequestDispatcher("/views/user/listInteracted.jsp").forward(req, resp);
    }
}
