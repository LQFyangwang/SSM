package com.gs.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * AuthenticatingRealm    用户信息数据源
 */
public class MyRealm extends AuthenticatingRealm {
    /**
     * 获取用户认证信息
     * <p>
     * AuthenticationInfo   认证信息，包含用户身份和用户凭证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = String.valueOf((char[]) authenticationToken.getCredentials());
        // TODO 调用自定义的service去查找是否有此用户
        if (username.equals("tom") && password.equals("123456")) {
            return new SimpleAuthenticationInfo(username, password, "myRealm");
        } else {
            throw new AccountException("用户名或密码错误");
        }

    }
}
