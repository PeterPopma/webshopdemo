package com.msdemo.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    UUID id;
    String customer;
    Instant dateCreated;
    List<OrderItem> orderItemList;
}
