package com.example.demo.rabbit_mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置类
 * 五、主题模式（Topic）
 * 所有发送到Topic Exchange的消息被转发到所有管线RouteKey中指定Topic的Queue上
 * Exchange将RouteKey和某Topic进行模糊匹配,此时队列需要绑定一个Topic
 */

@Configuration
public class RabbitMQConfigTopic {

    //队列
    @Bean("topicQueueA")
    public Queue topicQueueA(){
        return new Queue("topicQueueA",true, true, true);
    }
    //队列
    @Bean("topicQueueB")
    public Queue topicQueueB(){
        return new Queue("topicQueueB",true, true, true);
    }
    //队列
    @Bean("topicQueueC")
    public Queue topicQueueC(){
        return new Queue("topicQueueC",true, true, true);
    }

    /**
     * 声明一个的交换器
     */
    @Bean("topicExchange")
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    //将topicQueueA队列绑定到topicExchange交换机上面，使用topic.msg作为路由规则
    @Bean
    public Binding topicABinding(@Qualifier("topicQueueA")Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("topic.msg");
    }
    //将topicQueueB队列绑定到topicExchange交换机上面，使用topic.#作为路由规则
    @Bean
    public Binding topicBBinding(@Qualifier("topicQueueB")Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("topic.#");
    }
    //将topicQueueC队列绑定到topicExchange交换机上面，使用topic.msg作为路由规则
    @Bean
    public Binding topicCBinding(@Qualifier("topicQueueC")Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("topic.msg");
    }
}
