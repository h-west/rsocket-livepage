package io.hsjang.livepage.controller;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;

import io.hsjang.livepage.service.MQService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.Sender;

@Controller
public class TestSocketController {

    Map<String, Flux<Delivery>> pool = new HashMap<String, Flux<Delivery>>();

    @Autowired
    MQService mqService;

    @Autowired
    Receiver receiver;

    @Autowired
    Sender sender;

    @Autowired
    Flux<Delivery> gate;

    @Autowired
    Mono<RSocketRequester> requester;

    @MessageMapping("data.get")
    public Flux<Delivery> getDatas(Map<String,Object> data) {
        return getQueue((String)data.get("page")).mergeWith(gate); 
    }

    @MessageMapping("data.set")
    public Mono<Void> call(Map<String,Object> data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        OutboundMessage msg = new OutboundMessage("windmill","gate.", mapper.writeValueAsBytes(data));
        System.out.println(data);
        sender.send(Mono.just(msg)).subscribe(System.out::println);
        return Mono.empty();
    }

    public Flux<Delivery> getQueue(String page){
        if(!pool.containsKey(page)){
            String qName = mqService.createQueueNBindGateExchange("page."+page);
            Flux<Delivery> pageFlux = receiver.consumeNoAck(qName).publish().autoConnect();
            pool.put(page, pageFlux);
        }
        return pool.get(page);
    }

}