package com.example.api_web_ban_hang.models;

import com.example.api_web_ban_hang.models.entities.Order;
import com.example.api_web_ban_hang.models.entities.Product;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
@Getter
@Setter
@Builder
public class OrderDetailRequest {
    private Long id_product;

    private String nameSize;

    private BigDecimal price;

    private Integer quantity;
}
