package com.example.api_web_ban_hang.controllers;

import com.example.api_web_ban_hang.models.entities.Order;
import com.example.api_web_ban_hang.repos.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private  OrderService orderService;


//    http://localhost:8080/api/orders?phoneNumbers=0357695591
    @GetMapping("/api/orders")
    public List<Order> getOrdersByPhoneNumber(@RequestParam(name="phoneNumbers") String phoneNumber) {
        List<Order> orders = orderService.getOrdersByPhoneNumber(phoneNumber);
        if (orders == null || orders.isEmpty()) {

            return new ArrayList<>();
        }

        return orders;
    }


}
