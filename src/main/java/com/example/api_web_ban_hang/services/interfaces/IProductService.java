package com.example.api_web_ban_hang.services.interfaces;

import com.example.api_web_ban_hang.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    ProductDTO findById(long id);
    List<ProductDTO> findByNameProduct(String name);
    List<ProductDTO> findAll();

}
