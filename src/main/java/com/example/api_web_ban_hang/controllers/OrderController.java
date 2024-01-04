package com.example.api_web_ban_hang.controllers;

import com.example.api_web_ban_hang.dto.OrderDTO;
import com.example.api_web_ban_hang.dto.OrderDetailDTO;
import com.example.api_web_ban_hang.models.ResponseObject;
import com.example.api_web_ban_hang.models.entities.Order;
import com.example.api_web_ban_hang.models.entities.OrderDetail;
import com.example.api_web_ban_hang.repos.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private  OrderService orderService;
  
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


//    http://localhost:8080/api/orders?phoneNumbers=0357695591
    @GetMapping("/api/orders")
    public ResponseEntity<ResponseObject> getOrdersByPhoneNumber(@RequestParam(name="phoneNumbers") String phoneNumber) {
        List<OrderDTO> orders = orderService.getOrdersByPhoneNumber(phoneNumber);
        return Optional.of(ResponseEntity.ok(
                ResponseObject.builder().message(HttpStatus.OK.getReasonPhrase())
                                .status( HttpStatus.OK.name()).object(orders).build()))
                .get()
                ;
    }



    @GetMapping("/api/orders/status")
    public ResponseEntity<ResponseObject> getOrderByStatus(@RequestParam(name="status") Integer status) {
        List<OrderDTO> orders = orderService.getOrdersByStatus(status);
        return Optional.of(ResponseEntity.ok(
                        ResponseObject.builder().message(HttpStatus.OK.getReasonPhrase())
                                .status( HttpStatus.OK.name()).object(orders).build()))
                .get()
                ;
    }

    @GetMapping("/api/orders/all")
    public ResponseEntity<ResponseObject> getAllOrder() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return Optional.of(ResponseEntity.ok(
                        ResponseObject.builder().message(HttpStatus.OK.getReasonPhrase())
                                .status( HttpStatus.OK.name()).object(orders).build()))
                .get()
                ;
    }
    @GetMapping("/api/orders/{id}")
    public ResponseEntity<ResponseObject> getOne(@PathVariable Long id) {
        OrderDTO orders = orderService.getOrderById(id);
        return Optional.of(ResponseEntity.ok(
                        ResponseObject.builder().message(HttpStatus.OK.getReasonPhrase())
                                .status( HttpStatus.OK.name()).object(orders).build()))
                .get()
                ;
    }

        @GetMapping("/api/order-details/{orderDetailId}")
    public ResponseEntity<ResponseObject> getOrderDetailById(@PathVariable Long orderDetailId) {
        OrderDetailDTO orderDetail = orderService.getOrderDetailById(orderDetailId);

        if (orderDetail != null) {
            return ResponseEntity.ok(ResponseObject.builder()
                    .message(HttpStatus.OK.getReasonPhrase())
                    .status(HttpStatus.OK.name())
                    .object(orderDetail)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseObject.builder()
                    .message("Order Detail not found")
                    .status(HttpStatus.NOT_FOUND.name())
                    .object(null)
                    .build());
        }
    }

    @GetMapping("/api/order-details/order/{orderId}")
    public ResponseEntity<ResponseObject> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        Optional<OrderDetailDTO> orderDetails = Optional.of(orderService.getOrderDetailsByOrderId(orderId));

        if (!orderDetails.isEmpty()) {
            return ResponseEntity.ok(ResponseObject.builder()
                    .message(HttpStatus.OK.getReasonPhrase())
                    .status(HttpStatus.OK.name())
                    .object(orderDetails)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseObject.builder()
                    .message("Order Details not found for the given Order ID")
                    .status(HttpStatus.NOT_FOUND.name())
                    .object(null)
                    .build());
        }
    }
}

