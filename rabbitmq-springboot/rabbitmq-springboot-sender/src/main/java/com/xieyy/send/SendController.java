package com.xieyy.send;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xieyyt
 * @date 2019/12/13 16:36
 */
@RestController
public class SendController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitAdmin rabbitAdmin;

    @GetMapping("/send")
    public String send(String message) {
        rabbitTemplate.convertAndSend("xieyy-springboot-exchange", "xieyy-springboot-routingkey", message);
        return "success";
    }

    @GetMapping("/config")
    public String config(String message) {
        rabbitTemplate.convertAndSend("xieyy-springboot-exchange", "xieyy-springboot-routingkey", message);
        return "success";
    }

}
