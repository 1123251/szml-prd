package com.yt.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yt.config.RedisPageHelper;
import com.yt.entity.Product;
import com.yt.mapper.ProductMapper;
import com.yt.service.ProductService;
import com.yt.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.yt.constants.RedisConstants.CACHE_PRODUCT;
import static com.yt.constants.ResponseCode.SUCCESS;

@RestController
@RequestMapping("/product")
@Api(value = "商品接口", tags = "商品接口")
public class ProductController {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisPageHelper redisPageHelper;

    @Autowired
    ProductService productService;
    @GetMapping("/proList/{currentPage}/{pageSize}")
    @ApiOperation(value = "商品分页列表", tags = "商品分页列表")
    public ResponseResult proList(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize){
        //查询缓存中是否有数据
        int start=currentPage*pageSize-pageSize;
        int end=currentPage*pageSize-1;
        List<Product> list =redisPageHelper.getListPage(CACHE_PRODUCT,start,end);
        if(!list.isEmpty()){
            Page<Product> result = new Page<Product>();
            result.setRecords(list);
            result.setCurrent(currentPage);
            System.out.println(result);
            return new ResponseResult<>(SUCCESS,result);
        }

        //新建分页构造函数Page<T> T为目标实体类
        Page<Product> productPage = new Page<Product>(currentPage, pageSize);
        //result为分页查询结果
        Page<Product> result = productService.page(productPage);
        //将数据添加进缓存
        List<Product> productList= productService.list();
        redisTemplate.opsForList().rightPushAll(CACHE_PRODUCT,productList);
        return new ResponseResult<>(SUCCESS,result);

    }
}
