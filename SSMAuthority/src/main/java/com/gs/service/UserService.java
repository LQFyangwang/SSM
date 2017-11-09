package com.gs.service;

import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {
    User getByNamePwd(String name, String password);
}
