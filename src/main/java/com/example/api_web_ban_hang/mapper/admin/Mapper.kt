package com.example.api_web_ban_hang.mapper.admin

import com.example.api_web_ban_hang.models.entities.*
import com.example.api_web_ban_hang.repos.ImageProductRepository
import com.example.api_web_ban_hang.repos.OrderDetailRepository
import com.example.api_web_ban_hang.repos.SizeProductRepository
import com.example.api_web_ban_hang.repos.UserRepository
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

class UserDTO(
    val id: Long?,
    val name: String,
    val profileImage: String?
)

class OrderItemDTO(
    val product: ProductDTO?,
    val size: String?,
    val quantity: Int?,
    val totalPrice: Double?
)

class OrderDTO(
    val id: Long?,
    val user: UserDTO?,
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

fun ProductDTO.toProduct(): Product = Product.builder()
    .nameProduct(name)
    .starReview(star ?: 0)
    .idStatusProduct(0)
    .listedPrice(listedPrice?.toBigDecimal())
    .promotionalPrice(price?.toBigDecimal())
    .brand(brand)
    .typeProduct(type)
    .idSex(if (gender == true) 1 else 0)
    .timeCreated(timeCreated ?: LocalDateTime.now())
    .comments(emptySet())
    .imageProducts(emptySet())
    .listSizes(emptySet())
    .build()

fun Product.toProductDTO(
    sizeProductRepository: SizeProductRepository,
    imageProductRepository: ImageProductRepository
): ProductDTO = ProductDTO(
    id = id,
    name = nameProduct,
    gender = idSex == 1,
    star = starReview,
    brand = brand,
    type = typeProduct,
    listedPrice = listedPrice.toDouble(),
    price = promotionalPrice.toDouble(),
    sizes = sizeProductRepository.findByProductId(id).map { SizeDTO(it.id.nameSize, it.quantityAvailable) },
    images = imageProductRepository.findByProductId(id).map { it.path },
    timeCreated = timeCreated,
)

fun User.toUserDTO(): UserDTO = UserDTO(
    id = id,
    name = fullname,
    profileImage = pathImgAvatar
)

fun Order.toOrderDTO(
    userRepository: UserRepository,
    orderDetailRepository: OrderDetailRepository,
    sizeProductRepository: SizeProductRepository,
    imageProductRepository: ImageProductRepository
): OrderDTO = OrderDTO(
    id = idOrder,
    user = userRepository.findByPhone(fromPhone).getOrNull()!!.toUserDTO(),
    orderDate = timeOrder,
    statusId = idStatusOrder,
    totalPrice = totalPrice.toDouble(),
    address = toAddress,
    orderItems = orderDetailRepository.findByOrder_IdOrder(idOrder).map { orderDetail ->
        OrderItemDTO(
            product = orderDetail.product.toProductDTO(sizeProductRepository, imageProductRepository),
            size = orderDetail.nameSize,
            quantity = orderDetail.quantity,
            totalPrice = orderDetail.price.toDouble(),
        )
    }
)