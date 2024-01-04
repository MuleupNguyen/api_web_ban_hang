package com.example.api_web_ban_hang.services;

import com.example.api_web_ban_hang.dto.OrderDTO;
import com.example.api_web_ban_hang.dto.OrderDetailDTO;
import com.example.api_web_ban_hang.mapper.MapperOrder;
import com.example.api_web_ban_hang.mapper.MapperOrderDetail;
import com.example.api_web_ban_hang.models.OrderRequest;
import com.example.api_web_ban_hang.models.entities.Order;
import com.example.api_web_ban_hang.models.entities.OrderDetail;
import com.example.api_web_ban_hang.repos.OrderDetailRepository;
import com.example.api_web_ban_hang.repos.OrderRepository;
import com.example.api_web_ban_hang.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(MapperOrder::mapperOrdertoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        return MapperOrder.mapperOrdertoDTO(optionalOrder.get());
    }

    @Override
    public List<OrderDTO> getOrdersByPhoneNumber(String phoneNumber) {
        Optional<List<Order>> optionalOrder = Optional.ofNullable(orderRepository.findByToPhone(phoneNumber));
        return optionalOrder.get().stream()
                .map(MapperOrder::mapperOrdertoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByStatus(Integer status) {
        Optional<List<Order>> optionalOrder = Optional.ofNullable(orderRepository.findByIdStatusOrder(status));
        return optionalOrder.get().stream()
                .map(MapperOrder::mapperOrdertoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailDTO getOrderDetailById(Long orderDetailId) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(orderDetailId);
        return MapperOrderDetail.mapOrderDetailToDTO(optionalOrderDetail.get());
    }

    @Override
    public OrderDetailDTO getOrderDetailsByOrderId(Long orderId) {
        return MapperOrderDetail.mapOrderDetailToDTO(orderDetailRepository.findById(orderId).get());
    }

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