package com.example.event_producer.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfiguration {
    @Bean
    public NewTopic userActivityTopic(){
        return TopicBuilder.name("user-activity").partitions(2).build();
    }

    @Bean
    public NewTopic userOrdersTopic() {
        return TopicBuilder.name("user-order").partitions(2).build();
    }
}
