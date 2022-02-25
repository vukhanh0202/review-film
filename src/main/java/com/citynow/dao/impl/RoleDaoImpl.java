package com.citynow.dao.impl;

import com.citynow.dao.IRoleDao;
import com.citynow.mapper.impl.RoleMapper;
import com.citynow.model.RoleModel;

import java.util.List;

public class RoleDaoImpl extends AbstractDao<RoleModel> implements IRoleDao {

    /**
     * Find role with ID role from database
     * @param id
     * @return role
     */
    @Override
    public RoleModel findOne(Long id) {
        String sql = "SELECT * FROM role WHERE id = ?";
        List<RoleModel> roles = query(sql, new RoleMapper(), id);
        return roles.isEmpty() ? null : roles.get(0);
    }
}
