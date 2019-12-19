package com.xieyy.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.SimpleRoutingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xieyyt
 * @date 2019/12/13 16:43
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();

        return cachingConnectionFactory;
    }
//
//    @Bean
//    public Queue queue() {
//        return new Queue("xieyy-springboot-queue");
//    }
//
//    @Bean
//    public Binding binding() {
//        return new Binding("xieyy-springboot-destination", Binding.DestinationType.QUEUE, "xieyy-springboot-exchange", "xieyy-springboot-routingkey", null);
//    }
//
    @Bean
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
//        rabbitAdmin.declareQueue(queue());
//        rabbitAdmin.declareBinding(binding());
        return rabbitAdmin;
    }

}
