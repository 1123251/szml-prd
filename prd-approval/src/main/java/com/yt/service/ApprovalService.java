package com.yt.service;

import com.yt.entity.Approval;
import com.yt.vo.ResponseResult;

public interface ApprovalService {
    //审批,1通过,2驳回
    ResponseResult approval(Approval approval, Integer options);
    ResponseResult approvalByDraftId(Integer draftId,Integer options);
}
