package com.example.autoservice.data.remote.dto

data class PriceList(
    val id: Int,
    val cost: Double,
    val description: String,
    val isRemoved: Boolean
)