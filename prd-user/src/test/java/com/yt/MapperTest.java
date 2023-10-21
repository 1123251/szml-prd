package com.yt;

import com.yt.dao.UserMapper;
import com.yt.entity.User;
import com.yt.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginService loginService;
    @Test
    public void testLogin(){
        User user = new User();
        user.setUserName("root");
        user.setPassword("root");
        System.out.println(loginService.login(user));
    }

    @Test
    public void testUserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}