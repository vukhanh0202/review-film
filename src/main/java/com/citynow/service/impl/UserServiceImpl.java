package com.citynow.service.impl;

import com.citynow.dao.IRoleDao;
import com.citynow.dao.IUserDao;
import com.citynow.dao.impl.RoleDaoImpl;
import com.citynow.dao.impl.UserDaoImpl;
import com.citynow.model.UserModel;
import com.citynow.service.IUserService;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Date;
import java.util.List;

public class UserServiceImpl implements IUserService {

    IUserDao userDao = new UserDaoImpl();

    /**
     * Service save user model
     * @param userModel
     * @return user model
     */
    @Override
    public UserModel save(UserModel userModel) {

        return userDao.findOne(userDao.save(userModel));
    }

    /**
     * Service update user model
     * @param userModel
     * @return user model
     */
    @Override
    public UserModel update(UserModel userModel) {
        userDao.update(userModel);
        return userDao.findOne(userModel.getId());
    }

    /**
     * Service find user by username
     * @param username
     * @return user model
     */
    @Override
    public UserModel fineOneByUsername(String username) {
        return userDao.findByUserName(username);
    }

    /**
     * Service find user by ID
     * @param id
     * @return user model
     */
    @Override
    public UserModel findOne(Long id) {
        return userDao.findOne(id);
    }

    /**
     * Service find all user model
     * @return List user model
     */
    @Override
    public List<UserModel> findAll() {
        return userDao.findAll();
    }

    /**
     * Service find all user model with pagination
     * @param page
     * @param limit
     * @return list user model
     */
    @Override
    public List<UserModel> findAll(int page, int limit) {
        return userDao.findAll(page, limit);
    }

    /**
     * Service find top user have quantity like highest
     * @param top
     * @return list user model
     */
    @Override
    public List<UserModel> findTopByQuantityLike(int top) {
        return userDao.findTopByQuantityLike(top);
    }

    /**
     * Service count total like of user
     * @param userId
     * @return total like
     */
    @Override
    public Long countTotalLikedByUserId(Long userId) {
        return userDao.countTotalLikedByUserId(userId);
    }

    /**
     * Service count all user
     * @return total user
     */
    @Override
    public int count() {
        return userDao.count();
    }

    @Override
    public UserModel findOneByUsername(String username) {
        return userDao.findByUserName(username);
    }
}
