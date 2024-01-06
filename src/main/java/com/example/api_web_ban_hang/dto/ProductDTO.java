package com.example.api_web_ban_hang.dto;

import com.example.api_web_ban_hang.models.entities.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class ProductDTO {
    private long id_product;
    private String name_product;
    private int star_review;
    private BigDecimal listed_price;
    private BigDecimal promotional_price;
    private List<ImageDTO> list_image;
    private List<SizeDTO> list_size;
    private List<CommentDTO> list_comment;
    private String brand;
    private String type;



}
