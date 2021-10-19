package com.msdemo.webshopsimulationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Order {
    UUID id;
    String customer;
    Instant dateCreated;
    List<OrderItem> orderItemList;
}
