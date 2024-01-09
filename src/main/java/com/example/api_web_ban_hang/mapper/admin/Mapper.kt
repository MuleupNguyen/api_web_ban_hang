package com.example.api_web_ban_hang.mapper.admin

import com.example.api_web_ban_hang.models.entities.Brand
import com.example.api_web_ban_hang.models.entities.Order
import com.example.api_web_ban_hang.models.entities.Product
import com.example.api_web_ban_hang.models.entities.TypeProduct
import java.math.BigDecimal
import java.time.LocalDateTime

class OrderStatusStatistic(
    val id: Int?,
    val name: String?,
    val quantity: Int?,
    val percentage: Float?
)

class OrderItemDTO(
    val product: ProductDTO?,
    val size: String?,
    val quantity: Int?,
    val totalPrice: Double?
)

class OrderDTO(
    val id: Long?,
    val toPhone: String?,
    val orderDate: LocalDateTime?,
    val statusId: Int?,
    val totalPrice: Double?,
    val address: String?,
    val orderItems: List<OrderItemDTO>?
)

data class SizeDTO(
    val name: String?,
    val quantity: Int?
)

data class ProductDTO(
    val id: Long?,
    val name: String?,
    val gender: Int?,
    val star: Int?,
    val brand: Brand?,
    val type: TypeProduct?,
    val listedPrice: Double?,
    val price: Double?,
    val sizes: List<SizeDTO>?,
    val images: List<String>?,
    val timeCreated: LocalDateTime?,
)

fun ProductDTO.toProduct(): Product = Product.builder()
    .id(id)
    .nameProduct(name ?: "Sample name")
    .starReview(star ?: 0)
    .idStatusProduct(1)
    .listedPrice(listedPrice?.toBigDecimal() ?: BigDecimal.ZERO)
    .promotionalPrice(price?.toBigDecimal() ?: BigDecimal.ZERO)
    .brand(brand)
    .typeProduct(type)
    .idSex(gender ?: 0)
    .timeCreated(timeCreated ?: LocalDateTime.now())
    .comments(emptySet())
    .imageProducts(emptySet()) // Set later
    .listSizes(emptySet()) // Set later
    .build()

fun Product.toProductDTO() = ProductDTO(
    id = id,
    name = nameProduct,
    gender = idSex,
    star = starReview,
    brand = brand,
    type = typeProduct,
    listedPrice = listedPrice.toDouble(),
    price = promotionalPrice.toDouble(),
    sizes = listSizes.map { SizeDTO(name = it.size.nameSize, quantity = it.quantityAvailable) },
    images = imageProducts.map { it.path },
    timeCreated = timeCreated,
)

fun Order.toOrderDTO() = OrderDTO(
    id = idOrder,
    toPhone = toPhone,
    orderDate = timeOrder,
    statusId = idStatusOrder,
    totalPrice = totalPrice.toDouble(),
    address = toAddress,
    orderItems = listOrderDatail.map {
        OrderItemDTO(
            product = it.product.toProductDTO(),
            size = it.nameSize,
            quantity = it.quantity,
            totalPrice = it.price.toDouble(),
        )
    }
)