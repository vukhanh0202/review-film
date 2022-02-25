package com.citynow.service;

import com.citynow.model.UserModel;

import java.util.List;

/**
 * Service handle user
 * @author VuKhanh
 */
public interface IUserService {

    /**
     * Service save user model
     * @param userModel
     * @return user model
     */
    UserModel save(UserModel userModel);

    /**
     * Service update user model
     * @param userModel
     * @return user model
     */
    UserModel update(UserModel userModel);

    /**
     * Service find user by username
     * @param username
     * @return user model
     */
    UserModel fineOneByUsername(String username);

    /**
     * Service find user by ID
     * @param id
     * @return user model
     */
    UserModel findOne(Long id);

    /**
     * Service find all user model
     * @return List user model
     */
    List<UserModel> findAll();

    /**
     * Service find all user model with pagination
     * @param page
     * @param limit
     * @return list user model
     */
    List<UserModel> findAll(int page, int limit);

    /**
     * Service find top user have quantity like highest
     * @param top
     * @return list user model
     */
    List<UserModel> findTopByQuantityLike(int top);

    /**
     * Service count total like of user
     * @param userId
     * @return total like
     */
    Long countTotalLikedByUserId(Long userId);

    /**
     * Service count all user
     * @return total user
     */
    int count();

    /**
     * Service find one by user name
     * @return user model
     */
    UserModel findOneByUsername(String username);
}
