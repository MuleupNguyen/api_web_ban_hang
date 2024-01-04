package com.example.api_web_ban_hang.services.imp;

import com.example.api_web_ban_hang.models.OrderDetailRequest;
import com.example.api_web_ban_hang.models.entities.Order;
import com.example.api_web_ban_hang.models.entities.OrderDetail;
import com.example.api_web_ban_hang.repos.OrderDetailRepository;
import com.example.api_web_ban_hang.repos.ProductRepository;
import com.example.api_web_ban_hang.services.interfaces.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderDetailServiceImp implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public OrderDetail addOrderDetail(OrderDetailRequest orderDetailRequest, Order order) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(Optional.of(productRepository.findById(orderDetailRequest.getId_product())).orElse(Optional.empty()).get());
        orderDetail.setNameSize(orderDetailRequest.getNameSize());
        orderDetail.setQuantity(orderDetailRequest.getQuantity());
        orderDetail.setPrice(orderDetailRequest.getPrice());
        orderDetail.setOrder(order);
        System.out.println();
        return Optional.of(orderDetailRepository.save(orderDetail))
                .orElseThrow(() -> new RuntimeException("Failed to add order detail"));
    }
}
