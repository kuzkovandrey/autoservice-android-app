package com.example.autoservice.domain.use_case.get_order_details

import com.example.autoservice.common.Resource
import com.example.autoservice.common.constants.Messages
import com.example.autoservice.data.remote.dto.toOrderDetails
import com.example.autoservice.domain.model.OrderDetails
import com.example.autoservice.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetOrderDetailsUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    operator fun invoke(orderId: Int): Flow<Resource<OrderDetails>> = flow {
        try {
            emit(Resource.Loading())

            val orderDetails = repository.getOrderById(orderId).toOrderDetails()

            emit(Resource.Success(orderDetails))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: Messages.HTTP_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error(Messages.CHECK_INTERNET_ERROR))
        }
    }
}