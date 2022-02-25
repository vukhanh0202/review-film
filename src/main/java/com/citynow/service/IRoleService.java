package com.citynow.service;

import com.citynow.model.RoleModel;

/**
 * Service handle role
 * @author VuKhanh
 */
public interface IRoleService {

    /**
     * Service Find role by ID
     * @param id
     * @return role model
     */
    RoleModel findOne(Long id);
}
