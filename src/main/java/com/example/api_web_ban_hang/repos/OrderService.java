package com.example.api_web_ban_hang.repos;

import com.example.api_web_ban_hang.models.entities.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long orderId);

    List<Order> getOrdersByPhoneNumber(String phoneNumber);
    List<Order> getOrdersByStatus(Integer status);
}