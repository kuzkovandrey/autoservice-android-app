package com.example.autoservice.data.remote.dto

data class Employee(
    val id: Int,
    val isRemoved: Boolean,
    val name: String,
    val rate: Double
)