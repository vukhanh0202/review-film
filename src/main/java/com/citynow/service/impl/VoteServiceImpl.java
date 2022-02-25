package com.citynow.service.impl;

import com.citynow.dao.IVoteDao;
import com.citynow.dao.impl.VoteDaoImpl;
import com.citynow.model.VoteModel;
import com.citynow.service.IVoteService;

import java.util.List;

public class VoteServiceImpl implements IVoteService {

    IVoteDao voteDao = new VoteDaoImpl();

    /**
     * Service help save vote model
     * @param voteModel
     * @return vote model
     */
    @Override
    public VoteModel save(VoteModel voteModel) {
        return voteDao.findOne(voteDao.save(voteModel));
    }

    /**
     * Service help update vote model
     * @param voteModel
     * @return vote model
     */
    @Override
    public VoteModel update(VoteModel voteModel) {
        voteDao.update(voteModel);
        return voteDao.findOne(voteModel.getId());
    }

    /**
     * Service find all vote model by user ID
     * @param userId
     * @return List vote model
     */
    @Override
    public List<VoteModel> findAllByUserId(Long userId) {
        return voteDao.findAllByUserId(userId);
    }

    /**
     * Service find all vote model by user ID have pagination
     * @param userId
     * @param page
     * @param limit
     * @return list vote model
     */
    @Override
    public List<VoteModel> findAllByUserId(Long userId, int page, int limit) {
        return voteDao.findAllByUserId(userId, page,limit);
    }

    /**
     * Service find vote model by user ID and post ID
     * @param userId
     * @param postId
     * @return vote model
     */
    @Override
    public VoteModel findOneByUserIdAndPostId(Long userId, Long postId) {
        return voteDao.findOneByUserIdAndPostId(userId, postId);
    }

    /**
     * Service count total vote by post ID and action(like or dislike)
     * @param postId
     * @param action
     * @return total vote
     */
    @Override
    public Long countTotalVoteByPostIdAndAction(Long postId, int action) {
        return voteDao.countTotalVoteByPostIdAndAction(postId, action);
    }

    /**
     * Service delete vote
     * @param id
     */
    @Override
    public void delete(Long id) {
        voteDao.delete(id);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public int countByUserId(Long userId) {
        return voteDao.countByUserId(userId);
    }
}
