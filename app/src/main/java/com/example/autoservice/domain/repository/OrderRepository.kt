package com.example.autoservice.domain.repository

import com.example.autoservice.data.remote.dto.OrderDetailsDto
import com.example.autoservice.data.remote.dto.OrderDto

interface OrderRepository {
    suspend fun getOrders(): List<OrderDto>

    suspend fun getOrderById(id: Int): OrderDetailsDto
}