package com.yt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO implements Serializable {
    private String userName; //用户名
    private String nickName;

    private String password1; //首次输入密码
    private String password2; //确认密码
    private String phonenumber; //电话号码

    private String sex; //性别 单选框 0男，1女，2未知
    //private String userType; // 下拉列表 0管理员，1运营
}
