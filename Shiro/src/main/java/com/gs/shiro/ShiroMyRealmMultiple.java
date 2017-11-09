package com.gs.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class ShiroMyRealmMultiple {

    @Test
    public void testMyRealm() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-myrealm-multiple.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken("tom", "123456"));
        } catch (AccountException e) {
            System.out.println(e.getMessage());
        }
        String username =  (String) subject.getPrincipal();
        System.out.println(username);
        System.out.println(subject.hasRole("管理员"));
        System.out.println(subject.isPermitted("system:setting"));
    }

}
