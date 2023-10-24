package com.yt.mapper;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yt.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SofaService(interfaceType = ProductMapper.class, bindings = { @SofaServiceBinding(bindingType = "bolt") })
public interface ProductMapper extends BaseMapper<Product> {
}
