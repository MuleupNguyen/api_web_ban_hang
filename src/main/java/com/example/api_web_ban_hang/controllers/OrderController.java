package com.example.api_web_ban_hang.controllers;

import com.example.api_web_ban_hang.models.OrderRequest;
import com.example.api_web_ban_hang.models.ResponseObject;
import com.example.api_web_ban_hang.models.entities.Order;
import com.example.api_web_ban_hang.models.entities.OrderDetail;
import com.example.api_web_ban_hang.models.entities.Product;
import com.example.api_web_ban_hang.repos.OrderDetailRepository;
import com.example.api_web_ban_hang.repos.OrderRepository;
import com.example.api_web_ban_hang.repos.ProductRepository;
import com.example.api_web_ban_hang.services.interfaces.OrderDetailService;
import com.example.api_web_ban_hang.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Transactional
    @PostMapping("/create-order")
    public ResponseEntity<ResponseObject> create(@RequestBody @Valid OrderRequest orderRequest) {
        Order order = orderService.addOrder(orderRequest);


        orderRequest.getList_order_detail().stream().forEach(o -> {
            orderDetailService.addOrderDetail(o, order);
        });
        return Optional.of(ResponseEntity.ok().body(
                new ResponseObject(
                        HttpStatus.OK.name(),
                        HttpStatus.OK.getReasonPhrase(),
                        orderRequest
                )
        )).get();
    }

}
