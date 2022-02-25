package com.citynow.dao.impl;

import com.citynow.dao.IUserDao;
import com.citynow.mapper.impl.UserMapper;
import com.citynow.model.UserModel;
import com.citynow.utils.ConvertUtil;

import java.util.List;

public class UserDaoImpl extends AbstractDao<UserModel> implements IUserDao {

    /**
     * Find User by username from database
     * @param userName
     * @return user model
     */
    @Override
    public UserModel findByUserName(String userName) {
        StringBuilder sql = new StringBuilder("SELECT * FROM user ");
        sql.append(" INNER JOIN role ON role.id = user.role_id");
        sql.append(" WHERE username = ?");
        List<UserModel> users = query(sql.toString(), new UserMapper(), userName);
        return users.isEmpty() ? null : users.get(0);
    }

    /**
     * Find User by ID user from database
     * @param id
     * @return user model
     */
    @Override
    public UserModel findOne(Long id) {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM USER, ROLE ");
        sql.append(" WHERE USER.role_id = role.id AND user.id = ?");
        List<UserModel> users = query(sql.toString(), new UserMapper(), id);
        return users.isEmpty() ? null : users.get(0);
    }

    /**
     * Save user to database
     * @param userModel
     * @return ID of user
     */
    @Override
    public Long save(UserModel userModel) {
        if (userModel.getAvatar() == null){
            userModel.setAvatar("");
        }
        StringBuilder sql = new StringBuilder("INSERT INTO user (username, password,");
        sql.append(" fullname, email, dateofbirth, phone, quantitypost, quantityupvote, status, role_id)");
        sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
        return insert(sql.toString(), userModel.getUsername(),
                userModel.getPassword(), userModel.getFullname(), userModel.getEmail(),
                userModel.getDateOfBirth(),
                userModel.getPhone(), userModel.getQuantityPost(), userModel.getQuantityUpvote(), userModel.getStatus(),
                userModel.getRole().getId());
    }

    /**
     * Update user to database replace old user
     * @param userModel
     */
    @Override
    public void update(UserModel userModel) {
        if (userModel.getAvatar() == null){
            userModel.setAvatar("");
        }
        StringBuilder sql = new StringBuilder("UPDATE user SET username = ?, password = ?,");
        sql.append(" fullname = ?, email = ?, avatar = ?,");
        sql.append(" dateofbirth = ?, phone = ?, quantitypost = ? , quantityupvote = ?  , status = ? , role_id = ? WHERE id = ?");
        update(sql.toString(), userModel.getUsername(), userModel.getPassword(), userModel.getFullname(), userModel.getEmail(),
                userModel.getAvatar(), userModel.getDateOfBirth(),
                userModel.getPhone(), userModel.getQuantityPost(), userModel.getQuantityUpvote(), userModel.getStatus(),
                userModel.getRole().getId(), userModel.getId());
    }

    /**
     * Find all user from database
     * @return list user model
     */
    @Override
    public List<UserModel> findAll() {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM USER WHERE role_id = 2");
        return query(sql.toString(), new UserMapper());
    }

    /**
     * Find all user with pagination from database
     * @param page
     * @param limit
     * @return list user model with pagination
     */
    @Override
    public List<UserModel> findAll(int page, int limit) {
        StringBuilder sql = new StringBuilder(" SELECT * ");
        sql.append(" FROM USER WHERE role_id = 2 ");
        sql.append(" LIMIT ? OFFSET ?");

        // offset = (page -1 ) * limit (MySQL count from 0)
        int offset = (page - 1) * limit;
        return query(sql.toString(), new UserMapper(), limit, offset);
    }

    /**
     * Find all user have total post have liked highest
     * @param top
     * @return List user model
     */
    @Override
    public List<UserModel> findTopByQuantityLike(int top) {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM USER ");
        sql.append(" ORDER BY user.quantityupvote DESC ");
        sql.append(" LIMIT ? ");
        return query(sql.toString(), new UserMapper(), top);
    }

    /**
     * Count total like of user from database
     * @param userId
     * @return total like of user
     */
    @Override
    public Long countTotalLikedByUserId(Long userId) {
        StringBuilder sql = new StringBuilder("SELECT count(*) ");
        sql.append(" FROM POST, VOTE ");
        sql.append(" WHERE vote.post_id = post.id and actionvote = 1 and post.user_id = ? ");
        return Long.valueOf(count(sql.toString(), userId));
    }

    /**
     * Count total user from database
     * @return total user
     */
    @Override
    public int count() {
        StringBuilder sql = new StringBuilder("SELECT count(*) ");
        sql.append(" FROM USER ");
        return count(sql.toString());
    }
}
