package com.citynow.service;

import com.citynow.model.VoteModel;

import java.util.List;

/**
 * Service handle vote
 * @author VuKhanh
 */
public interface IVoteService {

    /**
     * Service help save vote model
     * @param voteModel
     * @return vote model
     */
    VoteModel save(VoteModel voteModel);

    /**
     * Service help update vote model
     * @param voteModel
     * @return vote model
     */
    VoteModel update(VoteModel voteModel);

    /**
     * Service find all vote model by user ID
     * @param userId
     * @return List vote model
     */
    List<VoteModel> findAllByUserId(Long userId);

    /**
     * Service find all vote model by user ID have pagination
     * @param userId
     * @param page
     * @param limit
     * @return list vote model
     */
    List<VoteModel> findAllByUserId(Long userId, int page, int limit);

    /**
     * Service find vote model by user ID and post ID
     * @param userId
     * @param postId
     * @return vote model
     */
    VoteModel findOneByUserIdAndPostId(Long userId, Long postId);

    /**
     * Service count total vote by post ID and action(like or dislike)
     * @param postId
     * @param action
     * @return total vote
     */
    Long countTotalVoteByPostIdAndAction(Long postId, int action);

    /**
     * Service delete vote
     * @param id
     */
    void delete(Long id);

    /**
     * @param userId
     * @return
     */
    int countByUserId(Long userId);
}
