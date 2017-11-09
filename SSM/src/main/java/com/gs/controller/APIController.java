package com.gs.controller;

import com.gs.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {

    Logger logger = LoggerFactory.getLogger(APIController.class);

    @GetMapping("user/one")
    public User test1() {
        User user = new User();
        user.setName("中文");
        user.setPassword("123456");
        logger.info("获取单个用户数据");
        return user;
    }

    @GetMapping("user/list")
    public List<User> test2() {
        User user = new User();
        user.setName("中文");
        user.setPassword("123456");
        User user1 = new User();
        user1.setName("中国");
        user1.setPassword("86");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        return userList;
    }

}
