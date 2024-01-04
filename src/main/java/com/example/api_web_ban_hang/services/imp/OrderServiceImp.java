package com.example.api_web_ban_hang.services.imp;

import com.example.api_web_ban_hang.models.OrderRequest;
import com.example.api_web_ban_hang.models.entities.Order;
import com.example.api_web_ban_hang.repos.OrderRepository;
import com.example.api_web_ban_hang.services.interfaces.OrderDetailService;
import com.example.api_web_ban_hang.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailService orderDetailService;
    @Override
    public Order addOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setIdStatusOrder(1);
        order.setToName(orderRequest.getName_customer());
        order.setToPhone(orderRequest.getPhone());
        order.setEmailCustomer(orderRequest.getEmail_customer());
        order.setToAddress(orderRequest.getTo_address().getAddress());
        order.setToWardName(orderRequest.getTo_address().getWard_name());
        order.setToDistrictName(orderRequest.getTo_address().getDistrict_name());
        order.setToProvinceName(orderRequest.getTo_address().getProvince_name());
        order.setToWardId(orderRequest.getTo_address().getWard_id());
        order.setToDistrictId(orderRequest.getTo_address().getDistrict_id());
        order.setToProvinceId(orderRequest.getTo_address().getProvince_id());
        order.setNote(orderRequest.getNote());
        order.setShipPrice(orderRequest.getShip_price());
        order.setOrderValue(orderRequest.getOrder_value());
        order.setTimeOrder(LocalDateTime.now());
        order.setTotalPrice(orderRequest.getTotal_price());

        return Optional.of(orderRepository.save(order))
                .orElseThrow(() -> new RuntimeException("Failed to add order"));
    }
}
