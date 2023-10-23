package com.yt.controller;

import com.yt.entity.Approval;
import com.yt.service.ApprovalService;
import com.yt.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/approval")
@Api(value = "商品接口", tags = "商品接口")
public class ApprovalController {
    @Autowired
    ApprovalService approvalService;
    @PostMapping("/commit/{options}")
    @ApiOperation(value = "审批", tags = "审批")

    public ResponseResult approval(@RequestBody Approval approval, @PathVariable("options") Integer options){
        return approvalService.approval(approval,options);
    }
}
