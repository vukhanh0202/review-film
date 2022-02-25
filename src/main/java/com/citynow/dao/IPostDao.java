package com.citynow.dao;

import com.citynow.model.PostModel;

import java.util.List;

/**
 * Use handle post data from database
 * @author VuKhanh
 */
public interface IPostDao {

    /**
     * Find post with ID post from database
     * @param id
     * @return  post model
     */
    PostModel findOne(Long id);

    /**
     * Save post to database
     * @param postModel
     * @return ID of post model
     */
    Long save(PostModel postModel);

    /**
     * Update post to database replace old post
     * @param postModel
     */
    void update(PostModel postModel);

    /**
     * Delete post from database
     * @param id
     */
    void delete(Long id);

    /**
     * Find all post from database
     * @return List all post model
     */
    List<PostModel> findAll();

    /**
     * Find all post with pagination(page, limit) from database
     * @param page
     * @param limit
     * @return List post with page and limit of 1 page
     */
    List<PostModel> findAll(int page, int limit);

    /**
     * Find list posts with status from database
     * @param status
     * @return List post with status
     */
    List<PostModel> findAllByStatus(int status);

    /**
     * Find all posts with status and pagination(page, limit) from database
     * @param status
     * @param search
     * @param page
     * @param limit
     * @param sort
     * @return List post with status and page, limit of 1 page
     */
    List<PostModel> findAllByStatus(int status,String search, int page, int limit, String sort);

    /**
     * Find list posts with user who created this post from database
     * @param userId
     * @return list posts which user created
     */
    List<PostModel> findAllByUserId(Long userId);

    /**
     * Find list posts with user who created this post and pagination(page, limit) from database
     * @param userId
     * @param page
     * @param limit
     * @return List posts which user created with page and limit of 1 page
     */
    List<PostModel> findAllByUserId(Long userId, int page, int limit);

    /**
     * Find list posts with user who created this post and status of that post
     * @param userId
     * @param postStatus
     * @return list posts which user created and status of this post
     */
    List<PostModel> findAllByUserIdAndStatus(Long userId, int postStatus);

    /**
     * Find list post top like highest with status
     * @param top
     * @param status
     * @return List posts top like highest with status
     */
    List<PostModel> findAllTopByStatus(int top, int status);

    /**
     * Count quantity post with post status from database
     * @param status
     * @return quantity post with status
     */
    int countByStatus(int status);

    /**
     * Count quantity post with post status and search text from database
     * @param status
     * @param search
     * @return quantity post with status and search text
     */
    int countByStatusAndSearch(int status,String search);

    /**
     * Count quantity post with user who created this from database
     * @param userId
     * @return quantity post with user who created this
     */
    int countByUserId(Long userId);

    /**
     * Count quantity all post form database
     * @return quantity all post
     */
    int count();
}
