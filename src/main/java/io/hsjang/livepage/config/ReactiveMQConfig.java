package io.hsjang.livepage.config;

import javax.annotation.PreDestroy;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;
import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.ReceiverOptions;
import reactor.rabbitmq.Sender;
import reactor.rabbitmq.SenderOptions;

@Configuration
public class ReactiveMQConfig{

    @Autowired
    Mono<Connection> connectionMono;

    @PreDestroy
    public void close() throws Exception {
        connectionMono.block().close();
    }

    @Bean
    Mono<Connection> connectionMono() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // connectionFactory.setHost("54.180.147.13");
        // connectionFactory.setPort(5672);
        // connectionFactory.setUsername("guest");
        // connectionFactory.setPassword("guest");
        return Mono.fromCallable(() -> connectionFactory.newConnection("reactor-rabbit")).cache();
    }
	
    @Bean
    Sender sender() {
        return RabbitFlux.createSender(new SenderOptions().connectionMono(connectionMono));
    }

    @Bean
    Receiver receiver() {
        return RabbitFlux.createReceiver(new ReceiverOptions().connectionMono(connectionMono));
    }

}