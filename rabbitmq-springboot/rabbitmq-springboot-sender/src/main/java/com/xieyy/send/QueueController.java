package com.xieyy.send;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xieyyt
 * @date 2019/12/19 15:52
 */
@RestController
public class QueueController {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @PostMapping("/publish")
    public String publish(String exchangeName, String routingKey, String queueName) {
        Exchange exchange = new TopicExchange(exchangeName, false, true, null);
        rabbitAdmin.declareExchange(exchange);
        Queue queue = new Queue(queueName, false, false, true);
        rabbitAdmin.declareQueue(queue);
        Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, routingKey, null);
        rabbitAdmin.declareBinding(binding);
        return "success";
    }

    @PostMapping("/delete")
    public String delete(String exchangeName, String queueName) {
        rabbitAdmin.deleteExchange(exchangeName);
        rabbitAdmin.deleteQueue(queueName);
        return "success";
    }

}
