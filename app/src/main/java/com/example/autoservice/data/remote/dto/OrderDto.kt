package com.example.autoservice.data.remote.dto

import com.example.autoservice.domain.model.Order

data class OrderDto(
    val id: Int,
    val clientName: String,
    val createdAt: String
)

fun OrderDto.toOrder(): Order {
    return Order(
        id = id,
        clientName = clientName,
        createdAt = createdAt
    )
}