package com.example.api_web_ban_hang.repos;

import com.example.api_web_ban_hang.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByIdStatusOrder(Integer id);

    List<Order> findByToPhone(String phone);

    @Transactional
    @Modifying
    @Query(value = "UPDATE orders SET id_status_order = :statusId WHERE id_order = :orderId", nativeQuery = true)
    int updateOrderStatus(@Param("orderId") int orderId, @Param("statusId") int statusId);

    @Query(value = "SELECT IFNULL(SUM(total_price), 0) FROM orders WHERE DATE_FORMAT(time_order, '%Y-%m') = :year_and_month", nativeQuery = true)
    double getTotalPriceByYearAndMonth(@Param("year_and_month") String yearAndMonth);
}

