package com.qiancong.sell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: sell
 * @description:
 * @author: Cong.Qian
 * @create: 2018-08-19 00:56
 **/
@Controller
public class LoginController {
    @RequestMapping("login")
    public String login(){
        return "login/register";
    }
}
