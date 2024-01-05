package com.example.api_web_ban_hang.controllers.admin

import com.example.api_web_ban_hang.mapper.admin.OrderDTO
import com.example.api_web_ban_hang.mapper.admin.toOrderDTO
import com.example.api_web_ban_hang.repos.*
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    @GetMapping("/api/admin/last-12-revenue")
    fun getRevenueByDate(): Map<String, Double> {
        val data = mutableMapOf<String, Double>()
        for (i in 1L..12L) {
            val yearAndMonth = LocalDateTime.now().minusMonths(i).format(DateTimeFormatter.ofPattern("YYYY-MM"))
            orderRepository.getTotalPriceByYearAndMonth(yearAndMonth).also { data[yearAndMonth] = it }
        }
        return data
    }

    @PatchMapping("/api/admin/order/update-status/{orderId}", consumes = [MediaType.TEXT_PLAIN_VALUE])
    fun updateOrderStatus(
        @PathVariable("orderId") orderId: Int, @RequestBody status: String
    ) = orderRepository.updateOrderStatus(orderId, status.toInt())
}