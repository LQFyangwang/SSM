package com.gs.controller;

import com.gs.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @PostMapping(value = "login", produces = {"application/json;charset=utf-8"})
    // @ResponseBody
    public String login(User user) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(user.getName(), user.getPassword()));
            Session session = subject.getSession();
            System.out.println(session.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY"));
        } catch (UnknownAccountException e) {
            return "用户名或密码错误";
        } catch (IncorrectCredentialsException e) {
            return "用户名或密码错误";
        } catch (AuthenticationException e) {
            return "用户名或密码错误";
        }
        return "home";
    }

}
