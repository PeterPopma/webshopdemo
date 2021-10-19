package com.msdemo.salesservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesController {
    @GetMapping("sales")
    public String getSales() {
        return "Hello world!";
    }
}
