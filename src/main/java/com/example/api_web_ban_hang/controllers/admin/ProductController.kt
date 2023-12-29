package com.example.api_web_ban_hang.controllers.admin

import com.example.api_web_ban_hang.models.entities.Brand
import com.example.api_web_ban_hang.models.entities.ImageProduct
import com.example.api_web_ban_hang.models.entities.Product
import com.example.api_web_ban_hang.models.entities.TypeProduct
import com.example.api_web_ban_hang.repos.ImageProductRepository
import com.example.api_web_ban_hang.repos.ProductRepository
import com.example.api_web_ban_hang.repos.SizeProductRepository
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

data class SizeDTO(
    val name: String?,
    val quantity: Int?
)

data class ProductDTO(
    val id: Long?,
    val name: String?,
    val gender: Boolean?,
    val star: Int?,
    val brand: Brand?,
    val type: TypeProduct?,
    val listedPrice: Double?,
    val price: Double?,
    val sizes: List<SizeDTO>?,
    val images: List<String>?,
    val timeCreated: LocalDateTime?,
)

@RestController
class ProductController(
    private val sizeProductRepository: SizeProductRepository,
    private val productRepository: ProductRepository,
    private val imageProductRepository: ImageProductRepository
) {

    @GetMapping("/api/admin/products")
    fun getProducts(): List<ProductDTO> {
        return productRepository.findAll().map { product ->
            ProductDTO(
                id = product.id,
                name = product.nameProduct,
                gender = product.idSex == 1,
                star = product.starReview,
                brand = product.brand,
                type = product.typeProduct,
                listedPrice = product.listedPrice.toDouble(),
                price = product.promotionalPrice.toDouble(),
                sizes = sizeProductRepository.findByProductId(product.id).map { SizeDTO(it.id.nameSize, it.quantityAvailable) },
                images = imageProductRepository.findByProductId(product.id).map { it.path },
                timeCreated = product.timeCreated,
            )
        }
    }

    @PostMapping("/api/products")
    fun createProduct(
        @RequestPart("images") files: List<MultipartFile>,
        @RequestPart("product") productDTO: ProductDTO
    ) {
        val product = Product().apply {
            nameProduct = productDTO.name
            starReview = productDTO.star ?: 0
            idStatusProduct = 0
            listedPrice = productDTO.listedPrice?.toBigDecimal()
            promotionalPrice = productDTO.price?.toBigDecimal()
            brand = productDTO.brand
            typeProduct = productDTO.type
            idSex = if (productDTO.gender == true) 1 else 0
            timeCreated = LocalDateTime.now()
            comments = emptySet()
            imageProducts = emptySet()
            listSizes = emptySet()
        }

        val savedProduct = productRepository.save(product)

        productDTO.sizes?.let {
            for (size in it) {
                sizeProductRepository.insertSize(savedProduct.id, size.name, size.quantity ?: 0)
            }
        }

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