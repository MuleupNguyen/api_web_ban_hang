package com.example.api_web_ban_hang.controllers;

import com.example.api_web_ban_hang.models.OrderRequest;
import com.example.api_web_ban_hang.models.ResponseObject;
import com.example.api_web_ban_hang.models.entities.Order;
import com.example.api_web_ban_hang.models.entities.OrderDetail;
import com.example.api_web_ban_hang.models.entities.Product;
import com.example.api_web_ban_hang.repos.OrderDetailRepository;
import com.example.api_web_ban_hang.repos.OrderRepository;
import com.example.api_web_ban_hang.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepo;

    @Autowired
    OrderDetailRepository orderDetailRepo;

    @Autowired
    ProductRepository productRepo;

    @Transactional
    @RequestMapping("/create-order")
    public ResponseObject create(@RequestBody @Valid OrderRequest orderRequest) {
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
        orderRepo.save(order);

        orderRequest.getList_order_detail().stream().forEach(o -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(productRepo.findProductById(o.getId_product()));
            orderDetail.setNameSize(o.getNameSize());
            orderDetail.setQuantity(o.getQuantity());
            orderDetail.setPrice(o.getPrice());
            orderDetail.setOrder(order);
            orderDetailRepo.save(orderDetail);
        });
        return new ResponseObject("OK", "Tạo đơn hàng thành công!", orderRequest);
    }

}
