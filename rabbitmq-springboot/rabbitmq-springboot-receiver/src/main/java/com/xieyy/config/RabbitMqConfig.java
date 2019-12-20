package com.xieyy.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xieyyt
 * @date 2019/12/13 16:43
 */
@Configuration
public class RabbitMqConfig {

    public static final String QUEUE_NAME = "xieyy-001";

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

//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate();
//        rabbitTemplate.setConnectionFactory(connectionFactory);
//        return rabbitTemplate;
//    }

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
        containerFactory.setConnectionFactory(connectionFactory);
//        containerFactory.createListenerContainer();
        containerFactory.setTaskExecutor(threadPoolTaskExecutor());
        containerFactory.setConcurrentConsumers(Runtime.getRuntime().availableProcessors() * 2);
//        containerFactory.setMaxConcurrentConsumers(Runtime.getRuntime().availableProcessors() * 4);
//        containerFactory.setPrefetchCount(1000);
//        SimpleRabbitListenerEndpoint rabbitListenerEndpoint = new SimpleRabbitListenerEndpoint();
//        rabbitListenerEndpoint.setQueueNames("xieyy-001");
//        rabbitListenerEndpoint.setMessageListener(receiveListener);

        return containerFactory;
    }

}
