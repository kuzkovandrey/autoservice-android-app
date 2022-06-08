package com.example.autoservice.data.remote.dto

data class Client(
    val id: Int,
    val cars: List<Car>,
    val email: String,
    val name: String
)