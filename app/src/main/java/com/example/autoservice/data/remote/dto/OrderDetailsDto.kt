package com.example.autoservice.data.remote.dto

import com.example.autoservice.domain.model.Order
import com.example.autoservice.domain.model.OrderDetails

data class OrderDetailsDto(
    val id: Int,
    val client: Client,
    val createdAt: String,
    val employee: Employee,
    val price: Double,
    val priceList: PriceList
)

fun OrderDetailsDto.toOrderDetails(): OrderDetails {
    return OrderDetails(
        id = id,
        client = client,
        createdAt = createdAt,
        employeeName = employee.name,
        price = price,
        priceDescription = priceList.description
    )
}