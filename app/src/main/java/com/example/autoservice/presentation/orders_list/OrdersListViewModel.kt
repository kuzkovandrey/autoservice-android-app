package com.example.autoservice.presentation.orders_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoservice.common.Resource
import com.example.autoservice.domain.use_case.get_orders.GetOrdersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class OrdersListViewModel @Inject constructor(
    private val getOrdersUseCase: GetOrdersUseCase
) : ViewModel() {

    private val _state = mutableStateOf(OrdersListState())
    val state: State<OrdersListState> = _state

    init {
        getOrders()
    }

    private fun getOrders() {
        getOrdersUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = OrdersListState(orders = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = OrdersListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = OrdersListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}