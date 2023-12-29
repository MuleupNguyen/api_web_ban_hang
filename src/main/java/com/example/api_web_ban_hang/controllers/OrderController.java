package com.example.api_web_ban_hang.controllers;

import com.example.api_web_ban_hang.models.entities.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController


public class OrderController {
    @GetMapping("/api/orders")
    public List<Order> getOrder(@RequestParam String phoneNumber){
        ArrayList<Order> list = new ArrayList<>();

        Order order= new Order();
        order.setTimeOrder(LocalDateTime.now());

        System.out.print(phoneNumber);





        list.add(order);


        return list;

    }
}
