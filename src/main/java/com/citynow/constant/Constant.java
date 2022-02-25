package com.citynow.constant;

/**
 * Constant for project
 * @author VuKhanh
 */
public interface Constant {

    // Status of user
    int USER_ACTIVE_STATUS = 1;
    int USER_BLOCK_STATUS = 0;
    int USER_BAN_STATUS = -1;

    // Status of post
    int POST_APPROVE_STATUS = 1;
    int POST_PENDING_STATUS = 0;

    // Role of user
    Long ROLE_ADMIN = 1L;
    Long ROLE_USER = 2L;

    // Vote of post
    int VOTE_LIKE = 1;
    int VOTE_DISLIKE = 0;
}
