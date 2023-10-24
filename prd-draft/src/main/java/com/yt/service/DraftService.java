package com.yt.service;

import com.yt.entity.Draft;
import com.yt.vo.ResponseResult;
import org.apache.rocketmq.client.exception.MQClientException;

public interface DraftService  {
        ResponseResult  commitDraft(Draft draft,String transactionId);

        ResponseResult commitDraft(Draft draft) throws MQClientException;

        ResponseResult createDraft(Draft draft);

        ResponseResult draftList(int currentPage,int pageSize);


}
