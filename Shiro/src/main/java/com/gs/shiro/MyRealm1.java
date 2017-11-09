package com.gs.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class MyRealm1 extends AuthorizingRealm {
    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取登录用户所对应的角色和权限信息，把角色和权限信息通过AuthorizationInfo对象返回
        // PrincipalCollection是身份的集合
        String username = (String) principalCollection.getPrimaryPrincipal(); // 获取主账号
        // TODO 根据用户名去数据库中查找此用户对应的所有角色和权限信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        roles.add("管理员");
        roles.add("老板");
        authorizationInfo.setRoles(roles);
        Set<String> permissions = new HashSet<>();
        permissions.add("user:add");
        permissions.add("system:setting");
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 获取用户认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = String.valueOf((char[]) authenticationToken.getCredentials());
        // TODO 调用自定义的service去查找是否有此用户
        if (username.equals("tom1") && password.equals("123456")) {
            return new SimpleAuthenticationInfo(username, password, "myRealm1");
        } else {
            throw new AccountException("用户名或密码错误");
        }

    }
}
