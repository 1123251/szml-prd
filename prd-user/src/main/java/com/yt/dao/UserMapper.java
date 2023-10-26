package com.yt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yt.entity.User;
import com.yt.entity.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户名查询用户是否存在
     * @param userName
     * @return
     */
    @Select("select * from sys_user where user_name = #{userName}")
    User getByUserName(String userName);


    /**
     * 向数据库里面插入用户数据并返回主键值
     * @param user
     * @return
     */
    @Insert("INSERT INTO sys_user (id, user_name, nick_name, password, status, phonenumber, sex, avatar, user_type) " +
            "VALUES (null, #{userName}, #{nickName}, #{password}, #{status}, #{phonenumber}, #{sex}, #{avatar}, #{userType})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);


    /**
     * 向用户角色中间表插入数据
     * @param userRole
     */
    @Insert("INSERT INTO sys_user_role (user_id, role_id)" +
            "VALUES (#{userId}, #{roleId})")
    void insertUserRole(UserRole userRole);
}
