package com.citynow.dao;

import com.citynow.model.CommentModel;

import java.util.List;

/**
 * Use handle comment data from database
 * @author VuKhanh
 */
public interface ICommentDao {

    /**
     * Save comment to database
     * @param commentModel
     * @return ID of comment model
     */
    Long save(CommentModel commentModel);

    /**
     * Find comment with ID from database
     * @param id
     * @return  comment model
     */
    CommentModel findOne(Long id);

    /**
     * Find all comment from database
     * @return List all comment model
     */
    List<CommentModel> findAll();

    /**
     * Find comment with ID user, who created this comment from database
     * @param userId
     * @return List comment which user created
     */
    List<CommentModel> findAllByUserId(Long userId);

    /**
     * Find comment of post with post ID from database
     * @param postId
     * @return List comment of post
     */
    List<CommentModel> findAllByPostId(Long postId);

    /**
     * Find comment of post with post ID and pagination(page, limit) from database
     * @param postId
     * @param page
     * @param limit
     * @return List comment of post with page and limit of 1 page
     */
    List<CommentModel> findAllByPostId(Long postId, int page, int limit);

    /**
     * Count quantity comment of 1 post with post ID from database
     * @param postId
     * @return quantity comment(int)
     */
    int countAllByPostId(Long postId);

    /**
     * Delete comment from database
     * @param postId
     */
    void deleteByPostId(Long postId);
}
