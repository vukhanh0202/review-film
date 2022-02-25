package com.citynow.api.user;

import com.citynow.constant.Constant;
import com.citynow.model.CommentModel;
import com.citynow.model.UserModel;
import com.citynow.service.ICommentService;
import com.citynow.service.IPostService;
import com.citynow.service.IUserService;
import com.citynow.service.impl.CommentServiceImpl;
import com.citynow.service.impl.PostServiceImpl;
import com.citynow.service.impl.UserServiceImpl;
import com.citynow.utils.ConvertUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Represent API comment
 * @author VuKhanh
 */
@WebServlet(urlPatterns = {"/api-comment"})
public class CommentAPI extends HttpServlet {

    ICommentService commentService = new CommentServiceImpl();

    IUserService userService = new UserServiceImpl();

    IPostService postService = new PostServiceImpl();

    /**
     * Get list comment of post by post_id
     * list get by pagination
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Get json from ajax request
        String json = ConvertUtil.convertJsonToString(req.getReader());
        JSONObject dataJson = new JSONObject(json);

        int page = dataJson.getInt("page");
        int limit = dataJson.getInt("limit");
        Long postId = dataJson.getLong("postId");


        int totalPage;
        // Get total comment of post -> Count total page
        totalPage = (int) Math.ceil((double) commentService.countAllByPostId(postId) / limit);

        req.setAttribute("totalPage", totalPage);
        req.setAttribute("currentPage", page);

        mapper.writeValue(resp.getOutputStream(), "{}" );
    }

    /**
     * Create comment to one post
     * @param req
     * @param resp
     * @return Comment after save to database
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        // Get data json form ajax request -> mapping to comment model
        CommentModel commentModel = mapper.readValue(ConvertUtil.convertJsonToString(req.getReader()), CommentModel.class);

        // Get user who create comment from database
        UserModel userModel = userService.findOne(commentModel.getUser_id());
        int statusAccount = userModel.getStatus();

        // Check comment not empty and user was not blocked
        if (commentModel.getContent() != "" && statusAccount != Constant.USER_BLOCK_STATUS) {
            // Set date when created comment
            commentModel.setDateCreated(new Date(System.currentTimeMillis()));
            // Set user who comment
            commentModel.setUser(userModel);
            commentModel.setPost(postService.findOne(commentModel.getPost_id()));
            // Save comment to database
            if (commentModel.getPost().getStatus() != Constant.POST_PENDING_STATUS)
            {
                commentModel = commentService.save(commentModel);
                // Response model json
                mapper.writeValue(resp.getOutputStream(), commentModel);
            }else{
                // Comment in post pending
                mapper.writeValue(resp.getOutputStream(), "{}");
            }

        }
    }
}
