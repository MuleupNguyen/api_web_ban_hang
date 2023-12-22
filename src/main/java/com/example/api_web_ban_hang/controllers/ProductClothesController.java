package com.example.api_web_ban_hang.controllers;

import com.example.api_web_ban_hang.common.StatusProduct;
import com.example.api_web_ban_hang.common.TypeProduct;
import com.example.api_web_ban_hang.dto.PagedResponse_Ver1;
import com.example.api_web_ban_hang.dto.ProductDTO_Ver1;
import com.example.api_web_ban_hang.filter.PaginationFilter;
import com.example.api_web_ban_hang.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller này dùng để handle các API liên quan đến sản phẩm quần áo
 */
@RestController
@RequestMapping("/api/products")
public class ProductClothesController {
    private final ProductService _productService;

    @Autowired
    public ProductClothesController(ProductService productService) {
        this._productService = productService;
    }

    @GetMapping("/ds-ao-da-banh-moi")
    public PagedResponse_Ver1<List<ProductDTO_Ver1>> getListSoccerShirtNew(@RequestParam(defaultValue = "1") int page,
                                                                           @RequestParam(defaultValue = "15") int pageSize) {
        try {
            var validFilter = new PaginationFilter(page, pageSize);
            var data = _productService.getListProductByTypeAndStatus(TypeProduct.AO_DAU, StatusProduct.MOI, validFilter.current_page, validFilter.page_size);
            return new PagedResponse_Ver1<List<ProductDTO_Ver1>>(data, validFilter.current_page, validFilter.page_size, 1000);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
