package io.hsjang.livepage.service;

import java.net.InetAddress;
import java.util.UUID;

import com.rabbitmq.client.Delivery;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.rabbitmq.Receiver;

@Service
public class MQService implements InitializingBean{

    String HOST_NAME = ".hostname";

    TopicExchange exchange = new TopicExchange("windmill"); // base

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    Receiver receiver;

    @Bean
    Flux<Delivery> gate(Receiver receiver) {
        String qName  = createQueueNBindGateExchange("gate");
        return receiver.consumeNoAck(qName).publish().autoConnect();
    }

    public String createQueueNBindGateExchange(String name) {
        String qName = name + HOST_NAME;
        Queue q = new Queue(qName, false, false, true);
        amqpAdmin.declareQueue(q);
        amqpAdmin.declareBinding(BindingBuilder.bind(q).to(exchange).with(name+".*"));
        return qName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try{
            HOST_NAME = "."+InetAddress.getLocalHost().getHostName();
        }catch(Exception e){
            HOST_NAME = "."+UUID.randomUUID();
        }
        // exchange
        amqpAdmin.declareExchange(exchange);
    }

}