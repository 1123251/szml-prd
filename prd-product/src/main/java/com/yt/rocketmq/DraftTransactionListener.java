package com.yt.rocketmq;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yt.entity.Draft;
import com.yt.entity.TransactionLog;
import com.yt.mapper.TransactionLogMapper;
import com.yt.service.DraftService;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DraftTransactionListener implements TransactionListener {
    @Autowired
    DraftService draftService;

    @Autowired
    TransactionLogMapper transactionLogMapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        logger.info("开始执行本地事务....");
        LocalTransactionState state;
        try{
            String body = new String(message.getBody());
            Draft draft = JSONObject.parseObject(body, Draft.class);
            draftService.commitDraft(draft,message.getTransactionId());
            state = LocalTransactionState.COMMIT_MESSAGE;
            logger.info("本地事务已提交。{}",message.getTransactionId());
        }catch (Exception e){
            logger.info("执行本地事务失败。{}",e);
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return state;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        logger.info("开始回查本地事务状态。{}",messageExt.getTransactionId());
        LocalTransactionState state;
        String transactionId = messageExt.getTransactionId();
        QueryWrapper<TransactionLog> qw = new QueryWrapper<>();
        qw.eq("id",transactionId);
        if (transactionLogMapper.selectCount(qw)>0){
            state = LocalTransactionState.COMMIT_MESSAGE;
        }else {
            state = LocalTransactionState.UNKNOW;
        }
        logger.info("结束本地事务状态查询：{}",state);
        return state;
    }
}
