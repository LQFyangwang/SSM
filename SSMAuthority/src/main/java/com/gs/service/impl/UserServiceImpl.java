package com.gs.service.impl;

import com.gs.bean.User;
import com.gs.dao.UserDAO;
import com.gs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User getByNamePwd(String name, String password) {
        return userDAO.getByNamePwd(name, password);
    }
}
