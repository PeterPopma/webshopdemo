package com.msdemo.webshopsimulationservice.config;

import com.msdemo.webshopsimulationservice.kafka.Topics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topicOrder() {
        return TopicBuilder.name(Topics.TOPIC_ORDER).build();
    }
}
