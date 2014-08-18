package com.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by apple on 8/18/14.
 */
@Controller
public class UserController {

//    @RequiresRoles("admin")
    @RequiresPermissions("admin:manager")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(){

        return "userlist";
    }

    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String success() {

        return "success";
    }

    @RequestMapping(value = "logout")
    public String logout() {

        SecurityUtils.getSubject().logout();

        return "logout";
    }
}
