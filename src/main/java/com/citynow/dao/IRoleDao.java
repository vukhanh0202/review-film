package com.citynow.dao;


import com.citynow.model.RoleModel;

/**
 * Use handle role data from database
 * @author VuKhanh
 */
public interface IRoleDao {

    /**
     * Find role with ID role from database
     * @param id
     * @return role
     */
    RoleModel findOne(Long id);
}
