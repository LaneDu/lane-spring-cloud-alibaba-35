package com.galaxy.service.impl;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * @author lane
 * @date 2021年06月25日 下午4:52
 */
@EnableBinding(Sink.class)
public class ReceiveMessage {

    @StreamListener(Sink.INPUT)
    public void receiveMessages(Message<String> message) {
        System.out.println("=========接收到的消息：" + message);
    }

}
