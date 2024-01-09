package com.example.api_web_ban_hang.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long idOrder;
    private List<OrderDetailDTO> list_order;
    private String address;
    private String district;
    private String nameCustomer;
    private String phoneCustomer;
    private String addressCustomer;
    private BigDecimal shipPrice;
    private BigDecimal orderValue;
    private BigDecimal totalPrice;
    private Integer status;
    private String timeOrder;





}
