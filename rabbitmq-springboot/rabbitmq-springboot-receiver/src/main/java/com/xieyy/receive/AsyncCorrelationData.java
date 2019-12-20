package com.xieyy.receive;

import org.springframework.amqp.rabbit.connection.CorrelationData;

import java.util.concurrent.FutureTask;

/**
 * @author xieyyt
 * @date 2019/12/19 20:51
 */
public class AsyncCorrelationData extends CorrelationData {

    public AsyncCorrelationData() {
        super();
    }

    public AsyncCorrelationData(String id) {
        super(id);
    }

    public volatile Boolean ack = false;
    public volatile String cause = null;
    public volatile FutureTask<Boolean> task = new FutureTask<>(this::call);

    private Boolean call() {
        return ack;
    }
}
