package com.example.autoservice.data.repository

import com.example.autoservice.data.remote.OrderApi
import com.example.autoservice.data.remote.dto.CreateOrderInfo
import com.example.autoservice.data.remote.dto.CreatedOrderDto
import com.example.autoservice.data.remote.dto.OrderDetailsDto
import com.example.autoservice.data.remote.dto.OrderDto
import com.example.autoservice.domain.model.NewOrder
import com.example.autoservice.domain.repository.OrderRepository
import retrofit2.Call
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val api: OrderApi
): OrderRepository {
    override suspend fun getOrders(): List<OrderDto> {
        return api.getOrders()
    }

    override suspend fun getOrderById(id: Int): OrderDetailsDto {
        return api.getOrderById(id)
    }

    override suspend fun getNewOrderInfo(): CreateOrderInfo {
        return api.getNewOrderInfo()
    }

    override suspend fun createOrder(order: NewOrder): CreatedOrderDto {
        return api.createOrder(order)
    }
}