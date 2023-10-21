package com.yt.service;

import com.yt.entity.User;
import com.yt.vo.ResponseResult;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
