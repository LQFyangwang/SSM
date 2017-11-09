package com.gs.controller;

import com.gs.bean.Permission;
import com.gs.bean.User;
import com.gs.common.ControllerResult;
import com.gs.service.PermissionService;
import com.gs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("login_page")
    public String loginPage() {
        return "login";
    }

    @PostMapping("login")
    @ResponseBody
    public ControllerResult login(User user, HttpSession session) {
        User user1 = userService.getByNamePwd(user.getName(), user.getPassword());
        ControllerResult controllerResult;
        if (user1 != null) {
            // 登录成功
            session.setAttribute("user", user1);
            controllerResult = new ControllerResult("ok", "登录成功");
        } else {
            // 登录失败，用户名或密码错误
            controllerResult = new ControllerResult("error", "用户名或密码错误");
        }
        return controllerResult;
    }

    @GetMapping("home")
    public String homePage() {
        return "home";
    }

    @GetMapping(value = "hire", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String hire(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Permission permission = permissionService.getByRolePermission(user.getRole().getId(), UserController.class.getName() + ".hire");
        if (permission != null) {
            // 有操作权限
            // TODO 做业务逻辑
            return "has permission";
        } else {
            // 无操作权限
            return "no permission";
        }
    }

    @GetMapping(value = "fire", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String fire(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Permission permission = permissionService.getByRolePermission(user.getRole().getId(), UserController.class.getName() + ".fire");
        if (permission != null) {
            // 有操作权限
            // TODO 做业务逻辑
            return "has permission";
        } else {
            // 无操作权限
            return "no permission";
        }
    }

}
