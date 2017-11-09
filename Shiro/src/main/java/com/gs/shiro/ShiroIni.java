package com.gs.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class ShiroIni {

    public static void main(String... args) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance(); // 获取安全管理器
        SecurityUtils.setSecurityManager(securityManager); // 通过安全管理器获取安全工具类
        Subject subject = SecurityUtils.getSubject(); // 获取主体
        try {
            subject.login(new UsernamePasswordToken("test2", "123456")); // 身份认证，认证令牌
        } catch (UnknownAccountException e) { // 用户账户异常
            System.out.println("不存在此账户");
        } catch (IncorrectCredentialsException e) { // 用户凭证异常
            System.out.println("密码错误");
        } catch (AccountException e) {
            System.out.println("用户名或密码错误");
        }
        String username = (String) subject.getPrincipal(); // 身份 用户账户
        Session session = subject.getSession(); // 会话对象，跟 HttpSession是不一样的
        session.setAttribute("user", username);
        System.out.println(username + "登录系统！");
        System.out.println(session.getAttribute("user"));
        subject.logout(); // 用户安全退出
        String username1 = (String) subject.getPrincipal(); // 身份 用户账户
        System.out.println(username1);
        System.out.println(session.getAttribute("user")); // 已经登出，则session自动清空
    }

}
