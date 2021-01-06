package com.example.demo.rabbit_mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 生产者
 */

@Component
@Slf4j
public class FanoutSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 四、路由模式（Direct）
     * 所有发送到Direct Exchange的消息被转发到RouteKey中指定的Queue。
     * 消息传递时，RouteKey必须完全匹配才会被队列接收，否则该消息会被抛弃。
     * 生产者，一个交换机(directExchange)，路由规则，多个消费者，多个队列
     */
    public void send(int i) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String content ="路由模式（Direct）==================" + i+":hello!"+date;
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        log.info("class:{},message:{}","DirectSender---send(0)",content);
        this.rabbitTemplate.convertAndSend("amq.direct","direct_routingKey",content,correlationData);
    }


    /**
     * 三、订阅与发布模式（Fanout）
     * 不处理路由键，只需要简单的将队里绑定到交换机上
     * 生产者将消息不是直接发送到队列，而是发送到X交换机，发送到交换机的消息都会被转发到与该交换机绑定的所有队列上
     * Fanout交换机转发消息是最快的
     * 生产者，一个交换机(fanoutExchange)，没有路由规则，多个消费者，多个队列
     */
    public void send() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String content = "订阅与发布模式（Fanout）==================" + "hello!"+date;
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        log.info("class:{},message:{}","FanoutSender---send(1)",content);
        this.rabbitTemplate.convertAndSend("fanoutExchange","",content,correlationData);
    }


    /**
     * 五、主题模式（Topic）---规则匹配上
     * 所有发送到Topic Exchange的消息被转发到所有管线RouteKey中指定Topic的Queue上
     * Exchange将RouteKey和某Topic进行模糊匹配,此时队列需要绑定一个Topic
     * 生产者，一个交换机(topicExchange)，模糊匹配路由规则，多个消费者，多个队列
     */
    public void send1() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String content ="主题模式（Topic）---规则匹配上==================" +  "hello!"+date;
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        log.info("class:{},message:{}","TopicSender---send1(2)",content);
        this.rabbitTemplate.convertAndSend("topicExchange","topic.msg",content,correlationData);
    }
    /**
     * 五、主题模式（Topic）---规则匹配不上
     * 所有发送到Topic Exchange的消息被转发到所有管线RouteKey中指定Topic的Queue上
     * Exchange将RouteKey和某Topic进行模糊匹配,此时队列需要绑定一个Topic
     * 生产者，一个交换机(topicExchange)，模糊匹配路由规则，多个消费者，多个队列
     */
    public void send2() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String content = "主题模式（Topic）---规则匹配不上==================" + "hello!"+date;
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        log.info("class:{},message:{}","TopicSender---send2(3)",content);
        this.rabbitTemplate.convertAndSend("topicExchange","topic.msg1",content,correlationData);
    }

    /**
     * 一、简单模式（Hello Word）
     * 生产者，一个消费者，一个队列
     *
     * 二、工作模式(一个队列有两个消费者。一个队列中一条消息，只能被一个消费者消费)
     * 生产者，多个消费者，一个队列
     */
    public void send3() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String content = "简单模式/工作模式==================" + "hello!"+date;
        log.info("class:{},message:{}","TopicSender---send3(4)",content);
        this.rabbitTemplate.convertAndSend("directQueue",content);
    }
}