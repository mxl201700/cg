package com.changgou.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Package: com.changgou.oauth.controller
 * Author: Mxl
 * Date: Created in 2019/9/3 10:00
 **/
@Controller
@RequestMapping("/oauth")
public class LoginRedirectController {

    @GetMapping("/login")
    public String login(String From, Model model){
        model.addAttribute("from",From);
        return "login";
    }
}
