package io.hsjang.livepage.common;

import java.net.InetAddress;
import java.util.UUID;

public class Constants {

    public static String EX_CHANGE_WHEEL = "amq.topic";

    public static String HOST_NAME = "hostname";
    static {
        try{
            HOST_NAME = InetAddress.getLocalHost().getHostName();
        }catch(Exception e){
            HOST_NAME = "" + UUID.randomUUID();
        }
    }

    public static String getGateQueue(){
        return "gate."+HOST_NAME;
    }

    public static String getPageQueue(String page){
        return "page."+page+"."+HOST_NAME;
    }

    public static String getGateRoutingKey(){
        return "gate.";
    }

    public static String getPageRoutingKey(String page){
        return "page."+page+".";
    }
}