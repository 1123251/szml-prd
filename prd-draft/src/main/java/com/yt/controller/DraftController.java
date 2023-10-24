package com.yt.controller;

import com.yt.entity.Draft;
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

@RestController
@RequestMapping("/draft")
@Api(value = "商品接口", tags = "商品接口")
public class DraftController {

    @Autowired
    DraftService draftService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @GetMapping("/draftList/{currentPage}/{pageSize}")
    @ApiOperation(value = "个人草稿列表", tags = "个人草稿列表")
    @PreAuthorize("hasAnyAuthority('product:add')")
    public ResponseResult draftList(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize)  {

        return  draftService.draftList(currentPage,pageSize);
    }





}
