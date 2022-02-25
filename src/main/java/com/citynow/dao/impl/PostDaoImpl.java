package com.citynow.dao.impl;

import com.citynow.dao.IPostDao;
import com.citynow.mapper.impl.PostMapper;
import com.citynow.model.PostModel;

import java.util.List;

public class PostDaoImpl extends AbstractDao<PostModel> implements IPostDao {

    /**
     * Find post with ID post from database
     * @param id
     * @return  post model
     */
    @Override
    public PostModel findOne(Long id) {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM POST, USER ");
        sql.append(" WHERE POST.user_id = USER.id AND post.id = ?");
        List<PostModel> postModels = query(sql.toString(), new PostMapper(), id);
        return postModels.isEmpty() ? null : postModels.get(0);
    }

    /**
     * Save post to database
     * @param postModel
     * @return ID of post model
     */
    @Override
    public Long save(PostModel postModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO post (user_id, datemodified,");
        sql.append(" upvote, downvote, title, filmname, postrate, postreview, status)");
        sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        return insert(sql.toString(), postModel.getUser().getId(),
                postModel.getDateModified(), postModel.getUpvote(), postModel.getDownvote(),
                postModel.getTitle(), postModel.getFilmName(), postModel.getPostRate(), postModel.getPostReview(), postModel.getStatus());
    }

    /**
     * Update post to database replace old post
     * @param postModel
     */
    @Override
    public void update(PostModel postModel) {
        StringBuilder sql = new StringBuilder("UPDATE post SET user_id =?, datemodified = ?, upvote = ?,");
        sql.append(" downvote = ?, title = ?, filmname = ?,");
        sql.append(" postrate = ?, postreview = ?, status = ? WHERE id = ?");
        update(sql.toString(), postModel.getUser().getId(), postModel.getDateModified(), postModel.getUpvote(), postModel.getDownvote(),
                postModel.getTitle(), postModel.getFilmName(), postModel.getPostRate(), postModel.getPostReview(),
                postModel.getStatus(), postModel.getId());
    }

    /**
     * Delete post from database
     * @param id
     */
    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM post WHERE id = ?";
        update(sql, id);
    }

    /**
     * Find all post from database
     * @return List all post model
     */
    @Override
    public List<PostModel> findAll() {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM POST, USER ");
        sql.append(" WHERE POST.user_id = USER.id");
        return query(sql.toString(), new PostMapper());
    }

    /**
     * Find all post with pagination(page, limit) from database
     * @param page
     * @param limit
     * @return List post with page and limit of 1 page
     */
    @Override
    public List<PostModel> findAll(int page, int limit) {
        StringBuilder sql = new StringBuilder(" SELECT * ");
        sql.append(" FROM POST, USER ");
        sql.append(" WHERE POST.user_id = USER.id");
        sql.append(" ORDER BY post.datemodified asc, post.id asc");
        sql.append(" LIMIT ? OFFSET ?");

        // offset = (page -1 ) * limit (MySQL count from 0)
        int offset = (page - 1) * limit;
        return query(sql.toString(), new PostMapper(), limit, offset);
    }

    /**
     * Find list posts with status from database
     * @param status
     * @return List post with status
     */
    @Override
    public List<PostModel> findAllByStatus(int status) {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM POST, USER ");
        sql.append(" WHERE POST.user_id = USER.id AND post.status = ?");
        return query(sql.toString(), new PostMapper(), status);
    }

    /**
     * Find all posts with status and pagination(page, limit) from database
     * @param status
     * @param search
     * @param page
     * @param limit
     * @param sort
     * @return List post with status and page, limit of 1 page
     */
    @Override
    public List<PostModel> findAllByStatus(int status, String search, int page, int limit, String sort) {

        StringBuilder sql = new StringBuilder(" ");
        if (sort.equals("date-asc")) {
            sql.append(" SELECT * ");
            sql.append(" FROM POST, USER ");
            sql.append(" WHERE POST.user_id = USER.id AND post.status = ?");
            sql.append(" and (LOWER(post.title) LIKE CONCAT(\"%\",  LOWER(?), \"%\") or  ");
            sql.append(" LOWER(post.postreview) LIKE CONCAT(\"%\",  LOWER(?), \"%\")) ");
            sql.append(" ORDER BY post.datemodified asc, post.id asc");
            sql.append(" LIMIT ? OFFSET ?");
        } else if (sort.equals("like-asc")) {
            sql.append(" SELECT * ");
            sql.append(" FROM POST, USER ");
            sql.append(" WHERE POST.user_id = USER.id AND post.status = ?");
            sql.append(" and (LOWER(post.title) LIKE CONCAT(\"%\",  LOWER(?), \"%\") or  ");
            sql.append(" LOWER(post.postreview) LIKE CONCAT(\"%\",  LOWER(?), \"%\")) ");
            sql.append(" ORDER BY post.upvote asc, post.id desc");
            sql.append(" LIMIT ? OFFSET ?");
        } else if (sort.equals("like-desc")) {
            sql.append(" SELECT * ");
            sql.append(" FROM POST, USER ");
            sql.append(" WHERE POST.user_id = USER.id AND post.status = ?");
            sql.append(" and (LOWER(post.title) LIKE CONCAT(\"%\",  LOWER(?), \"%\") or  ");
            sql.append(" LOWER(post.postreview) LIKE CONCAT(\"%\",  LOWER(?), \"%\")) ");
            sql.append(" ORDER BY post.upvote desc, post.id desc");
            sql.append(" LIMIT ? OFFSET ?");
        } else if (sort.equals("dislike-asc")) {
            sql.append(" SELECT * ");
            sql.append(" FROM POST, USER ");
            sql.append(" WHERE POST.user_id = USER.id AND post.status = ?");
            sql.append(" and (LOWER(post.title) LIKE CONCAT(\"%\",  LOWER(?), \"%\") or  ");
            sql.append(" LOWER(post.postreview) LIKE CONCAT(\"%\",  LOWER(?), \"%\")) ");
            sql.append(" ORDER BY post.downvote asc, post.id desc");
            sql.append(" LIMIT ? OFFSET ?");
        } else if (sort.equals("dislike-desc")) {
            sql.append(" SELECT * ");
            sql.append(" FROM POST, USER ");
            sql.append(" WHERE POST.user_id = USER.id AND post.status = ?");
            sql.append(" and (LOWER(post.title) LIKE CONCAT(\"%\",  LOWER(?), \"%\") or  ");
            sql.append(" LOWER(post.postreview) LIKE CONCAT(\"%\",  LOWER(?), \"%\")) ");
            sql.append(" ORDER BY post.downvote desc, post.id desc");
            sql.append(" LIMIT ? OFFSET ?");
        } else {
            // date- desc (Post new)
            sql.append(" SELECT * ");
            sql.append(" FROM POST, USER ");
            sql.append(" WHERE POST.user_id = USER.id AND post.status = ?");
            sql.append(" and (LOWER(post.title) LIKE CONCAT(\"%\",  LOWER(?), \"%\") or  ");
            sql.append(" LOWER(post.postreview) LIKE CONCAT(\"%\",  LOWER(?), \"%\")) ");
            sql.append(" ORDER BY post.datemodified desc, post.id desc");
            sql.append(" LIMIT ? OFFSET ?");
        }

        // offset = (page -1 ) * limit (MySQL count from 0)
        int offset = (page - 1) * limit;
        return query(sql.toString(), new PostMapper(), status, search, search, limit, offset);
    }

