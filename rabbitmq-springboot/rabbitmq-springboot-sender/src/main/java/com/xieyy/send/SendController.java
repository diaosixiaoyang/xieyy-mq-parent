package com.xieyy.send;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xieyyt
 * @date 2019/12/13 16:36
 */
@RestController
@Slf4j
public class SendController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitAdmin rabbitAdmin;

    @PostMapping("/send")
    public String send(String message, String exchange, String routingKey) {
        log.info("发布的消息为：{}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return "success";
    }

}
