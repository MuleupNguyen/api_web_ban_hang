package com.example.api_web_ban_hang.repos;

import com.example.api_web_ban_hang.models.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
