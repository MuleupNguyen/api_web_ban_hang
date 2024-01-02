package com.example.api_web_ban_hang.controllers;

import com.example.api_web_ban_hang.models.ResponseObject;
import com.example.api_web_ban_hang.models.entities.Order;
import com.example.api_web_ban_hang.repos.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private  OrderService orderService;


//    http://localhost:8080/api/orders?phoneNumbers=0357695591
    @GetMapping("/api/orders")
    public ResponseEntity<ResponseObject> getOrdersByPhoneNumber(@RequestParam(name="phoneNumbers") String phoneNumber) {
        List<Order> orders = orderService.getOrdersByPhoneNumber(phoneNumber);
        return Optional.of(ResponseEntity.ok(
                ResponseObject.builder().message(HttpStatus.OK.getReasonPhrase())
                                .status( HttpStatus.OK.name()).object(orders).build()))
                .get()
                ;
    }



    @GetMapping("/api/orders/status")
    public ResponseEntity<ResponseObject> getOrderByStatus(@RequestParam(name="status") Integer status) {
        List<Order> orders = orderService.getOrdersByStatus(status);
        return Optional.of(ResponseEntity.ok(
                        ResponseObject.builder().message(HttpStatus.OK.getReasonPhrase())
                                .status( HttpStatus.OK.name()).object(orders).build()))
                .get()
                ;
    }

    @GetMapping("/api/orders/all")
    public ResponseEntity<ResponseObject> getAllOrder() {
        List<Order> orders = orderService.getAllOrders();
        return Optional.of(ResponseEntity.ok(
                        ResponseObject.builder().message(HttpStatus.OK.getReasonPhrase())
                                .status( HttpStatus.OK.name()).object(orders).build()))
                .get()
                ;
    }


}
