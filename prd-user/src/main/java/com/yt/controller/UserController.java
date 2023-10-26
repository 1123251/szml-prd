package com.yt.controller;

import com.yt.dto.RegisterDTO;
import com.yt.entity.User;
import com.yt.service.UserService;
import com.yt.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.yt.service.LoginService;

import static com.yt.constants.ResponseCode.FAIL;
import static com.yt.constants.ResponseCode.SUCCESS;

@RestController
@RequestMapping("/user")
@Api(value = "用户接口", tags = "用户接口")
public class UserController {
    @Autowired
    private LoginService loginService;
    @Autowired
    UserService userService;

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

    @PostMapping("/register")
    @ApiOperation(value = "注册", tags = "注册")
    public ResponseResult register(@RequestBody RegisterDTO registerDTO){
        if (registerDTO.getUserName() == null
                || registerDTO.getUserName().length()==0
                || registerDTO.getPassword1() == null
                || registerDTO.getPassword1().length()==0
                || registerDTO.getNickName() == null
                || registerDTO.getNickName() .length()==0
        ){
            return new ResponseResult(FAIL,"账号、密码和昵称不能为空");
        }
        if (!registerDTO.getPassword1().equals(registerDTO.getPassword2())){
            return new ResponseResult(FAIL,"两次密码不相同");
        }
        if (registerDTO.getSex() == null || registerDTO.getSex().length()==0){
            return new ResponseResult(FAIL,"请选择用户性别");
        }

        //判断用户名是否重复
        User user = userService.getByUserName(registerDTO.getUserName());
        if (user != null && user.getUserName().equals(registerDTO.getUserName())){
            return new ResponseResult(FAIL,"用户名重复");
        }

        //注册
        userService.insert(registerDTO);
        return new ResponseResult(SUCCESS,"注册成功");
    }


}
