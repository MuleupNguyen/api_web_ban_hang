package com.example.api_web_ban_hang.services.interfaces;

import com.example.api_web_ban_hang.models.OrderDetailRequest;
import com.example.api_web_ban_hang.models.entities.Order;
import com.example.api_web_ban_hang.models.entities.OrderDetail;
import org.springframework.data.jpa.repository.Modifying;

public interface OrderDetailService {
    @Modifying
    OrderDetail addOrderDetail(OrderDetailRequest orderDetailRequest, Order order);
}
