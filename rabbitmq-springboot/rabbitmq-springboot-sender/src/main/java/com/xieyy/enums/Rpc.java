package com.xieyy.enums;

/**
 * @author xieyyt
 * @date 2019/12/25 15:51
 */
public class Rpc implements SubscribeType {
    @Override
    public void consume(String message) {
        System.out.println("rpc--" + message);
    }
}
