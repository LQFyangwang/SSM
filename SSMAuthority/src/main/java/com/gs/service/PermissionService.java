package com.gs.service;

import com.gs.bean.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionService {

    Permission getByRolePermission(Long roleId, String permission);

}
