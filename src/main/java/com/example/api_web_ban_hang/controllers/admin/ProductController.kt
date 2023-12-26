package com.example.api_web_ban_hang.controllers.admin

import com.example.api_web_ban_hang.models.entities.Product
import com.example.api_web_ban_hang.repos.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @PostMapping("/api/product/create")
    fun createProduct(product: Product) {
        println(product.nameProduct)
    }
}