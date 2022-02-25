package com.citynow.service.impl;

import com.citynow.dao.ICommentDao;
import com.citynow.dao.IPostDao;
import com.citynow.dao.IVoteDao;
import com.citynow.dao.impl.CommentDaoImpl;
import com.citynow.dao.impl.PostDaoImpl;
import com.citynow.dao.impl.VoteDaoImpl;
import com.citynow.model.PostModel;
import com.citynow.model.UserModel;
import com.citynow.service.IPostService;

import java.util.List;

public class PostServiceImpl implements IPostService {

    IPostDao postDao = new PostDaoImpl();

    ICommentDao commentDao = new CommentDaoImpl();

    IVoteDao voteDao = new VoteDaoImpl();

    /**
     * Service Find all post
     * @return list post model
     */
    @Override
    public List<PostModel> findAll() {
        return postDao.findAll();
    }

    /**
     * Service Find all post with pagination
     * @param page
     * @param limit
     * @return list post model
     */
    @Override
    public List<PostModel> findAll(int page, int limit) {
        return postDao.findAll(page, limit);
    }

    /**
     * Service Find all post by status
     * @param status
     * @return list post model
     */
    @Override
    public List<PostModel> findAllByStatus(int status) {
        return postDao.findAllByStatus(status);
    }

    /**
     * Service Find all post by status with pagination
     * @param status
     * @param search
     * @param page
     * @param limit
     * @param sort
     * @return list post model
     */
    @Override
    public List<PostModel> findAllByStatus(int status, String search, int page, int limit, String sort) {
        return postDao.findAllByStatus(status, search, page, limit, sort);
    }

    /**
     * Service Find all post by ID user
     * @param userId
     * @return list post model
     */
    @Override
    public List<PostModel> findAllByUserId(Long userId) {
        return postDao.findAllByUserId(userId);
    }

    /**
     * Service Find all post by ID user with pagination
     * @param userId
     * @param page
     * @param limit
     * @return list post model
     */
    @Override
    public List<PostModel> findAllByUserId(Long userId, int page, int limit) {
        return postDao.findAllByUserId(userId, page, limit);
    }

    /**
     * Service Find all post by id user and status
     * @param userId
     * @param postStatus
     * @return
     */
    @Override
    public List<PostModel> findAllByUserIdAndStatus(Long userId, int postStatus) {
        return postDao.findAllByUserIdAndStatus(userId, postStatus);
    }

    /**
     * Service Find top post with status which have like highest
     * @param top
     * @param postStatus
     * @return list post model
     */
    @Override
    public List<PostModel> findAllTopByStatus(int top, int postStatus) {
        return postDao.findAllTopByStatus(top, postStatus);
    }

    /**
     * Service Find post model with ID
     * @param id
     * @return post model
     */
    @Override
    public PostModel findOne(Long id) {
        return postDao.findOne(id);
    }

    /**
     * Service save post
     * @param postModel
     * @return post model
     */
    @Override
    public PostModel save(PostModel postModel) {
        if (postModel.getFilmName().equals("") || postModel.getPostReview().equals("") || postModel.getTitle().equals("")) {
            throw new NullPointerException("Value is null");
        }
        return postDao.findOne(postDao.save(postModel));
    }

    /**
     * Service update post
     * @param postModel
     * @return post model
     */
    @Override
    public PostModel update(PostModel postModel) {
        if (postModel.getFilmName().equals("") || postModel.getPostReview().equals("") || postModel.getTitle().equals("")) {
            throw new NullPointerException("Value is null");
        }


        postDao.update(postModel);
        return postDao.findOne(postModel.getId());
    }

    /**
     * Service count total post
     * @return total post
     */
    @Override
    public int count() {
        return postDao.count();
    }

    /**
     * Service count total post by status
     * @param status
     * @return total post
     */
    @Override
    public int countByStatus(int status) {
        return postDao.countByStatus(status);
    }

    /**
     * Service count total post by status and search text
     * @param status
     * @param search
     * @return total post
     */
    @Override
    public int countByStatusAndSearch(int status, String search) {
        return postDao.countByStatusAndSearch(status, search);
    }

    /**
     * Service count total post by user ID
     * @param userId
     * @return total post
     */
    @Override
    public int countByUserId(Long userId) {
        return postDao.countByUserId(userId);
    }

    /**
     * Service delete post
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id:
             ids) {
            commentDao.deleteByPostId(id);
            voteDao.deleteByPostId(id);
            postDao.delete(id);
        }
    }
}
