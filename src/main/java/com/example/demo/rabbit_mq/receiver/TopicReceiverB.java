package com.example.demo.rabbit_mq.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者-----主题模式（Topic）
 */
@Component
@RabbitListener(queues = {"topicQueueB"})
@Slf4j
public class TopicReceiverB {

    @RabbitHandler
    public void receiverMsg(String msg){
        log.info(msg + "----TopicReceiverB");
    }
}
