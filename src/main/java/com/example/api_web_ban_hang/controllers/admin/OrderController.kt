package com.example.api_web_ban_hang.controllers.admin

import com.example.api_web_ban_hang.mapper.admin.OrderDTO
import com.example.api_web_ban_hang.mapper.admin.toOrderDTO
import com.example.api_web_ban_hang.repos.*
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController(value = "AdminOrderController")
class OrderController(
    private val orderRepository: OrderRepository,
    private val orderDetailRepository: OrderDetailRepository,
    private val sizeProductRepository: SizeProductRepository,
    private val imageProductRepository: ImageProductRepository,
    private val userRepository: UserRepository
) {

    @GetMapping("/api/admin/orders")
    fun getOrders(): List<OrderDTO> =
        orderRepository.findAll().map {
            it.toOrderDTO(
                userRepository,
                orderDetailRepository,
                sizeProductRepository,
                imageProductRepository
            )
        }

    @PatchMapping("/api/admin/order/update-status/{orderId}", consumes = [MediaType.TEXT_PLAIN_VALUE])
    fun updateOrderStatus(
        @PathVariable("orderId") orderId: Int, @RequestBody status: String
    ) = orderRepository.updateOrderStatus(orderId, status.toInt())
}