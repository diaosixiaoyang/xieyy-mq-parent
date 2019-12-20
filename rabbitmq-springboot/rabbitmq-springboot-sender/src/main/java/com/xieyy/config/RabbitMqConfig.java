package com.xieyy.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
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
        cachingConnectionFactory.setAddresses("172.20.18.7");
        cachingConnectionFactory.setPort(5672);
        cachingConnectionFactory.setUsername("admin");
        cachingConnectionFactory.setPassword("admin");
        cachingConnectionFactory.setVirtualHost("/");
        cachingConnectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

}
