package com.yt.mapper;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yt.entity.Product;
import com.yt.entity.SelectRulers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
@SofaService(interfaceType = ProductMapper.class, bindings = { @SofaServiceBinding(bindingType = "bolt") })
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> selectByRulers(SelectRulers selectRulers);
}
