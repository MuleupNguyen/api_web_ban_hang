package com.example.api_web_ban_hang.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class này <=> chuỗi Json trả về thông tin của product
 * */
@Data
@Builder
public class ProductDTO_Ver1 {
      public long id_product;
      public String name_product;
      public int start_review;
      public BigDecimal listed_price;
      public BigDecimal promotional_price;
      public List<ImageProductDTO_Ver1> list_image;
}
