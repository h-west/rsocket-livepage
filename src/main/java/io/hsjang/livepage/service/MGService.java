package io.hsjang.livepage.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import io.hsjang.livepage.document.Page;
import reactor.core.publisher.Mono;

@Service
public class MGService {

    @Autowired
    ReactiveMongoTemplate template;

    public Mono<Page> getPageInfo(String pageId){
        return template.findOne(new BasicQuery("{\"name\":\""+pageId+"\"}"), Page.class);
    }
    
    public Mono<Page> savePageInfo(Page page){
        return template.save(page,"page");
    }

}