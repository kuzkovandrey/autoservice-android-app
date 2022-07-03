package com.example.autoservice.domain.model

import com.example.autoservice.data.remote.dto.Client

data class OrderDetails(
    val id: Int,
    val client: Client,
    val createdAt: String,
    val employeeName: String,
    val price: Double,
    val priceDescription: String,
    val carInfo: String
)
