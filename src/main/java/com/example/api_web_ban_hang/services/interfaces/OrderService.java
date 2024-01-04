package com.example.api_web_ban_hang.services.interfaces;

import com.example.api_web_ban_hang.dto.OrderDTO;
import com.example.api_web_ban_hang.dto.OrderDetailDTO;
import com.example.api_web_ban_hang.models.OrderRequest;
import com.example.api_web_ban_hang.models.entities.Order;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface OrderService {
    @Modifying
    Order addOrder(OrderRequest orderRequest);

    List<OrderDTO> getAllOrders();

    List<OrderDTO> getOrdersByPhoneNumber(String phoneNumber);

    List<OrderDTO> getOrdersByStatus(Integer status);

    OrderDetailDTO getOrderDetailById(Long orderDetailId);

    OrderDetailDTO getOrderDetailsByOrderId(Long orderId);

    OrderDTO getOrderById(Long id);
}
