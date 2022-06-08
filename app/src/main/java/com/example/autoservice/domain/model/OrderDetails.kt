package com.example.autoservice.domain.model

import com.example.autoservice.data.remote.dto.Client
import com.example.autoservice.data.remote.dto.Employee
import com.example.autoservice.data.remote.dto.PriceList

data class OrderDetails(
    val id: Int,
    val client: Client,
    val createdAt: String,
    val employeeName: String,
    val price: Double,
    val priceDescription: String
)
