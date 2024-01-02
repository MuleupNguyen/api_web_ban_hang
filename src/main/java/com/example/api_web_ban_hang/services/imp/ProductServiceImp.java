package com.example.api_web_ban_hang.services.imp;

import com.example.api_web_ban_hang.dto.ProductDTO;
import com.example.api_web_ban_hang.mapper.MapperProduct;
import com.example.api_web_ban_hang.repos.ProductRepository;
import com.example.api_web_ban_hang.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO findById(long id) {
        return Optional.ofNullable(productRepository.findById(id)
                        .orElse(null))
                .map(MapperProduct::mapperProductToDTO)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + id));
    }


    @Override
    public List<ProductDTO> findByNameProduct(String name) {

        return Optional.ofNullable(productRepository.findByNameContainingIgnoreCase(name))
                .orElse(Collections.emptyList())
                .stream()
                .map(MapperProduct::mapperProductToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAll() {
        return Optional.ofNullable(productRepository.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(MapperProduct::mapperProductToDTO)
                .collect(Collectors.toList());

    }
}