    /**
     * Find list posts with user who created this post from database
     * @param userId
     * @return list posts which user created
     */
    @Override
    public List<PostModel> findAllByUserId(Long userId) {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM POST, USER ");
        sql.append(" WHERE POST.user_id = USER.id AND user.id = ?");
        return query(sql.toString(), new PostMapper(), userId);
    }

    /**
     * Find list posts with user who created this post and pagination(page, limit) from database
     * @param userId
     * @param page
     * @param limit
     * @return List posts which user created with page and limit of 1 page
     */
    @Override
    public List<PostModel> findAllByUserId(Long userId, int page, int limit) {
        StringBuilder sql = new StringBuilder(" SELECT * ");
        sql.append(" FROM POST, USER ");
        sql.append(" WHERE POST.user_id = USER.id AND user.id = ? ");
        sql.append(" LIMIT ? OFFSET ?");

        // offset = (page -1 ) * limit (MySQL count from 0)
        int offset = (page - 1) * limit;
        return query(sql.toString(), new PostMapper(), userId, limit, offset);
    }

    /**
     * Find list posts with user who created this post and status of that post
     * @param userId
     * @param postStatus
     * @return list posts which user created and status of this post
     */
    @Override
    public List<PostModel> findAllByUserIdAndStatus(Long userId, int postStatus) {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM POST, USER ");
        sql.append(" WHERE POST.user_id = USER.id AND user.id = ? AND post.status = ?");
        return query(sql.toString(), new PostMapper(), userId, postStatus);
    }

    /**
     * Find list post top like highest with status
     * @param top
     * @param status
     * @return List posts top like highest with status
     */
    @Override
    public List<PostModel> findAllTopByStatus(int top, int status) {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM POST, USER ");
        sql.append(" WHERE POST.user_id = USER.id AND post.status = ? ");
        sql.append(" order by post.upvote desc, post.id desc ");
        sql.append(" limit ? ");
        return query(sql.toString(), new PostMapper(), status, top);
    }

    /**
     * Count quantity post with post status from database
     * @param status
     * @return quantity post with status
     */
    @Override
    public int countByStatus(int status) {
        StringBuilder sql = new StringBuilder("SELECT count(*) ");
        sql.append(" FROM POST ");
        sql.append(" WHERE post.status= ? ");
        return count(sql.toString(), status);
    }

    /**
     * Count quantity post with post status and search text from database
     * @param status
     * @param search
     * @return quantity post with status and search text
     */
    @Override
    public int countByStatusAndSearch(int status, String search) {
        StringBuilder sql = new StringBuilder("SELECT count(*) ");
        sql.append(" FROM POST ");
        sql.append(" WHERE post.status= ? ");
        sql.append(" and (LOWER(post.title) LIKE CONCAT(\"%\",  LOWER(?), \"%\") or  ");
        sql.append(" LOWER(post.postreview) LIKE CONCAT(\"%\",  LOWER(?), \"%\")) ");
        return count(sql.toString(), status, search, search);
    }

    /**
     * Count quantity post with user who created this from database
     * @param userId
     * @return quantity post with user who created this
     */
    @Override
    public int countByUserId(Long userId) {
        StringBuilder sql = new StringBuilder("SELECT count(*) ");
        sql.append(" FROM POST ");
        sql.append(" WHERE post.user_id = ? ");
        return count(sql.toString(), userId);
    }

    /**
     * Count quantity all post form database
     * @return quantity all post
     */
    @Override
    public int count() {
        StringBuilder sql = new StringBuilder("SELECT count(*) ");
        sql.append(" FROM POST ");
        return count(sql.toString());
    }
}
