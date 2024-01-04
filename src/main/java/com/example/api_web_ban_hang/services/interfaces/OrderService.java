package com.example.api_web_ban_hang.services.interfaces;

import com.example.api_web_ban_hang.models.OrderRequest;
import com.example.api_web_ban_hang.models.entities.Comment;
import com.example.api_web_ban_hang.models.entities.Order;
import org.springframework.data.jpa.repository.Modifying;

public interface OrderService {
    @Modifying
    Order addOrder(OrderRequest orderRequest);
}
