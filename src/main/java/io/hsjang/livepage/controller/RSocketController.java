package io.hsjang.livepage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import io.hsjang.livepage.pojo.Cmd;
import io.hsjang.livepage.service.MGService;
import io.hsjang.livepage.service.MQService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class RSocketController {

    @Autowired
    MQService mqService;
    
    @Autowired
    MGService mgService;

    @MessageMapping("page.{page}")
    public Flux<Cmd> getPageData(@DestinationVariable String page) {
        return mgService.getPageInfo(page).map(Cmd::of).mergeWith(mqService.getPageQueue(page)); 
    }

    @MessageMapping("page.{page}.send")
    public Mono<Void> setPageData(@DestinationVariable String page, Map<String,Object> data) throws Exception {
        return mqService.sendPageQueue(page, data);
    }
    
}