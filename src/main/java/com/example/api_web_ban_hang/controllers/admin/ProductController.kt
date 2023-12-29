package com.example.api_web_ban_hang.controllers.admin

import com.example.api_web_ban_hang.models.entities.ImageProduct
import com.example.api_web_ban_hang.models.entities.Product
import com.example.api_web_ban_hang.repos.ImageProductRepository
import com.example.api_web_ban_hang.repos.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.time.LocalDateTime
import kotlin.io.path.Path
import kotlin.random.Random


@RestController
class ProductController {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var imageProductRepository: ImageProductRepository

    @PostMapping("/api/products")
    fun createProductFiles(
        @RequestPart("images") files: List<MultipartFile>,
        @RequestPart("product") product: Product
    ) {
        val savedProduct = productRepository.save(product)

        for (file in files) {
            var fileName = file.hashCode() + Random.nextInt().hashCode()
            if (fileName < 0) fileName = -fileName
            val path = "images/$fileName"

            Files.copy(file.inputStream, Path(path), StandardCopyOption.REPLACE_EXISTING)

            val imageProduct = ImageProduct().apply {
                setPath(path)
                setProduct(savedProduct)
                timeCreated = LocalDateTime.now()
            }

            imageProductRepository.save(imageProduct)
        }
    }

    @GetMapping("/api/product/images/{id:.+}")
    fun getImage(@PathVariable("id") id: String?): ResponseEntity<ByteArray> {
        val image: ByteArray = Files.readAllBytes(File("images/$id").toPath())
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image)
    }
}