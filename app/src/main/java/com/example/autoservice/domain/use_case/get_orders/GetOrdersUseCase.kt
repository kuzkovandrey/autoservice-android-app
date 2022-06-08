package com.example.autoservice.domain.use_case.get_orders

import com.example.autoservice.common.Resource
import com.example.autoservice.common.constants.Messages
import com.example.autoservice.data.remote.dto.toOrder
import com.example.autoservice.domain.model.Order
import com.example.autoservice.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    operator fun invoke(): Flow<Resource<List<Order>>> = flow {
        try {
            emit(Resource.Loading())

            val orders = repository.getOrders().map { it.toOrder() }

            emit(Resource.Success(orders))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: Messages.HTTP_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error(Messages.CHECK_INTERNET_ERROR))
        }
    }
}