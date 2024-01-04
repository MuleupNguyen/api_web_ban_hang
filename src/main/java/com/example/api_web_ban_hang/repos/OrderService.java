package com.example.api_web_ban_hang.repos;

import com.example.api_web_ban_hang.dto.OrderDTO;
import com.example.api_web_ban_hang.dto.OrderDetailDTO;
import com.example.api_web_ban_hang.models.entities.Order;
import com.example.api_web_ban_hang.models.entities.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDTO> getAllOrders();


    List<OrderDTO> getOrdersByPhoneNumber(String phoneNumber);
    List<OrderDTO> getOrdersByStatus(Integer status);

    OrderDetailDTO getOrderDetailById(Long orderDetailId);
    OrderDetailDTO getOrderDetailsByOrderId(Long orderId);
    OrderDTO getOrderById(Long id);
}