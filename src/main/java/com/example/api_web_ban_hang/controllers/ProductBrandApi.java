package com.example.api_web_ban_hang.controllers;

import com.example.api_web_ban_hang.models.ResponseObject;
import com.example.api_web_ban_hang.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clothes")
public class ProductBrandApi {
    @Autowired
    ProductRepository product;
    @GetMapping("/ds-ao-nike")
    public ResponseObject getListNike() {
        return new ResponseObject("OK", "Danh sách áo Nike", product.findAllByBrand("NIKE"));
    }
    @GetMapping("/ds-ao-adidas")
    public ResponseObject getListAdidas() {
        return new ResponseObject("OK", "Danh sách áo Adidas", product.findAllByBrand("ADIDAS"));
    }
    @GetMapping("/ds-ao-puma")
    public ResponseObject getListPuma() {
        return new ResponseObject("OK", "Danh sách áo Puma", product.findAllByBrand("PUMA"));
    }
}
