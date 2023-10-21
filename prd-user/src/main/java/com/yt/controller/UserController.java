package com.yt.controller;

import com.yt.entity.User;
import com.yt.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yt.service.LoginService;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }

    @GetMapping("/logout")
    @ResponseBody
    public ResponseResult logout(){
        return loginService.logout();
    }
}
