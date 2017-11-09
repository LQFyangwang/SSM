package com.gs.service;

import com.gs.bean.User;

public interface UserService {

    User getByNamePwd(String name, String password);

}
