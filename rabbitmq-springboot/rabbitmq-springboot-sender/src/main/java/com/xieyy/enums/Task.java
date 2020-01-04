package com.xieyy.enums;

/**
 * @author xieyyt
 * @date 2019/12/25 15:50
 */
public class Task implements SubscribeType {
    @Override
    public void consume(String message) {
        System.out.println("task--" + message);
    }
}
