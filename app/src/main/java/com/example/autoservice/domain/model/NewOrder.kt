package com.example.autoservice.domain.model

data class NewOrder(
    val client: Client = Client(),
    val car: Car = Car(),
    var employeeId: Number? = null,
    var priceListId: Number? = null,
    var price: Number? = null,
)
