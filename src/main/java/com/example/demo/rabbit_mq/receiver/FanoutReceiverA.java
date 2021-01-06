package com.example.demo.rabbit_mq.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者----订阅与发布模式（Fanout）
 */
@Component
@Slf4j
public class FanoutReceiverA {

    @RabbitListener(queues = {"fanoutQueueA"})
    @RabbitHandler
    public void process(String hello) {
        log.info(hello + "----FanoutReceiverA");
    }

    @RabbitListener(queues = {"fanoutQueueD"})
    @RabbitHandler
    public void processB(String hello) {
        log.info(hello + "----FanoutReceiverD");
    }

    @RabbitListener(queues = {"fanoutQueueE"})
    @RabbitHandler
    public void processC(String hello) {
        log.info(hello + "----FanoutReceiverE");
    }
}
