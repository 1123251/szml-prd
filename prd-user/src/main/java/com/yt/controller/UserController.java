package com.yt.controller;

import com.yt.entity.User;
import com.yt.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.yt.service.LoginService;
@RestController
@RequestMapping("/user")
@Api(value = "用户接口", tags = "用户接口")
public class UserController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "登录", tags = "登录")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }

    @GetMapping("/logout")
    @ResponseBody
    @ApiOperation(value = "登出", tags = "登出")

    public ResponseResult logout(){
        return loginService.logout();
    }


}
