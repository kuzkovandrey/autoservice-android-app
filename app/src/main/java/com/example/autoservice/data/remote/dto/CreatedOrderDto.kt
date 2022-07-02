package com.example.autoservice.data.remote.dto

data class CreatedOrderDto(
    val id: Number,
    val clientId: Number,
    val priceListId: Number,
    val employeeId: Number,
    val createdAt: String,
    val price: Double
)
