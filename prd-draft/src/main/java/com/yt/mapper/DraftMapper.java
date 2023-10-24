package com.yt.mapper;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yt.entity.Draft;
import com.yt.service.DraftService;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SofaService(interfaceType = DraftMapper.class, bindings = { @SofaServiceBinding(bindingType = "bolt") })
public interface DraftMapper extends BaseMapper<Draft> {
}
