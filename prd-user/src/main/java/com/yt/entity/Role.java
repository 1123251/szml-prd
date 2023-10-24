package com.yt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 角色表(SysRole)实体类
 *
 * @author makejava
 * @since 2023-10-24 18:02:54
 */
@TableName("sys_role")
public class Role implements Serializable {
    private static final long serialVersionUID = -77145920571234236L;
    @TableId(value="id",type = IdType.AUTO)
    private Long id;
    
    private String name;
    /**
     * 角色权限字符串
     */
    private String roleKey;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

}

