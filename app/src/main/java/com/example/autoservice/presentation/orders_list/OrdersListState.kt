package com.example.autoservice.presentation.orders_list

import com.example.autoservice.domain.model.Order

data class OrdersListState(
    val isLoading: Boolean = false,
    val orders: List<Order> = emptyList(),
    val error: String = ""
)
