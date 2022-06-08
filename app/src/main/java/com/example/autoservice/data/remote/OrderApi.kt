package com.example.autoservice.data.remote

import com.example.autoservice.common.constants.ApiControllers
import com.example.autoservice.data.remote.dto.OrderDetailsDto
import com.example.autoservice.data.remote.dto.OrderDto
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderApi {
    @GET(ApiControllers.ORDERS)
    suspend fun getOrders(): List<OrderDto>

    @GET("${ApiControllers.ORDER_DETAIL}/{${ApiControllers.ID}}")
    suspend fun getOrderById(@Path(ApiControllers.ID) id: Int): OrderDetailsDto
}

