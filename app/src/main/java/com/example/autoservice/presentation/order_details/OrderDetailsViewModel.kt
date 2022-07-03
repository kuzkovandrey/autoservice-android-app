package com.example.autoservice.presentation.order_details

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoservice.common.Resource
import com.example.autoservice.common.constants.Constants
import com.example.autoservice.domain.use_case.get_order_details.GetOrderDetailsUseCase
import com.example.autoservice.presentation.orders_list.OrderDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel @Inject constructor(
    private val getOrderDetailsUseCase: GetOrderDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(OrderDetailsState())
    val state: State<OrderDetailsState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_ORDER_ID)?.let { orderId ->
            getOrderDetails(orderId.toInt())
        }
    }

    private fun getOrderDetails(orderId: Int) {
        getOrderDetailsUseCase(orderId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = OrderDetailsState(order = result.data)
                }
                is Resource.Error -> {
                    _state.value = OrderDetailsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = OrderDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}