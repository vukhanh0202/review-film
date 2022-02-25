package com.citynow.api.user;

import com.citynow.constant.Constant;
import com.citynow.model.PostModel;
import com.citynow.model.UserModel;
import com.citynow.service.IPostService;
import com.citynow.service.IUserService;
import com.citynow.service.impl.PostServiceImpl;
import com.citynow.service.impl.UserServiceImpl;
import com.citynow.utils.ConvertUtil;
import com.citynow.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Represent API post
 * @author VuKhanh
 */
@WebServlet(urlPatterns = {"/api-post"})
public class PostAPI extends HttpServlet {

    IPostService postService = new PostServiceImpl();

    IUserService userService = new UserServiceImpl();

    /**
     * Create a new post
     * @param req
     * @param resp
     * @return a post after save to database
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        // Get data json form ajax request -> mapping to post model
        PostModel postModel = mapper.readValue(ConvertUtil.convertJsonToString(req.getReader()), PostModel.class);

        // Set default for new post: status = pending; 0 Like; 0 Dislike; date created = now
        postModel.setStatus(Constant.POST_PENDING_STATUS);
        postModel.setDownvote(0L);
        postModel.setUpvote(0L);
        postModel.setDateModified(new Date(System.currentTimeMillis()));
        try {
            // Set user who create a post
            postModel.setUser((UserModel) SessionUtil.getInstance().getValue(req, "LOGIN"));
        } catch (Exception e) {
            return;
        }

        // Check user was not blocked
        if (postModel.getUser().getStatus() != Constant.USER_BLOCK_STATUS){
            postModel = postService.save(postModel);
            mapper.writeValue(resp.getOutputStream(), postModel);
        }
    }

    /**
     * Update post
     * @param req
     * @param resp
     * @return a post after update
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        // Get data json form ajax request -> mapping to post model
        PostModel postModel = mapper.readValue(ConvertUtil.convertJsonToString(req.getReader()), PostModel.class);
        postModel.setDateModified(new Date(System.currentTimeMillis()));

        // Find data old post from database
        PostModel oldPost = postService.findOne(postModel.getId());
        // Set old value for post which was updated
        postModel.setUpvote(oldPost.getUpvote());
        postModel.setDownvote(oldPost.getDownvote());
        postModel.setStatus(oldPost.getStatus());
        postModel.setUser(oldPost.getUser());

        // Save post to database
        postModel = postService.update(postModel);
        mapper.writeValue(resp.getOutputStream(), postModel);
    }

    /**
     * Delete Post
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        // Get json from ajax request
        String json = ConvertUtil.convertJsonToString(req.getReader());
        JSONObject dataJson = new JSONObject(json);
        JSONArray idsJson = dataJson.getJSONArray("ids");

        // Get id list post need delete
        Long[] ids = new Long[idsJson.length()];
        Long[] idUsers = new Long[idsJson.length()];
        for (int i = 0; i <idsJson.length(); i++) {
            ids[i] = idsJson.getLong(i);
            idUsers[i] = postService.findOne(ids[i]).getUser().getId();
        }
        try{
            // Delete post from database
            postService.delete(ids);
        }catch (Exception e){}
        // Change user vote (total vote of user write this post)
        for (Long i:
                idUsers) {
            Long idUser = postService.findOne(i).getUser().getId();
            UserModel author = userService.findOne(idUser);
            author.setQuantityUpvote(userService.countTotalLikedByUserId(author.getId()));
            userService.update(author);
        }

        mapper.writeValue(resp.getOutputStream(), "{}");
    }
}
