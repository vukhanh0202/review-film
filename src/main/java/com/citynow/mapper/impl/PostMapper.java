package com.citynow.mapper.impl;

import com.citynow.mapper.IMapper;
import com.citynow.model.PostModel;
import com.citynow.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Map data to post model
 * @author VuKhanh
 */
public class PostMapper implements IMapper<PostModel> {

    /**
     * Map from resultSet to post model
     * @param rs
     * @return post model
     */
    public PostModel map(ResultSet rs){
        PostModel postModel = new PostModel();
        try {
            postModel.setId(rs.getLong("id"));
            postModel.setDateModified(rs.getDate("datemodified"));
            postModel.setUpvote(rs.getLong("upvote"));
            postModel.setDownvote(rs.getLong("downvote"));
            postModel.setTitle(rs.getString("title"));
            postModel.setFilmName(rs.getString("filmname"));
            postModel.setPostRate(rs.getInt("postrate"));
            postModel.setPostReview(rs.getString("postreview"));
            postModel.setStatus(rs.getInt("status"));

            try {
                UserModel user = new UserModel();
                user.setId(rs.getLong("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setQuantityPost(rs.getLong("quantitypost"));
                user.setQuantityUpvote(rs.getLong("quantityupvote"));
                user.setAvatar(rs.getString("avatar"));
                postModel.setUser(user);
            } catch (Exception e) {
            }
            return postModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
