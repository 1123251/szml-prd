package com.yt.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yt.config.RedisPageHelper;
import com.yt.entity.LoginUser;
import com.yt.entity.Product;
import com.yt.entity.SelectRulers;
import com.yt.entity.TimePeriod;
import com.yt.mapper.ExchangeRecordsMapper;
import com.yt.mapper.ProductMapper;
import com.yt.service.ProductService;
import com.yt.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yt.constants.RedisConstants.CACHE_PRODUCT;
import static com.yt.constants.ResponseCode.FAIL;
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
    ExchangeRecordsMapper exchangeRecordsMapper;

    @Autowired
    ProductService productService;
    @GetMapping("/productDetails/{id}")
    @ApiOperation(value = "商品详情", tags = "商品详情")
    public ResponseResult productDetails(@PathVariable int id){
        return new ResponseResult(SUCCESS,productMapper.selectById(id));
    }
    @GetMapping("/proList")
    @ApiOperation(value = "商品分页列表", tags = "商品分页列表")
    public ResponseResult proList(@RequestBody SelectRulers selectRulers){
        //查询缓存中是否有数据
        System.out.println(selectRulers);
        int offset = (selectRulers.getCurrentPage()-1)* selectRulers.getPageSize();
        selectRulers.setCurrentPage(offset);
//        int start=currentPage*pageSize-pageSize;
//        int end=currentPage*pageSize-1;
//        List<Product> list =redisPageHelper.getListPage(CACHE_PRODUCT,start,end);
//        if(!list.isEmpty()){
//            Page<Product> result = new Page<Product>();
//            result.setRecords(list);
//            result.setCurrent(currentPage);
//            System.out.println(result);
//            return new ResponseResult<>(SUCCESS,result);
//        }
        //新建分页构造函数Page<T> T为目标实体类
        Page<Product> result = new Page<Product>();
        result.setRecords(productMapper.selectByRulers(selectRulers));

        //将数据添加进缓存
        //List<Product> productList= productService.list();
        //redisTemplate.opsForList().rightPushAll(CACHE_PRODUCT,productList);
        return new ResponseResult<>(SUCCESS,result);
    }
    @GetMapping("/editStatus/{id}/{status}")
    @ApiOperation(value = "商品上下线", tags = "商品上下线")
    @PreAuthorize("hasAnyAuthority('product:approval','product:add')")
    public ResponseResult editProduct(@PathVariable int id,@PathVariable int status){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        Product product = productMapper.selectById(id);
        if (userid!=product.getId().intValue()&&userid!=product.getProxyId().intValue()){
            return new ResponseResult<>(FAIL,"您没有该商品的修改权限！");
        }
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        Product product1 = new Product();
        product1.setStatus(status);
        productMapper.update(product1, updateWrapper);
      //删除缓存
        //redisTemplate.delete(CACHE_PRODUCT);
        return new ResponseResult<>(SUCCESS,"编辑成功！");
    }
    @PostMapping("/dateRecordsList")
    @ApiOperation(value = "日兑换量列表", tags = "日兑换量列表")
    public ResponseResult  dateRecordsList(@RequestBody TimePeriod timePeriod){

        return new ResponseResult(SUCCESS,exchangeRecordsMapper.dateRecordsList(timePeriod));
    }
    @PostMapping("/productRecordsList/{nums}")
    @ApiOperation(value = "销量排行", tags = "销量排行")
    public ResponseResult  productRecordsList(@RequestBody TimePeriod timePeriod,@PathVariable int nums){

        return new ResponseResult(SUCCESS,exchangeRecordsMapper.productRecords(timePeriod,nums));
    }

    @PostMapping("/priceTypeRecords")
    @ApiOperation(value = "兑换方式统计", tags = "兑换方式统计")
    public ResponseResult  priceTypeRecords(@RequestBody TimePeriod timePeriod){

        return new ResponseResult(SUCCESS,exchangeRecordsMapper.priceTypeRecords(timePeriod));
    }

}
