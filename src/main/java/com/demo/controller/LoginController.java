package com.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by apple on 8/18/14.
 */
@Controller
public class LoginController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String displayLoginPage() {



        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password
    ) {


        Subject currentUser = SecurityUtils.getSubject();

        currentUser.login(new UsernamePasswordToken(username, password));

        return "success";
    }


}
