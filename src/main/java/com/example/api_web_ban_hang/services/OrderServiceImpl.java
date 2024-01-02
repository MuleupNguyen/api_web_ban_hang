package com.example.api_web_ban_hang.services;

import com.example.api_web_ban_hang.models.entities.Order;
import com.example.api_web_ban_hang.repos.OrderRepository;
import com.example.api_web_ban_hang.repos.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.orElse(null);
    }

    @Override
    public List<Order> getOrdersByPhoneNumber(String phoneNumber) {
        Optional<List<Order>> optionalOrder = Optional.ofNullable(orderRepository.findByToPhone(phoneNumber));
        return optionalOrder.orElse(null);
    }

    @Override
    public List<Order> getOrdersByStatus(Integer status) {
        Optional<List<Order>> optionalOrder = Optional.ofNullable(orderRepository.findByIdStatusOrder(status));
        return optionalOrder.orElse(null);
    }
}