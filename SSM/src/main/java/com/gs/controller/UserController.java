package com.gs.controller;

import com.gs.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 如果直接使用@RequestMapping，默认使用的是get请求
     * @return
     */
    @RequestMapping("login_page")
    public String showLoginPage() {
        return "user/login";
    }

    @RequestMapping("login_page1")
    public String showLoginPage1() {
        return "user/login2";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("name") String aa, String password) {
        System.out.println(aa + ": " + password);
        return "登录成功！";
    }

    /**
     * 如果只是返回单个字符串，则比较特殊，需要在RequestMapping中加入produces，说明返回类型
     * @param user
     * @return
     */
    @PostMapping(value = "login1", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login1(User user) {
        System.out.println(user.getName() + ": " + user.getPassword());
        String msg = "{\"msg\":\"登录成功\"}";
        return msg;
    }

    @PostMapping(value = "login2")
    public ModelAndView login2(User user, HttpSession session) {
        System.out.println(user.getName() + ": " + user.getPassword());
        ModelAndView mav = new ModelAndView("user/home");
        mav.addObject("user", user); // 默认使用request.setAttribute的方法把对象放入request中
        session.setAttribute("user1", user);
        return mav;
    }

    @GetMapping("get")
    @ResponseBody
    public List<User> getUser() {
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

    /**
     * http://localhost:8080/user/param?test1=test1&test2=test2&test3=test3   @RequestParam
     * http://localhost:8080/user/param/test1_value/test2_value/test3_value   @PathVariable
     *
     * http://localhost:8080/user/remove?id=100
     * http://localhost:8080/user/remove/100
     * http://localhost:8080/user/remove/id/100
     *
     * http://localhost:8080/user/remove?email=abc@qq.com
     * http://localhost:8080/user/remove/email/abc@qq.com
     *
     * @param test1
     * @param test2
     * @param a
     * @return
     */
    @GetMapping(value = "param", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String test(String test1, String test2, @RequestParam("test3") String a) {
        System.out.println("test1: " + test1);
        System.out.println("test2: " + test2);
        System.out.println("test3: " + a);
        return "test";
    }

    @GetMapping(value = "param1/{test_1}/{test_2}/{test_3}", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String test1(@PathVariable("test_1") String test1, @PathVariable("test_2") String test2, @PathVariable("test_3") String a) {
        System.out.println("test1: " + test1);
        System.out.println("test2: " + test2);
        System.out.println("test3: " + a);
        return "test";
    }

}
