package com.yt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yt.entity.Product;
import com.yt.mapper.ProductMapper;
import com.yt.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>implements ProductService {
}
