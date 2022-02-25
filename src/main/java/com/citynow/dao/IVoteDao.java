package com.citynow.dao;

import com.citynow.model.VoteModel;

import java.util.List;

public interface IVoteDao {

    /**
     * Save vote model to database
     * @param voteModel
     * @return ID vote
     */
    Long save(VoteModel voteModel);

    /**
     * Update vote model replace old model from database
     * @param voteModel
     */
    void update(VoteModel voteModel);

    /**
     * Find all vote model of user from database
     * @param userId
     * @return List vote model
     */
    List<VoteModel> findAllByUserId(Long userId);

    /**
     * Find all vote model of user with pagination from database
     * @param userId
     * @param page
     * @param limit
     * @return List vote model
     */
    List<VoteModel> findAllByUserId(Long userId, int page, int limit);

    /**
     * find vote model with ID from database
     * @param id
     * @return vote model
     */
    VoteModel findOne(Long id);

    /**
     * Find vote model with user and post from database
     * @param userId
     * @param postId
     * @return vote model
     */
    VoteModel findOneByUserIdAndPostId(Long userId, Long postId);

    /**
     * Count total vote of post with action(like or dislike)
     * @param postId
     * @param action
     * @return total vote
     */
    Long countTotalVoteByPostIdAndAction(Long postId, int action);

    /**
     * Delete vote from database
     * @param id
     */
    void delete(Long id);

    /**
     * Delete vote by Post ID from database
     * @param postId
     */
    void deleteByPostId(Long postId);

    /**
     * Count total vote of user (like or dislike)
     * @param userId
     * @return total vote of user
     */
    int countByUserId(Long userId);
}
