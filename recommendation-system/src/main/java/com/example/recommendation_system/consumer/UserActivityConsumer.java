package com.example.recommendation_system.consumer;

import com.example.ActivityMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserActivityConsumer {

    @KafkaListener(topics = "user-activity", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(ActivityMessage message) {
        System.out.printf(
                "Radi realtime preporuku za korisnika %s, \n zato što je odradio aktivnost %s \n nad proizvodom sa ID-jem: %s \n",
                message.getUserid(),
                message.getActivity(),
                message.getProductId()
        );    }

}
