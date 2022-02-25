package com.citynow.model;

import java.util.Date;

/**
 * Represents a comment of user
 * @author VuKhanh
 */
public class CommentModel {

    private Long id;

    /**
     * Represent who create comment
     */
    private Long user_id;
    private UserModel user;

    /**
     * Represent post where comment is created
     */
    private Long post_id;
    private PostModel post;

    /**
     * Represent content of comment
     */
    private String content;

    /**
     * Represents a date when comment is created
     */
    private Date dateCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public PostModel getPost() {
        return post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }
}
