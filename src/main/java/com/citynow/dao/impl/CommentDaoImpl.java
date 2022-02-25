package com.citynow.dao.impl;

import com.citynow.dao.ICommentDao;
import com.citynow.mapper.impl.CommetMapper;
import com.citynow.model.CommentModel;

import java.util.List;

public class CommentDaoImpl extends AbstractDao<CommentModel> implements ICommentDao {

    /**
     * Save comment to database
     *
     * @param commentModel
     * @return ID of comment model
     */
    @Override
    public Long save(CommentModel commentModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO comment (user_id, post_id , content, datecreated) ");
        sql.append(" VALUES(?, ?, ?, ?)");
        return insert(sql.toString(), commentModel.getUser_id(), commentModel.getPost_id(),
                commentModel.getContent(), commentModel.getDateCreated());
    }

    /**
     * Find comment with ID from database
     *
     * @param id
     * @return comment model
     */
    @Override
    public CommentModel findOne(Long id) {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM COMMENT WHERE id = ?");
        List<CommentModel> commentModels = query(sql.toString(), new CommetMapper(), id);
        return commentModels.isEmpty() ? null : commentModels.get(0);
    }

    /**
     * Find all comment from database
     * @return List all comment model
     */
    @Override
    public List<CommentModel> findAll() {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM COMMENT");
        return query(sql.toString(), new CommetMapper());
    }

    /**
     * Find comment with ID user, who created this comment from database
     * @param userId
     * @return List comment which user created
     */
    @Override
    public List<CommentModel> findAllByUserId(Long userId) {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM COMMENT, POST, USER ");
        sql.append("  WHERE COMMENT.post_id = POST.id and COMMENT.user_id = USER.id and user_id = ?");
        return query(sql.toString(), new CommetMapper(), userId);
    }

    /**
     * Find comment of post with post ID from database
     * @param postId
     * @return List comment of post
     */
    @Override
    public List<CommentModel> findAllByPostId(Long postId) {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM COMMENT, POST, USER ");
        sql.append(" WHERE COMMENT.post_id = POST.id and COMMENT.user_id = USER.id and post_id = ? ");
        sql.append(" ORDER BY comment.id DESC");
        return query(sql.toString(), new CommetMapper(), postId);
    }

    /**
     * Find comment of post with post ID and pagination(page, limit) from database
     * @param postId
     * @param page
     * @param limit
     * @return List comment of post with page and limit of 1 page
     */
    @Override
    public List<CommentModel> findAllByPostId(Long postId, int page, int limit) {
        StringBuilder sql = new StringBuilder(" SELECT * ");
        sql.append(" FROM COMMENT, POST, USER ");
        sql.append(" WHERE COMMENT.post_id = POST.id and COMMENT.user_id = USER.id and post_id = ? ");
        sql.append(" ORDER BY comment.id DESC");
        sql.append(" LIMIT ? OFFSET ?");

        // offset = (page -1 ) * limit (MySQL count from 0)
        int offset = (page - 1) * limit;
        return query(sql.toString(), new CommetMapper(), postId, limit, offset);
    }

    /**
     * Count quantity comment of 1 post with post ID from database
     * @param postId
     * @return quantity comment(int)
     */
    @Override
    public int countAllByPostId(Long postId) {
        StringBuilder sql = new StringBuilder("SELECT count(*) ");
        sql.append(" FROM comment ");
        sql.append(" WHere comment.post_id = ?");
        return count(sql.toString(), postId);
    }

    /**
     * Delete post from database
     * @param postId
     */
    @Override
    public void deleteByPostId(Long postId) {
        String sql = "DELETE FROM comment WHERE comment.post_id = ?";
        update(sql, postId);
    }
}
