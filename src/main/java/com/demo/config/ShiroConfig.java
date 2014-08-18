package com.demo.config;

import com.demo.realm.MyRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Created by apple on 8/18/14.
 */
@Configuration
public class ShiroConfig {


    @Bean
    public MyRealm myRealm() {

        return new MyRealm();

    }


    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("myRealm") MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(myRealm);
        return securityManager;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();


        shiroFilter.setSecurityManager(securityManager);

        shiroFilter.setLoginUrl("/login");
        shiroFilter.setSuccessUrl("/success");
        shiroFilter.setUnauthorizedUrl("/login");

//        shiroFilter.setFilterChainDefinitions("" +
//                        "/static/** = anon\n" +
//                        "/index.html = anon \n" +
//                        "/users/** = roles[admin] \n" +
//                        "/role/edit/* = roles[admin:edit]\n" +
//                        "/role/save = roles[admin:new]\n" +
//                        "/role/list = perms[admin:manager]\n" +
//                        "/** = authc\n"
//
//        );
        return shiroFilter;


    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {

        return new LifecycleBeanPostProcessor();

    }

    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true); // it's false by default
        return creator;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
