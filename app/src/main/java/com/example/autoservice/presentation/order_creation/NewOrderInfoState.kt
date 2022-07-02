package com.example.autoservice.presentation.order_creation

import com.example.autoservice.data.remote.dto.CreateOrderInfo

data class NewOrderInfoState(
    val isLoading: Boolean = false,
    val info: CreateOrderInfo = CreateOrderInfo(),
    val error: String = ""
)
