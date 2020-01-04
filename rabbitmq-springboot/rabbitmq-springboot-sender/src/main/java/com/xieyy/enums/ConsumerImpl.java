package com.xieyy.enums;

/**
 * @author xieyyt
 * @date 2019/12/25 15:53
 */
public class ConsumerImpl implements Consumer {

    private SubscribeType type;

    public ConsumerImpl(SubscribeType type) {
        this.type = type;
    }

    void consume(String message) {
        type.consume(message);
    }

    public static void main(String[] args) {
        new ConsumerImpl(new Task()).consume(">>>>test");
    }
}
