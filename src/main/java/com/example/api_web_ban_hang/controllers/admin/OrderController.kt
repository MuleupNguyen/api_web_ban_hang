package com.example.api_web_ban_hang.controllers.admin

import com.example.api_web_ban_hang.mapper.admin.OrderStatusStatistic
import com.example.api_web_ban_hang.mapper.admin.toOrderDTO
import com.example.api_web_ban_hang.repos.OrderRepository
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController(value = "AdminOrderController")
class OrderController(private val orderRepository: OrderRepository) {

    @GetMapping("/api/admin/total-revenue")
    fun getTotalRevenue(): Long = orderRepository.getTotalRevenue()

    @GetMapping("/api/admin/orders-amount")
    fun getProductsAmount(): Long = orderRepository.count()

    @GetMapping("/api/admin/order-status-statistics")
    fun getOrderStatusStatistic(): List<OrderStatusStatistic> {
        val ordersAmount = orderRepository.count()
        return listOf(1 to "Packaging", 2 to "Shipping", 3 to "Delivered", 4 to "Canceled").map {
            val quantity = orderRepository.countByIdStatusOrder(it.first).toInt()
            OrderStatusStatistic(
                id = it.first,
                name = it.second,
                quantity = quantity,
                percentage = quantity.toFloat() / ordersAmount * 100
            )
        }
    }

    @GetMapping("/api/admin/orders")
    fun getOrders(pageable: Pageable?) = orderRepository.findAll(pageable ?: Pageable.unpaged()).toList().map { it.toOrderDTO() }

    @GetMapping("/api/admin/last-12-revenue")
    fun getRevenueByDate(): Map<String, Double> {
        val data = mutableMapOf<String, Double>()
        for (i in (1L..12L).sortedDescending()) {
            val yearAndMonth = LocalDateTime.now().minusMonths(i).format(DateTimeFormatter.ofPattern("YYYY-MM"))
            orderRepository.getTotalPriceByYearAndMonth(yearAndMonth).also { data[yearAndMonth] = it }
        }
        return data
    }

    @PatchMapping("/api/admin/order/update-status/{orderId}", consumes = [MediaType.TEXT_PLAIN_VALUE])
    fun updateOrderStatus(
        @PathVariable("orderId") orderId: Int,
        @RequestBody status: String
    ) = orderRepository.updateOrderStatus(orderId, status.toInt())
}