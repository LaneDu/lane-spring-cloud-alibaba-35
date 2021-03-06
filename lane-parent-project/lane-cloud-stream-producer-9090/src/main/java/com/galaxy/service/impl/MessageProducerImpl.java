package com.galaxy.service.impl;

import com.galaxy.service.IMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

/**
 * // Source.class里面就是对输出通道的定义（这是Spring Cloud Stream内置的通道封装）
 * @author lane
 * @date 2021年06月25日 下午4:29
 */
@EnableBinding(Source.class)
public class MessageProducerImpl implements IMessageProducer {

    // 将MessageChannel的封装对象Source注入到这里使用
    @Autowired
    private Source source;
    @Override
    public String sendMessage(String message) {
        // 向mq中发送消息（并不是直接操作mq，应该操作的是spring cloud stream）
        // 使用通道向外发出消息(指的是Source里面的output通道)
        source.output().send(MessageBuilder.withPayload(message).build());
        System.out.println("send message start。。。");
        return null;
    }
}
