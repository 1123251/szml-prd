package com.yt.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.yt.rocketmq.rocketmqConstants.TRANSACTION_APPROVAL_TOPIC;

@Component
public class Consumer {

    String consumerGroup = "consumer-group";
    DefaultMQPushConsumer consumer;
    @Value("${rocketmq.name-server}")
    String nameServer;

    @Autowired
    ApprovalListener approvalListener;

    @PostConstruct
    public void init() throws MQClientException {
        consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.subscribe(TRANSACTION_APPROVAL_TOPIC,"*");
        consumer.setNamesrvAddr(nameServer);
        consumer.registerMessageListener(approvalListener);
        consumer.setMaxReconsumeTimes(3);
        consumer.start();
    }
}
