package com.sjin.kafka.service;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sjin.kafka.dto.User;
import com.sjin.kafka.dto.User2;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "exam5", groupId = "foo" , containerFactory = "UserConsumer")
    public void consume(User user) throws IOException {
        System.out.println(String.format("Consumed message : %s", user.toString()));
        System.out.println(String.format("Consumed getFirstName : %s", user.getFirstName()));
    }
    
    @KafkaListener(topics = "exam7", groupId = "foo" , containerFactory = "UserConsumer2")
    public void consume2(User2 user) throws IOException {
    	System.out.println(String.format("Consumed2 message : %s", user.toString()));
    	System.out.println(String.format("Consumed2 getFirstName : %s", user.getAge()));
    }
}