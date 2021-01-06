package com.example.demo.rabbit_mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * RabbitMQ配置类
 * 三、订阅与发布模式（Fanout）
 * 不处理路由键，只需要简单的将队里绑定到交换机上
 * 生产者将消息不是直接发送到队列，而是发送到X交换机，发送到交换机的消息都会被转发到与该交换机绑定的所有队列上
 * Fanout交换机转发消息是最快的
 * @author ml
 */
@Configuration
public class RabbitMQConfigFanout {

    //队列
    @Bean("fanoutQueueA")
    public Queue fanoutQueueA(){
        return new Queue("fanoutQueueA", true, true, true);
    }
    //队列
    @Bean("fanoutQueueB")
    public Queue fanoutQueueB(){
        return new Queue("fanoutQueueB", true, true, true);
    }
    //队列
    @Bean("fanoutQueueC")
    public Queue fanoutQueueC(){
        return new Queue("fanoutQueueC", true, true, true);
    }
    //队列
    @Bean("fanoutQueueD")
    public Queue fanoutQueueD(){
        return new Queue("fanoutQueueD", true, true, true);
    }
    //队列
    @Bean("fanoutQueueE")
    public Queue fanoutQueueE(){
        return new Queue("fanoutQueueE", true, true, true);
    }

    /**
     * 声明一个Fanout类型的交换器
     * @Author mazq
     * @Date 2020/04/08 11:25
     * @Param []
     * @return org.springframework.amqp.core.FanoutExchange
     */
    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }


    //将fanoutQueueA队列绑定到fanoutExchange交换机上面
    @Bean
    public Binding fanoutABinding(@Qualifier("fanoutQueueA")Queue queue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
    //将fanoutQueueB队列绑定到fanoutExchange交换机上面
    @Bean
    public Binding fanoutBBinding(@Qualifier("fanoutQueueB")Queue queue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
    //将fanoutQueueC队列绑定到fanoutExchange交换机上面
    @Bean
    public Binding fanoutCBinding(@Qualifier("fanoutQueueC")Queue queue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
    //将fanoutQueueD队列绑定到fanoutExchange交换机上面
    @Bean
    public Binding fanoutDBinding(Queue fanoutQueueD, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueueD).to(fanoutExchange);
    }
    //将fanoutQueueE队列绑定到fanoutExchange交换机上面
    @Bean
    public Binding fanoutEBinding(Queue fanoutQueueE, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueueE).to(fanoutExchange);
    }

}
