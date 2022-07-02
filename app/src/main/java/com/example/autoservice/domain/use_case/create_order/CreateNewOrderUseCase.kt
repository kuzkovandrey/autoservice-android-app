package com.example.autoservice.domain.use_case.create_order

import com.example.autoservice.common.Resource
import com.example.autoservice.common.constants.Messages
import com.example.autoservice.data.remote.dto.CreateOrderInfo
import com.example.autoservice.data.remote.dto.CreatedOrderDto
import com.example.autoservice.domain.model.NewOrder
import com.example.autoservice.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreateNewOrderUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    operator fun invoke(order: NewOrder): Flow<Resource<CreatedOrderDto>> = flow {
        try {
            emit(Resource.Loading())
            val createdOrder = repository.createOrder(order)
            emit(Resource.Success(createdOrder))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: Messages.HTTP_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error(Messages.CHECK_INTERNET_ERROR))
        }
    }
}