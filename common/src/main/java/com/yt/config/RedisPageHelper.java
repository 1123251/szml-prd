package com.yt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
public class RedisPageHelper {

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;



    /** 添加对象到redis 里面的list中
     *  redis中的 list 是双向的 所以添加的时候需要注意
     *  rightPush 先进先出 leftPush 先进后出 这里 需要注意
     * @param key list 对应的key
     * @param obj 需要存的对象
     */
    public void addList(String key,Object obj){
        redisTemplate.opsForList().rightPush(key,obj);
    }


    /**
     * opsForList().range(key, start, end);  取范围值  redis里面的list下标从0开始
     *  流程 拿到key 对应的list 取 0 到 5  和 mysql的limit类似
     * 从redis list 里面的获取数据分页
     * @param key redis list 对应的key
     * @param start  开始下标
     * @param end 介绍下标
     * @return 返回list给前端
     */
    public List getListPage(String key, int start, int end){
        return (List)redisTemplate.opsForList().range(key, start, end);
    }

}
