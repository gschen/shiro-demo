package com.demo.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by apple on 8/18/14.
 */
@Controller
public class DemoController {


    @RequiresRoles("guest")
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public  String demo( ) {

        return "demo";
    }
}
