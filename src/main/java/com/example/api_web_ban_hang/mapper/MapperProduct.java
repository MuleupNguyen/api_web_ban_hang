package com.example.api_web_ban_hang.mapper;

import com.example.api_web_ban_hang.dto.ImageDTO;
import com.example.api_web_ban_hang.dto.ProductDTO;
import com.example.api_web_ban_hang.dto.SizeDTO;
import com.example.api_web_ban_hang.models.entities.Comment;
import com.example.api_web_ban_hang.models.entities.ImageProduct;
import com.example.api_web_ban_hang.models.entities.Product;
import com.example.api_web_ban_hang.models.entities.SizeProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public class MapperProduct {
    public static ProductDTO mapperProductToDTO(Product product){
        ProductDTO build = ProductDTO.builder()
                .id_product(product.getId())
                .name_product(product.getNameProduct())
                .star_review(product.getStarReview())
                .listed_price(product.getListedPrice())
                .promotional_price(product.getPromotionalPrice())
                .brand(product.getBrand().getNameBrand())
                .type(product.getTypeProduct().getNameType())
                .list_image(mapperImageToDTO(product.getImageProducts()))
                .list_size(mappperSizeToDTO(product.getListSizes()))
                .list_comment(new ArrayList<>(product.getComments()))
                .build();
        return build;
    }
    public static List<ImageDTO> mapperImageToDTO(Set<ImageProduct> list_image){

        return list_image.stream().map(img->{
            return ImageDTO.builder()
                    .id_image(img.getId())
                    .path_image(img.getPath())
                    .build();
        }).collect(Collectors.toList());

    }
    public static List<SizeDTO> mappperSizeToDTO(Set<SizeProduct> list_size){
        return list_size.stream().map(size->{
            return SizeDTO.builder()
                    .name_size(size.getSize().getNameSize())
                    .quantity_available(size.getQuantityAvailable())
                    .build();
        }).collect(Collectors.toList());
    }


}
