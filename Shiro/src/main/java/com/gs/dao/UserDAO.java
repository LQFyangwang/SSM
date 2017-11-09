package com.gs.dao;

import com.gs.bean.User;

public interface UserDAO {

    User getByNamePwd(String name, String password);

}
