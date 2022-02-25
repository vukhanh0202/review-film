package com.citynow.dao.impl;

import com.citynow.dao.IVoteDao;
import com.citynow.mapper.impl.VoteMapper;
import com.citynow.model.VoteModel;

import java.util.List;

public class VoteDaoImpl extends AbstractDao<VoteModel> implements IVoteDao {

    /**
     * Save vote model to database
     * @param voteModel
     * @return ID vote
     */
    @Override
    public Long save(VoteModel voteModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO vote (user_id, post_id , actionvote) ");
        sql.append(" VALUES(?, ?, ?)");
        return insert(sql.toString(), voteModel.getUser().getId(), voteModel.getPost().getId(),
                voteModel.getActionVote());
    }

    /**
     * Update vote model replace old model from database
     * @param voteModel
     */
    @Override
    public void update(VoteModel voteModel) {
        StringBuilder sql = new StringBuilder("UPDATE vote SET user_id =?, post_id = ?, actionvote = ? ");
        sql.append(" WHERE id = ?");
        update(sql.toString(), voteModel.getUser().getId(), voteModel.getPost().getId(), voteModel.getActionVote(),
                voteModel.getId());
    }

    /**
     * Find all vote model of user from database
     * @param userId
     * @return List vote model
     */
    @Override
    public List<VoteModel> findAllByUserId(Long userId) {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM VOTE, USER, POST ");
        sql.append("  WHERE vote.post_id = post.id and vote.user_id = user.id and user.id = ?");
        return query(sql.toString(), new VoteMapper(), userId);
    }

    /**
     * Find all vote model of user with pagination from database
     * @param userId
     * @param page
     * @param limit
     * @return List vote model
     */
    @Override
    public List<VoteModel> findAllByUserId(Long userId, int page, int limit) {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM VOTE, USER, POST ");
        sql.append("  WHERE vote.post_id = post.id and vote.user_id = user.id and user.id = ?");
        sql.append(" LIMIT ? OFFSET ?");

        // offset = (page -1 ) * limit (MySQL count from 0)
        // limit = limit - 1
        int offset = (page - 1) * limit;
        return query(sql.toString(), new VoteMapper(), userId, limit, offset);
    }

    /**
     * find vote model with ID from database
     * @param id
     * @return vote model
     */
    @Override
    public VoteModel findOne(Long id) {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM VOTE, USER, POST ");
        sql.append("  WHERE vote.post_id = post.id and vote.user_id = user.id and vote.id = ?");
        List<VoteModel> votes = query(sql.toString(), new VoteMapper(), id);
        return votes.isEmpty() ? null : votes.get(0);
    }

    /**
     * Find vote model with user and post from database
     * @param userId
     * @param postId
     * @return vote model
     */
    @Override
    public VoteModel findOneByUserIdAndPostId(Long userId, Long postId) {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM VOTE, USER, POST ");
        sql.append("  WHERE vote.post_id = post.id and vote.user_id = user.id and user.id = ? and post.id = ? ");
        List<VoteModel> votes = query(sql.toString(), new VoteMapper(), userId, postId);
        return votes.isEmpty() ? null : votes.get(0);
    }

    /**
     * Count total vote of post with action(like or dislike)
     * @param postId
     * @param action
     * @return total vote
     */
    @Override
    public Long countTotalVoteByPostIdAndAction(Long postId, int action) {
        StringBuilder sql = new StringBuilder("SELECT count(*) ");
        sql.append(" FROM VOTE ");
        sql.append("  WHERE vote.post_id = ? and actionvote = ? ");
        return Long.valueOf(count(sql.toString(), postId, action));
    }

    /**
     * Delete vote from database
     * @param id
     */
    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM VOTE WHERE id = ?";
        update(sql, id);
    }

    @Override
    public void deleteByPostId(Long postId) {
        String sql = "DELETE FROM VOTE WHERE post_id = ?";
        update(sql, postId);
    }

    /**
     * Count total vote of user (like or dislike)
     * @param userId
     * @return total vote of user
     */
    @Override
    public int countByUserId(Long userId) {
        StringBuilder sql = new StringBuilder("SELECT count(*) ");
        sql.append(" FROM vote ");
        sql.append(" WHERE vote.user_id = ? ");
        return count(sql.toString(), userId);
    }
}
