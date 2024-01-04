package com.example.api_web_ban_hang.repos;

import com.example.api_web_ban_hang.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByIdStatusOrder(Integer id);
}