package com.example.order_processing_1.consumer;

import com.example.ActivityMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class UserOrdersConsumer {

    @KafkaListener(topics = "user-orders", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(ActivityMessage message) {
        System.out.printf("Obradi narudzbinu za korisnika: %s , id narudzbine je : %s \n",
                message.getUserid(),
                message.getProductId()
        );    }

}
