package com.yt.controller;

import com.yt.entity.Draft;
import com.yt.mapper.DraftMapper;
import com.yt.service.DraftService;
import com.yt.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.client.exception.MQClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.yt.constants.ResponseCode.SUCCESS;

@RestController
@RequestMapping("/draft")
@Api(value = "商品接口", tags = "商品接口")
public class DraftController {

    @Autowired
    DraftService draftService;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DraftMapper draftMapper;

    @PostMapping("/commitDraft")
    @ApiOperation(value = "草稿提交审批", tags = "草稿提交审批")
    @PreAuthorize("hasAnyAuthority('product:add')")
    public ResponseResult commitDraft(@RequestBody Draft draft) throws MQClientException {
        logger.info("提交审批数据：{}",draft.getName());
        return  draftService.commitDraft(draft);
    }

    @PostMapping("/createDraft")
    @ApiOperation(value = "草稿创建", tags = "草稿创建")
    @PreAuthorize("hasAnyAuthority('product:add')")
    public ResponseResult createDraft(@RequestBody Draft draft)  {

        return  draftService.createDraft(draft);
    }
    @PutMapping("/editDraft")
    @ApiOperation(value = "草稿编辑", tags = "草稿编辑")
    @PreAuthorize("hasAnyAuthority('product:add')")
    public ResponseResult editDraft(@RequestBody Draft draft)  {

        return  new ResponseResult(SUCCESS,draftMapper.updateById(draft));
    }

    @GetMapping("/draftList/{currentPage}/{pageSize}")
    @ApiOperation(value = "个人草稿列表", tags = "个人草稿列表")
    @PreAuthorize("hasAnyAuthority('product:add')")
    public ResponseResult draftList(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize)  {

        return  draftService.draftList(currentPage,pageSize);
    }

    @GetMapping("/unApprovalDraftList/{currentPage}/{pageSize}")
    @ApiOperation(value = "待审核草稿列表", tags = "待审核草稿列表")
    @PreAuthorize("hasAnyAuthority('product:approval')")
    public ResponseResult unApprovalDraftList(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize)  {

        return  draftService.unApprovalDraftList(currentPage,pageSize);
    }

    @GetMapping("/getDraft/{draftId}")
    @ApiOperation(value = "草稿详情", tags = "草稿详情")
    public ResponseResult getDraft(@PathVariable Integer draftId) throws MQClientException {

        return  new ResponseResult(SUCCESS,draftMapper.selectById(draftId));
    }


}
