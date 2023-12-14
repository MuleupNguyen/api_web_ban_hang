package com.example.api_web_ban_hang.controllers;

import com.example.api_web_ban_hang.models.ResponseObject;
import com.example.api_web_ban_hang.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/products")
public class ProductApi {
    // Ví dụ
    @Autowired
    private ProductRepository repo;

    @GetMapping
    public ResponseObject findAllProducts() {
        return new ResponseObject("OK", "Lấy ra danh sách sản phẩm!", repo.findAll());
    }

}