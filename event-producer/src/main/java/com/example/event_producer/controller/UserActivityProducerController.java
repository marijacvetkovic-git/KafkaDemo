package com.example.event_producer.controller;

import com.example.event_producer.producer.UserActivityProducer;
import com.example.ActivityMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserActivityProducerController {
    private final UserActivityProducer producer;

    public UserActivityProducerController(UserActivityProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/sendToTopic")
    public void sendToTopic(@RequestBody ActivityMessage userActivity){
        producer.sendMessage(userActivity);
    }
}
