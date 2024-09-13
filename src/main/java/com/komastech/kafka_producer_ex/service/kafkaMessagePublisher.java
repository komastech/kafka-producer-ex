package com.komastech.kafka_producer_ex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class kafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessageToTopic(String message) {
        CompletableFuture<SendResult<String, Object>> future = template.send("komastech-top3", message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent msg=["+message+
                        "] with offset=["+result.getRecordMetadata().offset()+"]");
            } else {
                System.out.println("error : "+ex.getMessage());
            }
        });
    }
}
