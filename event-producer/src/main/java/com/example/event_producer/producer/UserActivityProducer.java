package com.example.event_producer.producer;

import com.example.ActivityMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserActivityProducer {

    private final KafkaTemplate<String, ActivityMessage> kafkaTemplate;

    public UserActivityProducer(KafkaTemplate<String, ActivityMessage> template) {
        kafkaTemplate = template;
    }

    public void sendMessage(ActivityMessage message) {
        if (message.getActivity().equals("ordered")) {
            kafkaTemplate.send("user-orders", message.getUserid(), message);

        }
        kafkaTemplate.send("user-activity", message.getUserid(), message);
        System.out.println("User activity: " + message);
    }
}
