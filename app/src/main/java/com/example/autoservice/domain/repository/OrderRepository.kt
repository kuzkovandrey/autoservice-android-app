package com.example.autoservice.domain.repository

import com.example.autoservice.data.remote.dto.CreateOrderInfo
import com.example.autoservice.data.remote.dto.CreatedOrderDto
import com.example.autoservice.data.remote.dto.OrderDetailsDto
import com.example.autoservice.data.remote.dto.OrderDto
import com.example.autoservice.domain.model.NewOrder
import retrofit2.Call

interface OrderRepository {
    suspend fun getOrders(): List<OrderDto>

    suspend fun getOrderById(id: Int): OrderDetailsDto

    suspend fun getNewOrderInfo(): CreateOrderInfo

    suspend fun createOrder(order: NewOrder): CreatedOrderDto
}