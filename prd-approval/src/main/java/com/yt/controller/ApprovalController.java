package com.yt.controller;

import com.yt.entity.Approval;
import com.yt.service.ApprovalService;
import com.yt.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/approval")
@Api(value = "商品接口", tags = "商品接口")
public class ApprovalController {
    @Autowired
    ApprovalService approvalService;

    @PostMapping("/commit/{options}")
    @ApiOperation(value = "审批", tags = "审批")
    @PreAuthorize("hasAnyAuthority('product:approval')")
    public ResponseResult approval(@RequestBody Approval approval, @PathVariable("options") Integer options){
        return approvalService.approval(approval,options);
    }
    @PostMapping("/commitByDraftId/{draftId}/{options}")
    @ApiOperation(value = "根据草稿id审批", tags = "根据草稿id审批")
    @PreAuthorize("hasAnyAuthority('product:approval')")
    public ResponseResult approvalByDraftId(@PathVariable("draftId")Integer draftId,@PathVariable("options") Integer options){

        return approvalService.approvalByDraftId(draftId,options);
    }
}
