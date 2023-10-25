package com.yt.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yt.dao.RoleMapper;
import com.yt.dao.UserRoleMapper;
import com.yt.entity.LoginUser;
import com.yt.entity.User;
import com.yt.entity.UserRole;
import com.yt.service.LoginService;
import com.yt.util.JwtUtil;
import com.yt.util.RedisCache;
import com.yt.vo.ResponseResult;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

import static com.yt.constants.ResponseCode.SUCCESS;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleMapper roleMapper;
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        //把token响应给前端
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",jwt);
        QueryWrapper<UserRole> wrapper = new QueryWrapper<UserRole>();
        wrapper.eq("user_id",userId)
                .select();
        map.put("role",roleMapper.selectById( userRoleMapper.selectOne(wrapper).getRoleId()));
        return new ResponseResult(SUCCESS,"登陆成功",map);
    }


    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(SUCCESS,"退出成功");
    }
}
