package com.qiancong.sell.controller;

import com.qiancong.sell.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @program: sell
 * @description:
 * @author: Cong.Qian
 * @create: 2018-08-19 00:56
 **/
@Slf4j
@Controller
public class LoginController {
    @Autowired
    private HttpSession session;
    @Autowired
    private SellerService sellerService;

    @RequestMapping("")
    public String login() {
        return "login/login";
    }

    @RequestMapping("loginJudge")
    public String loginJudge(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (sellerService.getPasswordByUserName(username).equals(password)) {
            session.setAttribute("name", username);
            session.setAttribute("password", password);
            return "redirect:/seller/order/list";
        } else {
            return "login/login";
        }
    }

    @RequestMapping("/seller/loginOut")
    public String logOut() {
        session.invalidate();
        return "redirect:/";
    }
}
