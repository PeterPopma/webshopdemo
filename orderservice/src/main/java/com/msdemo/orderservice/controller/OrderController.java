package com.msdemo.orderservice.controller;

import com.msdemo.orderservice.dto.Order;
import com.msdemo.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class OrderController {
    @Autowired
    OrderService órderService;

    @GetMapping("orders/{option}")
    public List<Order> getOrders(@PathVariable String option) {
        return órderService.getOrders(option);
    }
}
