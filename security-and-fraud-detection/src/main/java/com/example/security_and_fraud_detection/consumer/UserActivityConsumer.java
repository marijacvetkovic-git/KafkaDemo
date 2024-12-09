package com.example.security_and_fraud_detection.consumer;

import com.example.ActivityMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserActivityConsumer {

    @KafkaListener(topics = "user-activity", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(ActivityMessage message) {
        System.out.printf(
                "Proveri korisnika %s  pri obavljanju aktivnosti %s nad proizvodom %s \n",
                message.getUserid(),
                message.getActivity(),
                message.getProductId()
        );    }

}

