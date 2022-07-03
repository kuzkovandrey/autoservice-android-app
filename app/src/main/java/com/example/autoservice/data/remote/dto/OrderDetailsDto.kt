package com.example.autoservice.data.remote.dto

import com.example.autoservice.domain.model.OrderDetails

data class OrderDetailsDto(
    val id: Int,
    val client: Client,
    val createdAt: String,
    val employee: Employee,
    val price: Double,
    val priceList: PriceList,
    val carInfo: String
)

fun OrderDetailsDto.toOrderDetails(): OrderDetails {
    return OrderDetails(
        id = id,
        client = client,
        createdAt = createdAt.substring(0..9) + " " + createdAt.substring(11..15),
        employeeName = employee.name,
        price = price,
        priceDescription = priceList.description,
        carInfo = carInfo
    )
}