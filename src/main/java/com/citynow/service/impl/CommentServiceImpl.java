package com.citynow.service.impl;

import com.citynow.dao.ICommentDao;
import com.citynow.dao.impl.CommentDaoImpl;
import com.citynow.model.CommentModel;
import com.citynow.service.ICommentService;

import java.util.List;

public class CommentServiceImpl implements ICommentService {

    ICommentDao commentDao = new CommentDaoImpl();

    /**
     * Service find all comment
     * @return list comment model
     */
    @Override
    public List<CommentModel> findAll() {
        return commentDao.findAll();
    }

    /**
     * Service find all comment by user ID
     * @param userId
     * @return list comment model
     */
    @Override
    public List<CommentModel> findAllByUserId(Long userId) {
        return commentDao.findAllByUserId(userId);
    }

    /**
     * Service find all comment by post ID
     * @param postId
     * @return list comment model
     */
    @Override
    public List<CommentModel> findAllByPostId(Long postId) {
        return commentDao.findAllByPostId(postId);
    }

    /**
     * Service find all comment by post ID wth pagination
     * @param postId
     * @param page
     * @param limit
     * @return list comment model
     */
    @Override
    public List<CommentModel> findAllByPostId(Long postId, int page, int limit) {
        return commentDao.findAllByPostId(postId, page, limit);
    }

    /**
     * Service save comment
     * @param commentModel
     * @return comment model
     */
    @Override
    public CommentModel save(CommentModel commentModel) {
        if (commentModel.getContent() == null || commentModel.getContent().trim().equals("")) {
            throw new NullPointerException("Value is null");
        }
        return commentDao.findOne(commentDao.save(commentModel));
    }

    /**
     * Service count total comment by post ID
     * @param postId
     * @return total comment
     */
    @Override
    public int countAllByPostId(Long postId) {
        return commentDao.countAllByPostId(postId);
    }

    /**
     * Service delete comment
     * @param id
     */
    @Override
    public void deleteByPostId(Long id) {
        commentDao.deleteByPostId(id);
    }
}
