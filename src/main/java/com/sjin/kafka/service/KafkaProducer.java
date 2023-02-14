package com.sjin.kafka.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sjin.kafka.dto.User;
import com.sjin.kafka.dto.User2;

@Service
public class KafkaProducer {
    private static final String TOPIC = "exam5";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        System.out.println(String.format("Produce message : %s", message));
        User user =  new User();
        user.setUserId(40);
        user.setFirstName("SangJin");
        user.setLastName("Lee");
        
        this.kafkaTemplate.send(TOPIC, user);
        
        User2 user2 =  new User2();
        user2.setUserId(40);
        user2.setFirstName("SangJin");
        user2.setLastName("Lee");
        user2.setAge(10);
        
        this.kafkaTemplate.send("exam7", user);
    }
}
