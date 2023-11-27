package com.example.api_web_ban_hang.controllers;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.example.api_web_ban_hang.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.api_web_ban_hang.models.Product;

@RestController
@RequestMapping("/products")
public class ProductApi {

    @Autowired
    private ProductRepository repo;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid Product product) {
        Product savedProduct = repo.save(product);
        URI productURI = URI.create("/products/" + savedProduct.getId());
        return ResponseEntity.created(productURI).body(savedProduct);
    }

    @GetMapping
    public List<Product> list() {
        return repo.findAll();
    }
}