package io.hsjang.livepage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hsjang.livepage.service.MGService;
import reactor.core.publisher.Mono;


@RestController
public class TestApiController {

    @Autowired
    MGService mgService;

    @GetMapping("/api/savepage")
    public Mono<Data> savePage() {
        return mgService.savePageInfo(new Data(Map.of("test1", 1)));
    }
    
}