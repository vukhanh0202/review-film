package com.citynow.model;


/**
 * Represents vote of user for a post
 * @author VuKhanh
 */
public class VoteModel {

    private Long id;

    /**
     * Represent user vote
     */
    private UserModel user;

    /**
     * Represent post was voted
     */
    private PostModel post;
    private int actionVote;

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

    public PostModel getPost() {
        return post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }

    public int getActionVote() {
        return actionVote;
    }

    public void setActionVote(int actionVote) {
        this.actionVote = actionVote;
    }
}
