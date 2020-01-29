package io.hsjang.livepage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import io.hsjang.livepage.service.MGService;


@RestController
public class TestApiController {

    @Autowired
    MGService mgService;

    // @GetMapping("/api/savepage")
    // public Mono<Page> savePage() {
    //     return mgService.savePageInfo(new Page("name","type",List.of(Map.of("k1", "v1")), ""));
    // }
    
    // @GetMapping("/api/page")
    // public Mono<Page> page() {
    //     return mgService.getPageInfo("test1");
    // }
}