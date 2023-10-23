package com.yt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yt.entity.TransactionLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionLogMapper extends BaseMapper<TransactionLog> {
}
