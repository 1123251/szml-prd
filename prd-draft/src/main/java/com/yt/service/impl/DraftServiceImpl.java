package com.yt.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yt.entity.Draft;
import com.yt.entity.LoginUser;
import com.yt.entity.TransactionLog;
import com.yt.mapper.DraftMapper;
import com.yt.mapper.TransactionLogMapper;
import com.yt.rocketmq.TransactionProducer;
import com.yt.service.DraftService;
import com.yt.vo.ResponseResult;
import org.apache.rocketmq.client.exception.MQClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.yt.constants.ResponseCode.SUCCESS;
import static com.yt.constants.rocketmqConstants.TRANSACTION_APPROVAL_TOPIC;

@Service
public class DraftServiceImpl extends ServiceImpl<DraftMapper,Draft> implements DraftService {
    @Autowired
    DraftMapper draftMapper;
    @Autowired
    TransactionLogMapper transactionLogMapper;
    @Autowired
    TransactionProducer producer;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    //执行本地事务，不提供给前端控制器
    @Transactional
    @Override
    public ResponseResult commitDraft(Draft draft, String transactionId) {

        UpdateWrapper<Draft> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",draft.getId());
        Draft draft1 = new Draft();
        draft1.setStatus(2);
        draftMapper.update(draft1, updateWrapper);
        TransactionLog log = new TransactionLog();
        log.setId(transactionId);
        log.setBusiness("commitDraft");
        log.setForeignKey(String.valueOf(draft.getId()));
        transactionLogMapper.insert(log);

        logger.info("草稿提交审核。{}",draft);
        return null;
    }


    //前端调用，只发送消息
    @Override
    public ResponseResult commitDraft(Draft draft) throws MQClientException {
        //查询草稿是否存在，状态是否为待审核
        Draft select=draftMapper.selectById(draft.getId());
        if (Objects.isNull(select)){
            //throw new IllegalArgumentException("参数非法，该草稿不存在！");
            return  new ResponseResult(SUCCESS,"参数非法，该草稿不存在！");
        }
        if ( select.getStatus()!=1) {
            //throw new IllegalArgumentException("参数非法，该草稿已审核！");
            return  new ResponseResult(SUCCESS,"参数非法，该草稿已审核 ,请等待管理员处理！");
        }
        if (select.getStatus()==1) {

            producer.send(JSON.toJSONString(draft),TRANSACTION_APPROVAL_TOPIC);

        }
        return new ResponseResult(SUCCESS,"草稿已提交审核！");

    }

    @Override
    public ResponseResult createDraft(Draft draft) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        draft.setCaretaker(Math.toIntExact(userid));
        draftMapper.insert(draft);
        return new ResponseResult(SUCCESS,"草稿已保存！");
    }

    @Override
    public ResponseResult draftList(int currentPage,int pageSize) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        QueryWrapper<Draft> wrapper = new QueryWrapper<Draft>();
        wrapper.eq("caretaker",userid)
                .select();
        IPage<Draft> page = this.page(new Page<>(currentPage,pageSize), wrapper);

        return new ResponseResult(SUCCESS,page);
    }
}
