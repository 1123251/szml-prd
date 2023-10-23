package com.yt.rocketmq;

import com.alibaba.fastjson.JSONObject;
import com.yt.entity.Approval;
import com.yt.entity.Draft;
import com.yt.mapper.ApprovalMapper;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class ApprovalListener implements MessageListenerConcurrently {
    @Autowired
    ApprovalMapper approvalMapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        logger.info("消费者线程监听到消息。");
        try{
            for (MessageExt message:list) {
                logger.info("开始处理审批消息，准备将信息存入审批表");
                Draft draft = JSONObject.parseObject(message.getBody(), Draft.class);
                Approval approval = new Approval();
                approval.setDraftId(draft.getId());
                approvalMapper.insert(approval);
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }catch (Exception e){
            logger.error("处理消费者数据发生异常。{}",e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }
}
