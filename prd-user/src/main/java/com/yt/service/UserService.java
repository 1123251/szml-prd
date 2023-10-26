package com.yt.service;

import com.yt.dto.RegisterDTO;
import com.yt.entity.User;

public interface UserService {
    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    User getByUserName(String userName);

    /**
     * 将用户信息写入数据库,返回主键id
     * @param registerDTO
     */
    void insert(RegisterDTO registerDTO);
}
