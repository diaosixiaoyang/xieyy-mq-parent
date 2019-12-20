package com.xieyy.receive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @author xieyyt
 * @date 2019/12/20 9:52
 */
@Slf4j
@Component
public class BaseListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            byte[] body = message.getBody();
            log.info("BaseListener接收到的消息为：{}", new String(body, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
    }
}
