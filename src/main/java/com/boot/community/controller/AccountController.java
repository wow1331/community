package com.boot.community.controller;

import com.boot.community.model.User;
import com.boot.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/account")
public class AccountController {
    @Autowired
    UserService userService;
    @GetMapping("/login")
    public String login(){
        log.info("@# login()");

        return "account/login";
    }
    @GetMapping("/register")
    public String register(){
        return "account/register";
    }
    @PostMapping("/register")
    public String register(User user){
        userService.save(user);

        return "account/login";
    }
}
