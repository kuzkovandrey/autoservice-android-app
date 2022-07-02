package com.example.autoservice.data.remote.dto

data class CreateOrderInfo(
    val employeesList: List<Employee> = emptyList(),
    val priceList: List<PriceList> = emptyList()
)
