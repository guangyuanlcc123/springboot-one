package com.example.demo.rabbit_mq.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者-----主题模式（Topic）
 */
@Component
@Slf4j
public class TopicReceiverA {

    @RabbitListener(queues = {"topicQueueA"})
    @RabbitHandler
    public void receiverMsg(String msg){
        log.info(msg + "----TopicReceiverA");
    }

    @RabbitListener(queues = {"topicQueueC"})
    @RabbitHandler
    public void receiverMsgB(String msg){
        log.info(msg + "----TopicReceiverC");
    }
}