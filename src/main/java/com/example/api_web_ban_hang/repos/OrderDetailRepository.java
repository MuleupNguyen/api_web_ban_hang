package com.example.api_web_ban_hang.repos;

import com.example.api_web_ban_hang.models.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    static List<OrderDetail> findByOrderId(Long orderId) {
        return null;
    }

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
