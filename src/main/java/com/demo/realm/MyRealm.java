package com.demo.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {



        String username = (String) super.getAvailablePrincipal(principals);

        if ( "gschen".equals(username)) {

            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

            simpleAuthorizationInfo.addRole("admin");
            simpleAuthorizationInfo.addStringPermission("admin:manager");


            return simpleAuthorizationInfo;
        }

        return null;


    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        UsernamePasswordToken tokens = (UsernamePasswordToken) token;

        if ("gschen".equals(tokens.getUsername())) {
            System.out.println("gschen authenticated! ");
            return new SimpleAuthenticationInfo(tokens.getUsername(), tokens.getPassword(), getName());
        }


        return null;
    }


}
