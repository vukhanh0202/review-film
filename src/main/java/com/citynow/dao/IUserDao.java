package com.citynow.dao;

import com.citynow.model.UserModel;

import java.util.List;

/**
 * Use handle user data from database
 * @author VuKhanh
 */
public interface IUserDao {

    /**
     * Find User by username from database
     * @param userName
     * @return user model
     */
    UserModel findByUserName(String userName);

    /**
     * Find User by ID user from database
     * @param id
     * @return user model
     */
    UserModel findOne(Long id);

    /**
     * Save user to database
     * @param userModel
     * @return ID of user
     */
    Long save(UserModel userModel);

    /**
     * Update user to database replace old user
     * @param userModel
     */
    void update(UserModel userModel);

    /**
     * Find all user from database
     * @return list user model
     */
    List<UserModel> findAll();

    /**
     * Find all user with pagination from database
     * @param page
     * @param limit
     * @return list user model with pagination
     */
    List<UserModel> findAll(int page, int limit);

    /**
     * Find all user have total post have liked highest
     * @param top
     * @return List user model
     */
    List<UserModel> findTopByQuantityLike(int top);

    /**
     * Count total like of user from database
     * @param userId
     * @return total like of user
     */
    Long countTotalLikedByUserId(Long userId);

    /**
     * Count total user from database
     * @return total user
     */
    int count();
}
