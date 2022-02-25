package com.citynow.service.impl;

import com.citynow.dao.IRoleDao;
import com.citynow.dao.impl.RoleDaoImpl;
import com.citynow.model.RoleModel;
import com.citynow.service.IRoleService;

public class RoleServiceImpl implements IRoleService {

    IRoleDao roleDao = new RoleDaoImpl();

    /**
     * Service Find role by ID
     * @param id
     * @return role model
     */
    @Override
    public RoleModel findOne(Long id) {
        return roleDao.findOne(id);
    }
}
