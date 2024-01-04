package com.example.api_web_ban_hang.services.interfaces;

import com.example.api_web_ban_hang.dto.ProductDTO;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface IProductService {

    ProductDTO findById(long id);
    List<ProductDTO> findByNameProduct(String name);
    List<ProductDTO> findAll(Pageable pageable);
    List<ProductDTO> findProductByBrandWithOptionSort(String name,Pageable pageable);

}
