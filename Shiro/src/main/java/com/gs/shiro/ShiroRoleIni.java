package com.gs.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShiroRoleIni {

    @Test
    public void testShiroRole() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-role.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken("test2", "123456"));
        } catch (AccountException e) {
            System.out.println("用户名或密码错误！");
        }
        String username = (String) subject.getPrincipal();
        System.out.println("role1： " + subject.hasRole("role1")); // 判断登录的用户是否拥有指定的角色
        System.out.println("role2： " + subject.hasRole("role2"));
        List<String> roleList = new ArrayList<>();
        roleList.add("role1");
        roleList.add("role2");
        boolean[] hasRoles = subject.hasRoles(roleList);
        for (boolean hasRole : hasRoles) {
            System.out.print(hasRole + ", ");
        }
        System.out.println(subject.hasAllRoles(roleList));
        // subject.checkRole("role2"); // 需要捕捉异常

        System.out.println("user:add: " + subject.isPermitted("user:add"));
        System.out.println("user:remove: " + subject.isPermitted("user:remove"));
        System.out.println("user:update: " + subject.isPermitted("user:update"));
        subject.isPermitted("user:add", "user:remove"); // 判断多个权限
        subject.isPermittedAll("user:add", "user:remove"); // 判断是否同时拥有指定的多个权限
        System.out.println(subject.isPermitted(new WildcardPermission("user:aaa"))); // 通配符匹配
    }

}
