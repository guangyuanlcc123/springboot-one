package com.example.demo.rabbit_mq.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者-----路由模式（Direct）
 */
@Component
@Slf4j
public class DirectReceiver {

    @RabbitListener(queues = {"directQueue"})
    @RabbitHandler
    public void receiverMsg(String msg){
        log.info(msg + "----DirectReceiver");
    }

    @RabbitListener(queues = {"directQueue"})
    @RabbitHandler
    public void receiverMsgB(String msg){
        log.info(msg + "----DirectReceiverThree");
    }
}