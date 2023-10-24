package com.yt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yt.entity.Menu;
import com.yt.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
}
