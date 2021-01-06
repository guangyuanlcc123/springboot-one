package com.example.demo.rabbit_mq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置类
 * 四、路由模式（Direct）
 * 所有发送到Direct Exchange的消息被转发到RouteKey中指定的Queue。
 * 消息传递时，RouteKey必须完全匹配才会被队列接收，否则该消息会被抛弃。
 */

@Configuration
public class RabbitMQConfigDirect {

    /**
     * 声明一个的交换器
     */
    @Bean("directExchange")
    public Exchange directExchange() {
        return ExchangeBuilder.directExchange("amq.direct").durable(true).build();
    }

    //队列
    @Bean("directQueue")
    public Queue directQueue(){
        return new Queue("directQueue", true, true, true);
        //return QueueBuilder.durable("directQueue").build();
    }

    //将directQueue队列绑定到directExchange交换机上面，使用direct_routingKey作为路由规则
    @Bean
    public Binding directBinding(@Qualifier("directQueue")Queue queue, @Qualifier("directExchange")Exchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("direct_routingKey").noargs();
    }

}
