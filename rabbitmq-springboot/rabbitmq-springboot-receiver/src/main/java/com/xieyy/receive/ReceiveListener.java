package com.xieyy.receive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

import static com.xieyy.config.RabbitMqConfig.*;

/**
 * @author xieyyt
 * @date 2019/12/13 16:38
 */
@Slf4j
@Component
public class ReceiveListener implements MessageListener {

//    @Autowired
//    private RabbitListenerContainerFactory xyyContainerFactory;

    /**
     * 要想使@RabbitListener生效，这个类必须进行初始化，可以用spring的初始化bean进行
     *
     * @param message
     */
    @RabbitListener(queues = {QUEUE_NAME}, containerFactory = "xyyContainerFactory")
//    @RabbitListener(queues = {QUEUE_NAME})
    public void onMessage(Message message) {
        try {
            log.info("接收到的消息为：{}", new String(message.getBody(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("发生异常:{}", e);
        }
    }
}
