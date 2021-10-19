package com.msdemo.orderservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.msdemo.orderservice.dao.OrderRepository;
import com.msdemo.orderservice.dto.Order;
import com.msdemo.orderservice.kafka.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @KafkaListener(topics = Topics.TOPIC_ORDER)
    public void consume(List<LinkedHashMap> orderList)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        List<Order> orders = objectMapper.convertValue(orderList, new TypeReference<List<Order>>() { });
        for(Order order : orders) {
            orderRepository.save(order);
        }
    }

    public List<Order> getOrders(String option) {
        List<Order> orders = new ArrayList<>();

        if(option.equals("all")) {
            orders = orderRepository.findAll();
        }

        if(option.equals("lastminute")) {
            Instant lastMinute = Instant.now().minus(1, ChronoUnit.MINUTES);
            Query query = new Query();
            query.addCriteria(Criteria.where("dateCreated").gte(lastMinute));
            orders = mongoTemplate.find(query, Order.class);
        }

        return orders;
    }

}
