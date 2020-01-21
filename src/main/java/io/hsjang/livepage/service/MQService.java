package io.hsjang.livepage.service;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Delivery;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;

import io.hsjang.livepage.common.Constants;
import io.hsjang.livepage.pojo.Cmd;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.Sender;

@Service
public class MQService implements InitializingBean{

    String HOST_NAME = ".hostname";

    TopicExchange exchange = new TopicExchange(Constants.EX_CHANGE_WHEEL); // base

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    Receiver receiver;

    @Autowired
    Sender sender;

    @Autowired
    Flux<Delivery> gate;

    @Autowired
    Mono<RSocketRequester> requester;

    @Autowired 
    ObjectMapper mapper;

    Map<String, Flux<Cmd>> pool = new HashMap<String, Flux<Cmd>>();

    /**
     * gate queue 생성 및 바인드
     * @param receiver
     * @return
     */
    @Bean
    Flux<Delivery> gate(Receiver receiver) {
        String qName = Constants.getGateQueue();
        Queue q = new Queue(qName, false, false, true);
        amqpAdmin.declareQueue(q);
        amqpAdmin.declareBinding(BindingBuilder.bind(q).to(exchange).with(Constants.getGateRoutingKey()+"*"));
        return receiver.consumeNoAck(qName).publish().autoConnect();
    }

    /**
     * 모든 호스트의 page(null=>gate) queue 에 메시지 전송
     * @param page
     * @param message
     * @return
     */
    public Mono<Void> sendPageQueue(String page, Object message){
        return sender.send(Mono.just(getPageOutMessage(page, message)));
    }

    /**
     * 해당 page(null=>gate) 라우팅키(모든호스트큐)로 메시지 생성
     * @param page
     * @param message
     * @return
     */
    public OutboundMessage getPageOutMessage(String page, Object message){
        byte[] out = null;
        try {
            out = mapper.writeValueAsBytes(message);
        } catch (Exception e) {
            //
        }
        return new OutboundMessage(Constants.EX_CHANGE_WHEEL, (page!=null?Constants.getPageRoutingKey(page):Constants.getGateRoutingKey()), out);
    }

    /**
     * Page Queue 가져오기
     * pool에 있으면 가져오고, 없으면 만들고 바인드 후 가져온다.
     * @param page
     * @return
     */ 
    public Flux<Cmd> getPageQueue(String page){
        if(!pool.containsKey(page)){
            String qName = Constants.getPageQueue(page);
            Queue q = new Queue(qName, false, false, true);
            amqpAdmin.declareQueue(q);
            amqpAdmin.declareBinding(BindingBuilder.bind(q).to(exchange).with(Constants.getPageRoutingKey(page)+"*"));
            Flux<Cmd> pageFlux = receiver.consumeNoAck(qName).publish().autoConnect().map(Cmd::of);
            pool.put(page, pageFlux);
        }
        return pool.get(page);
    }
    
    /**
     * Init
     * exchange 생성 (없으면)
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        amqpAdmin.declareExchange(exchange);
    }

}