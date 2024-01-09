package com.example.api_web_ban_hang.controllers.admin

import com.example.api_web_ban_hang.mapper.admin.ProductDTO
import com.example.api_web_ban_hang.mapper.admin.toProduct
import com.example.api_web_ban_hang.mapper.admin.toProductDTO
import com.example.api_web_ban_hang.repos.ImageProductRepository
import com.example.api_web_ban_hang.repos.ProductRepository
import com.example.api_web_ban_hang.repos.SizeProductRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.mizosoft.methanol.*
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpResponse


@RestController
class ProductController(
    private val sizeProductRepository: SizeProductRepository,
    private val productRepository: ProductRepository,
    private val imageProductRepository: ImageProductRepository
) {

    @GetMapping("/api/admin/products-amount")
    fun getProductsAmount(): Long = productRepository.count()

    @GetMapping("/api/admin/products")
    fun getProducts(pageable: Pageable?): List<ProductDTO> {
        return productRepository.findAll(pageable ?: Pageable.unpaged()).toList().map { it.toProductDTO() }
    }

    @PostMapping("/api/products")
    fun createProduct(
        @RequestPart("images") files: List<MultipartFile>?,
        @RequestPart("product") productDTO: ProductDTO
    ) {
        updateProduct(files, productDTO)
    }

    @PutMapping("/api/products")
    fun updateProduct(
        @RequestPart("images", required = false) files: List<MultipartFile>?,
        @RequestPart("product") productDto: ProductDTO
    ) {
        val savedProduct = productRepository.save(productDto.toProduct())
        sizeProductRepository.deleteByProduct_Id(savedProduct.id)
        productDto.sizes?.let {
            for (size in it) {
                sizeProductRepository.insertSize(savedProduct.id, size.name, size.quantity ?: 0)
            }
        }

        imageProductRepository.deleteByProduct_Id(savedProduct.id)
        files?.let { multipartFiles ->
            for (file in multipartFiles) {
                val imagePart = MoreBodyPublishers.ofMediaType(BodyPublishers.ofInputStream { file.inputStream }, MediaType.IMAGE_ANY)
                val multipartBody = MultipartBodyPublisher.newBuilder()
                    .formPart("image", file.bytes.hashCode().toString(), MoreBodyPublishers.ofMediaType(imagePart, MediaType.IMAGE_ANY))
                    .build()
                val request = MutableRequest.POST("https://api.imgbb.com/1/upload?key=7cee02feb70a6ee7728e0e025519ebd8", multipartBody)
                val response = Methanol.create().send(request, HttpResponse.BodyHandlers.ofString())
                val map = ObjectMapper().readValue(response.body(), Map::class.java)
                imageProductRepository.insertImage((map["data"] as Map<*, *>)["url"].toString(), savedProduct.id)
            }
        }
    }

    @DeleteMapping("/api/product/{id}")
    fun deleteProduct(@PathVariable("id") id: Long) = productRepository.deleteById(id)
}