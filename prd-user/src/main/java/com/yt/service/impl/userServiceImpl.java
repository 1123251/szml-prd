package com.yt.service.impl;

import com.yt.dao.UserMapper;
import com.yt.dto.RegisterDTO;
import com.yt.entity.User;
import com.yt.entity.UserRole;
import com.yt.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class userServiceImpl implements UserService {
    // 生成盐的强度
    private static final int SALT_ROUNDS = 12;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUserName(String userName) {
        return userMapper.getByUserName(userName);
    }

    @Override
    @Transactional
    public void insert(RegisterDTO registerDTO) {
        User user = new User();
        //拷贝属性
        BeanUtils.copyProperties(registerDTO,user);

        //密码bcrypt加密
        String salt = BCrypt.gensalt(SALT_ROUNDS);
        String password = BCrypt.hashpw(registerDTO.getPassword1(), salt);
        user.setPassword(password);

        //设置账号状态（默认启动 0正常 1停用）
        user.setStatus("0"); //默认启动
        user.setUserType(1L); //0管理员，1运营
        //向用户表插入数据
        userMapper.insertUser(user); //用户id
        //System.out.println("用户id为：----------"+user.getId());

        //管理用户权限添加
        UserRole userRole = new UserRole(user.getId(),UserRole.ROLE_ID_DEFAULT);
        userMapper.insertUserRole(userRole);

    }
}
