package com.example.autoservice.data.remote

import com.example.autoservice.common.constants.ApiControllers
import com.example.autoservice.data.remote.dto.CreateOrderInfo
import com.example.autoservice.data.remote.dto.CreatedOrderDto
import com.example.autoservice.data.remote.dto.OrderDetailsDto
import com.example.autoservice.data.remote.dto.OrderDto
import com.example.autoservice.domain.model.NewOrder
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApi {
    @GET(ApiControllers.ORDERS)
    suspend fun getOrders(): List<OrderDto>

    @GET("${ApiControllers.ORDER_DETAIL}/{${ApiControllers.ID}}")
    suspend fun getOrderById(@Path(ApiControllers.ID) id: Int): OrderDetailsDto

    @GET(ApiControllers.NEW_ORDER_INFO)
    suspend fun getNewOrderInfo(): CreateOrderInfo

    @POST(ApiControllers.ORDERS)
    suspend fun createOrder(@Body order: NewOrder): CreatedOrderDto
}

