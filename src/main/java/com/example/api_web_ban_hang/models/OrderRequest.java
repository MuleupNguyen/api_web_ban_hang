package com.example.api_web_ban_hang.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
public class OrderRequest {
    private String name_customer;
    private String phone;
    private String email_customer;
    private AddressRequest to_address;
    private String note;
    private BigDecimal ship_price;
    private BigDecimal order_value;
    private BigDecimal total_price;
    private Set<OrderDetailRequest> list_order_detail = new HashSet<>();
}
