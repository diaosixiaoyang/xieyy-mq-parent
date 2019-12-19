package com.xieyy.receive;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.UnsupportedEncodingException;

/**
 * @author xieyyt
 * @date 2019/12/13 16:38
 */
public class ReceiveListener implements MessageListener {

    @RabbitListener(queues = {"xieyy-springboot-queue"})
    public void onMessage(Message message) {
        try {
            System.out.println("rabbitmq-springboot-receive>>>>>>" + new String(message.getBody(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
