package com.citynow.service;

import com.citynow.model.PostModel;

import java.util.List;

/**
 * Service handle post
 * @author VuKhanh
 */
public interface IPostService {

    /**
     * Service Find all post
     * @return list post model
     */
    List<PostModel> findAll();

    /**
     * Service Find all post with pagination
     * @param page
     * @param limit
     * @return list post model
     */
    List<PostModel> findAll(int page, int limit);

    /**
     * Service Find all post by status
     * @param status
     * @return list post model
     */
    List<PostModel> findAllByStatus(int status);

    /**
     * Service Find all post by status with pagination
     * @param status
     * @param search
     * @param page
     * @param limit
     * @param sort
     * @return list post model
     */
    List<PostModel> findAllByStatus(int status, String search, int page, int limit, String sort);

    /**
     * Service Find all post by ID user
     * @param userId
     * @return list post model
     */
    List<PostModel> findAllByUserId(Long userId);

    /**
     * Service Find all post by ID user with pagination
     * @param userId
     * @param page
     * @param limit
     * @return list post model
     */
    List<PostModel> findAllByUserId(Long userId, int page, int limit);

    /**
     * Service Find all post by id user and status
     * @param userId
     * @param postStatus
     * @return
     */
    List<PostModel> findAllByUserIdAndStatus(Long userId, int postStatus);

    /**
     * Service Find top post with status which have like highest
     * @param top
     * @param postStatus
     * @return list post model
     */
    List<PostModel> findAllTopByStatus(int top, int postStatus);

    /**
     * Service Find post model with ID
     * @param id
     * @return post model
     */
    PostModel findOne(Long id);

    /**
     * Service save post
     * @param postModel
     * @return post model
     */
    PostModel save(PostModel postModel);

    /**
     * Service update post
     * @param postModel
     * @return post model
     */
    PostModel update(PostModel postModel);

    /**
     * Service count total post
     * @return total post
     */
    int count();

    /**
     * Service count total post by status
     * @param status
     * @return total post
     */
    int countByStatus(int status);

    /**
     * Service count total post by status and search text
     * @param status
     * @param search
     * @return total post
     */
    int countByStatusAndSearch(int status, String search);

    /**
     * Service count total post by user ID
     * @param userId
     * @return total post
     */
    int countByUserId(Long userId);

    /**
     * Service delete post
     * @param ids
     */
    void delete(Long[] ids);
}
