package io.hsjang.livepage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import io.hsjang.livepage.document.Data;
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
    public Flux<Data> getPageData(@DestinationVariable String page) {
        return mqService.getPageQueue(page).mergeWith(mgService.getPageInfo(page)); 
    }

    @MessageMapping("page.{page}.send")
    public Mono<Void> setPageData(@DestinationVariable String page, Map<String,Object> data) throws Exception {
        return mqService.sendPageQueue(page, data);
    }
    
}