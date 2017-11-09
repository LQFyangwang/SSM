package com.gs.dao;

import com.gs.bean.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDAO {

    Permission getByRolePermission(@Param("roleId") Long roleId, @Param("permission") String permission);

}
