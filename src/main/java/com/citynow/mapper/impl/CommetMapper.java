package com.citynow.mapper.impl;

import com.citynow.mapper.IMapper;
import com.citynow.model.CommentModel;
import com.citynow.model.PostModel;
import com.citynow.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Map data to comment model
 * @author VuKhanh
 */
public class CommetMapper implements IMapper<CommentModel> {

    /**
     * Map from resultSet to comment model
     * @param rs
     * @return comment model
     */
    public CommentModel map(ResultSet rs) {
        CommentModel commentModel = new CommentModel();
        try {
            commentModel.setId(rs.getLong("id"));
            commentModel.setContent(rs.getString("content"));
            commentModel.setDateCreated(rs.getDate("datecreated"));

            try {
                UserModel user = new UserModel();
                user.setId(rs.getLong("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setAvatar(rs.getString("avatar"));
                commentModel.setUser(user);
            } catch (Exception e) {
            }
            try {
                PostModel post = new PostModel();
                post.setId(rs.getLong("post_id"));
                post.setTitle(rs.getString("post.title"));
                commentModel.setPost(post);
            } catch (Exception e) {
            }
            return commentModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
