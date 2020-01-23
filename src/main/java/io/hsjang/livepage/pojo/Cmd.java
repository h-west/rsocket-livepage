package io.hsjang.livepage.pojo;


import com.rabbitmq.client.Delivery;

import io.hsjang.livepage.document.Page;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cmd {

    String cmd;
    Object data;

    public static Cmd of(Page page){
        return new Cmd("page", page);
    }

    public static Cmd of(Delivery delivery){
        return new Cmd("delivery", delivery.getBody());
    }
}