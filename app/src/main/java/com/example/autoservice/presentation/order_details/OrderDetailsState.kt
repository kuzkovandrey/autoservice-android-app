package com.example.autoservice.presentation.orders_list

import com.example.autoservice.data.remote.dto.OrderDetailsDto
import com.example.autoservice.domain.model.Order
import com.example.autoservice.domain.model.OrderDetails

data class OrderDetailsState(
    val isLoading: Boolean = false,
    val order: OrderDetails? = null,
    val error: String = ""
)
