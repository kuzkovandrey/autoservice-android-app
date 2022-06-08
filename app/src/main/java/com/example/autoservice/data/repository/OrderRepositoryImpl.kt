package com.example.autoservice.data.repository

import com.example.autoservice.data.remote.OrderApi
import com.example.autoservice.data.remote.dto.OrderDetailsDto
import com.example.autoservice.data.remote.dto.OrderDto
import com.example.autoservice.domain.repository.OrderRepository
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

}