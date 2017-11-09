package com.gs.service.impl;

import com.gs.bean.Permission;
import com.gs.dao.PermissionDAO;
import com.gs.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDAO permissionDAO;

    @Override
    public Permission getByRolePermission(Long roleId, String permission) {
        return permissionDAO.getByRolePermission(roleId, permission);
    }
}
