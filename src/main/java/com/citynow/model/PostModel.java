package com.citynow.model;

import java.sql.Date;

/**
 * Represents a post review film
 * @author VuKhanh
 */
public class PostModel {

    private Long id;

    /**
     * Represent user who create this post
     */
    private UserModel user;

    /**
     * Represent date when post is created
     */
    private Date dateModified;

    /**
     * Represent quantity Up Vote or Like of post
     */
    private Long upvote;

    /**
     * Represent quantity Down Vote or Dislike of post
     */
    private Long downvote;

    /**
     * Represent title of post
     */
    private String title;

    /**
     * Represent the name of film which this post review
     */
    private String filmName;

    /**
     * Represent rate of this film (which this post review)
     */
    private int postRate;

    /**
     * Represent content of post
     */
    private String postReview;

    /**
     * Represent status of post (Approve or Pending,..)
     */
    private int status;

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

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Long getUpvote() {
        return upvote;
    }

    public void setUpvote(Long upvote) {
        this.upvote = upvote;
    }

    public Long getDownvote() {
        return downvote;
    }

    public void setDownvote(Long downvote) {
        this.downvote = downvote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public int getPostRate() {
        return postRate;
    }

    public void setPostRate(int postRate) {
        this.postRate = postRate;
    }

    public String getPostReview() {
        return postReview;
    }

    public void setPostReview(String postReview) {
        this.postReview = postReview;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
