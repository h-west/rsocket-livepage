package io.hsjang.livepage.document;

import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.Delivery;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Data extends HashMap<String,Object> {

    private static final long serialVersionUID = 1L;

    public Data(Map<String,Object> m){
        super(m);
    }

    public Data(String k, Object v){
        super();
        put(k, v);
    }

    public static Data of(Delivery delivery){
        return new Data("message",delivery.getBody());
    }

}