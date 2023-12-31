package com.yt.service.impl;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yt.entity.Approval;
import com.yt.entity.Draft;
import com.yt.entity.LoginUser;
import com.yt.entity.Product;
import com.yt.mapper.ApprovalMapper;
import com.yt.mapper.DraftMapper;
import com.yt.mapper.ProductMapper;
import com.yt.service.ApprovalService;
import com.yt.vo.ResponseResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.yt.constants.ResponseCode.FAIL;
import static com.yt.constants.ResponseCode.SUCCESS;

@Service
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    ApprovalMapper approvalMapper;

    @SofaReference(interfaceType = DraftMapper.class, jvmFirst = false,
            binding = @SofaReferenceBinding(bindingType = "bolt"))
    DraftMapper draftMapper;
    @SofaReference(interfaceType = ProductMapper.class, jvmFirst = false,
            binding = @SofaReferenceBinding(bindingType = "bolt"))
    ProductMapper productMapper;

    @Override
    @Transactional
    public ResponseResult approval(Approval approval, Integer options) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        UpdateWrapper<Approval> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",approval.getId());
        approval.setStatus(options);
        approval.setProcessTime(new Date());
        approval.setManagerId(Math.toIntExact(userid));
        approvalMapper.update(approval,updateWrapper);
        if(options==3){
            //商品上线

            Draft draft = draftMapper.selectById(approval.getDraftId());
            Product product = new Product();

            System.out.println(draft);
            BeanUtils.copyProperties(draft,product);
            product.setStatus(1);
            product.setId(null);
            productMapper.insert(product);
        } else if (options==2) {
            //退回草稿
            Draft draft = draftMapper.selectById(approval.getDraftId());
            //审核未通过
            draft.setStatus(4);
            draftMapper.updateById(draft);
        } else{
            return new ResponseResult(FAIL,"审批参数错误！");
        }
        return new ResponseResult(SUCCESS,"审批成功！");
    }
    @Transactional
    public ResponseResult approvalByDraftId(Integer draftId,Integer options){
        Draft draft = draftMapper.selectById(draftId);
        if (draft.getStatus()==1){
            return new ResponseResult(FAIL,"创建者未发起审批申请！");
        }
        if (draft.getStatus()==3){
            return new ResponseResult(FAIL,"商品已上线！");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        //修改审批表中处理时间和处理人
        Approval approval = new Approval();
        UpdateWrapper<Approval> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("draft_id",draftId);
        approval.setManagerId(Math.toIntExact(userid));
        approval.setProcessTime(new Date());
        approvalMapper.update(approval,updateWrapper);
        //修改草稿状态
        draft.setStatus(options);
        draftMapper.updateById(draft);
        if(options==3){
            //商品上线
            Product product = new Product();
            System.out.println(draft);
            BeanUtils.copyProperties(draft,product);
            product.setStatus(1);
            product.setId(null);
            productMapper.insert(product);
        }
        return new ResponseResult(SUCCESS,"操作成功");
    }
}
