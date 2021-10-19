package com.msdemo.webshopsimulationservice.service;

import com.msdemo.webshopsimulationservice.dto.Order;
import com.msdemo.webshopsimulationservice.dto.OrderItem;
import com.msdemo.webshopsimulationservice.kafka.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@EnableAsync
@Service
public class OrderGenerationService {
    List<String> productList = new ArrayList<>(List.of("shoe", "playstation", "pants", "socks", "ball", "balloon", "cup", "plate", "spoon",
            "bike", "tv", "couch", "fan", "table", "cable", "book", "pencil"));

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public String generateRandomString(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Async
    @Scheduled(fixedRate = 1000)
    public void OrderGenerationJob() {
        Random random = new Random();
        int numOrders = random.nextInt(5) + 1;
        List<Order> orderList = new ArrayList<Order>();
        for (int i=0; i<numOrders; i++) {
            List<OrderItem> orderItemList = new ArrayList<>();
            int numItems = random.nextInt(10) + 1;
            for (int j = 0; j < numItems; j++) {
                String product = productList.get(random.nextInt(productList.size()));
                orderItemList.add(new OrderItem(product, random.nextInt(5000) / 100, random.nextInt(20)));
            }
            Order order = new Order(UUID.randomUUID(), generateRandomString(10), Instant.now(), orderItemList);
            orderList.add(order);
        }
        kafkaTemplate.send(Topics.TOPIC_ORDER, orderList);
    }
}
