package com.xieyy.enums;

/**
 * @author xieyyt
 * @date 2019/12/25 15:47
 */
public class App implements SubscribeType {
    @Override
    public void consume(String message) {
        System.out.println("app--" + message);
    }
}
