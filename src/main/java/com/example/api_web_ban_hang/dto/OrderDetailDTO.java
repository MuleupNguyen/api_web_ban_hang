package com.example.api_web_ban_hang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    private Long id;
    private String nameSize;
    private BigDecimal price;
    private  int  quantity;
    private ProductDTO productDTO;




}
