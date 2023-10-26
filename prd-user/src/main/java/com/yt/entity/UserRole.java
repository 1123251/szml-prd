package com.yt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_role")
public class UserRole implements Serializable {
    public static final Long ROLE_ID_DEFAULT = 2L; //默认用户是运营  1是管理员

    private Long userId; //用户id
    private Long roleId; //角色id
}
