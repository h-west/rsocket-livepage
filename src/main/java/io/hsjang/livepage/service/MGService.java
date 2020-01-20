package io.hsjang.livepage.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import io.hsjang.livepage.document.Data;
import reactor.core.publisher.Mono;

@Service
public class MGService {

    @Autowired
    ReactiveMongoTemplate template;

    public Mono<Data> getPageInfo(String pageId){
        return template.find(new BasicQuery("{\"id\":\""+pageId+"\"}"), Data.class, "page").single();
    }
    
    public Mono<Data> savePageInfo(Data page){
        return template.save(page,"page");
    }

}