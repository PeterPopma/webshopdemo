package com.msdemo.webshopsimulationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderItem {
    String product;
    float price;
    int quantity;
}
