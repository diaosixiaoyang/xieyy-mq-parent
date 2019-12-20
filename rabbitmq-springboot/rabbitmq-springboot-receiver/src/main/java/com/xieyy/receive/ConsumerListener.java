package com.xieyy.receive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

import static com.xieyy.config.RabbitMqConfig.*;

/**
 * @author xieyyt
 * @date 2019/12/20 9:36
 */
@Slf4j
@Component
public class ConsumerListener implements MessageListener {

    @Override
    @RabbitListener(queues = QUEUE_NAME)
    public void onMessage(Message message) {
        byte[] body = message.getBody();
        try {
            log.info("ConsumerListener接收到的消息为：{}", new String(body, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }

    }
}
