package com.xieyy;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xieyyt
 * @date 2019/12/13 15:08
 */
public class RabbitMqSender {

    private static final String QUEUE_NAME = "xieyy-simple";

    public static void main(String[] args) throws IOException, TimeoutException {

        String message = "this is a simple rabbitmq test...";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("172.20.18.7");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

//        channel.queueDeclare(QUEUE_NAME, false, true, true, null);

        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        channel.close();
        connection.close();

    }
}
