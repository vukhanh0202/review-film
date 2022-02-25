package com.citynow.service;

import com.citynow.model.CommentModel;

import java.util.List;

/**
 * Service handle comment
 *
 * @author VuKhanh
 */
public interface ICommentService {

    /**
     * Service find all comment
     * @return list comment model
     */
    List<CommentModel> findAll();

    /**
     * Service find all comment by user ID
     * @param userId
     * @return list comment model
     */
    List<CommentModel> findAllByUserId(Long userId);

    /**
     * Service find all comment by post ID
     * @param postId
     * @return list comment model
     */
    List<CommentModel> findAllByPostId(Long postId);

    /**
     * Service find all comment by post ID wth pagination
     * @param postId
     * @param page
     * @param limit
     * @return list comment model
     */
    List<CommentModel> findAllByPostId(Long postId, int page, int limit);

    /**
     * Service save comment
     * @param commentModel
     * @return comment model
     */
    CommentModel save(CommentModel commentModel);

    /**
     * Service count total comment by post ID
     * @param postId
     * @return total comment
     */
    int countAllByPostId(Long postId);

    /**
     * Service delete comment
     * @param id
     */
    void deleteByPostId(Long id);
}
