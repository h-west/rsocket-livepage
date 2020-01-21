package io.hsjang.livepage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hsjang.livepage.document.Page;
import io.hsjang.livepage.service.MGService;
import reactor.core.publisher.Mono;


@RestController
public class TestApiController {

    @Autowired
    MGService mgService;

    @GetMapping("/api/savepage")
    public Mono<Page> savePage() {
        return mgService.savePageInfo(new Page("name","type",List.of(Map.of("k1", "v1")), ""));
    }
    
    @GetMapping("/api/page")
    public Mono<Page> page() {
        return mgService.getPageInfo("test1");
    }
}