package com.xieyy.config;

import com.xieyy.receive.BaseListener;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerEndpoint;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xieyyt
 * @date 2019/12/20 9:54
 */
@Configuration
public class BaseConsomerConfig {

    @Autowired
    private BaseListener baseListener;
    @Resource
    private RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;

    private static final String[] queueNames = {"xieyy-base-01", "xieyy-base-02"};

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors() * 5);
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 10);
        executor.setKeepAliveSeconds(500);
        executor.setThreadNamePrefix("xieyy-mq-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        executor.initialize();
        return executor;
    }

//    @Autowired
//    private ReceiveListener receiveListener;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("172.20.18.7");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherReturns(true);
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
//        connectionFactory.setExecutor(threadPoolTaskExecutor());
        return connectionFactory;
    }

    /**
     * 使用多线程模仿多个消费者，需要首先配置多线程的信息，然后再配置此信息，主要配置参数concurrentConsumers
     * 并且需要在listener中 @RabbitListener设置containerFactory为该bean的名字
     *
     * @param connectionFactory
     * @return
     */
    @Bean("xyyContainerFactory")
    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
        SimpleRabbitListenerEndpoint endpoint = new SimpleRabbitListenerEndpoint();
        endpoint.setQueueNames(queueNames);
//        endpoint.setGroup("xieyy-group");
        endpoint.setId("xieyy-id");
        endpoint.setMessageListener(baseListener);
        endpoint.setAckMode(AcknowledgeMode.AUTO);

        containerFactory.createListenerContainer(endpoint);
        containerFactory.setTaskExecutor(threadPoolTaskExecutor());
        containerFactory.setConnectionFactory(connectionFactory);
        containerFactory.setConcurrentConsumers(Runtime.getRuntime().availableProcessors() * 2);
        rabbitListenerEndpointRegistry.registerListenerContainer(endpoint, containerFactory, true);
//        containerFactory.setMaxConcurrentConsumers(Runtime.getRuntime().availableProcessors() * 4);
//        containerFactory.setPrefetchCount(1000);

        return containerFactory;
    }

}
