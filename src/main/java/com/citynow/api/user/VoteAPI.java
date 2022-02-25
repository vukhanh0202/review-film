package com.citynow.api.user;

import com.citynow.constant.Constant;
import com.citynow.model.PostModel;
import com.citynow.model.UserModel;
import com.citynow.model.VoteModel;
import com.citynow.service.IPostService;
import com.citynow.service.IUserService;
import com.citynow.service.IVoteService;
import com.citynow.service.impl.PostServiceImpl;
import com.citynow.service.impl.UserServiceImpl;
import com.citynow.service.impl.VoteServiceImpl;
import com.citynow.utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Represent API Vote
 * @author VuKhanh
 */
@WebServlet(urlPatterns = {"/api-vote"})
public class VoteAPI extends HttpServlet {

    IVoteService voteService = new VoteServiceImpl();

    IPostService postService = new PostServiceImpl();

    IUserService userService = new UserServiceImpl();

    /**
     * Create Like or dislike post
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get user was login by Session
        UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "LOGIN");
        Long idPost = Long.parseLong(req.getParameter("idPost"));
        // Find info post from database
        PostModel postModel = postService.findOne(idPost);
        int statusVote = Integer.parseInt(req.getParameter("statusVote"));

        // Change vote
        if (postModel.getStatus() != Constant.POST_PENDING_STATUS)
        {
            VoteModel voteModel = voteService.findOneByUserIdAndPostId(model.getId(), idPost);
            if (voteModel == null) {
                voteModel = new VoteModel();
                voteModel.setPost(postModel);
                voteModel.setUser(model);
                voteModel.setActionVote(statusVote);
                voteService.save(voteModel);
            } else {
                voteModel.setActionVote(statusVote);
                voteService.update(voteModel);
            }

            // Change total vote of post
            Long totalVoteLike = voteService.countTotalVoteByPostIdAndAction(idPost, Constant.VOTE_LIKE);
            Long totalVoteDislike = voteService.countTotalVoteByPostIdAndAction(idPost, Constant.VOTE_DISLIKE);
            postModel.setUpvote(totalVoteLike);
            postModel.setDownvote(totalVoteDislike);
            postService.update(postModel);

            // Change user vote (total vote of user write this post)
            UserModel author = userService.findOne(postModel.getUser().getId());
            author.setQuantityUpvote(userService.countTotalLikedByUserId(author.getId()));
            userService.update(author);
        }

    }

    /**
     * Delete vote of a post
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "LOGIN");
        Long idPost = Long.parseLong(req.getParameter("idpost"));
        PostModel postModel = postService.findOne(idPost);


        // Change vote
        VoteModel voteModel = voteService.findOneByUserIdAndPostId(model.getId(), idPost);
        voteService.delete(voteModel.getId());

        // Change post (total vote)
        Long totalVoteLike = voteService.countTotalVoteByPostIdAndAction(idPost, Constant.VOTE_LIKE);
        Long totalVoteDislike = voteService.countTotalVoteByPostIdAndAction(idPost, Constant.VOTE_DISLIKE);
        postModel.setUpvote(totalVoteLike);
        postModel.setDownvote(totalVoteDislike);
        postService.update(postModel);

        // Change user vote (total vote of user write this post)
        UserModel author = userService.findOne(postModel.getUser().getId());
        author.setQuantityUpvote(userService.countTotalLikedByUserId(author.getId()));
        userService.update(author);
    }
}
