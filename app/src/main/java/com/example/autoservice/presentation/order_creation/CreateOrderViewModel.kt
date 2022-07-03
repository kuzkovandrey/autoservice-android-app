package com.example.autoservice.presentation.order_creation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoservice.common.Resource
import com.example.autoservice.data.remote.dto.CreateOrderInfo
import com.example.autoservice.data.remote.dto.CreatedOrderDto
import com.example.autoservice.data.remote.dto.Employee
import com.example.autoservice.data.remote.dto.PriceList
import com.example.autoservice.domain.model.Car
import com.example.autoservice.domain.model.Client
import com.example.autoservice.domain.model.NewOrder
import com.example.autoservice.domain.use_case.create_order.CreateNewOrderUseCase
import com.example.autoservice.domain.use_case.create_order.GetNewOrderInfoUseCase
import com.example.autoservice.presentation.order_creation.constants.FieldNames
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CreateOrderViewModel @Inject constructor(
    private val getNewOrderInfoUseCase: GetNewOrderInfoUseCase,
    private val createNewOrderUseCase: CreateNewOrderUseCase
) : ViewModel() {
    private val _orderInfo = mutableStateOf(NewOrderInfoState())
    val orderInfo: State<NewOrderInfoState> = _orderInfo

    private val _isValidForm = mutableStateOf(false)
    val isValidForm: State<Boolean> = _isValidForm

    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var model by mutableStateOf("")
    var stateNumber by mutableStateOf("")
    var employeeId by mutableStateOf<Number?>(null)
    var priceListId by mutableStateOf<Number?>(null)
    var price by mutableStateOf<Double?>(null)

    init {
        getNewOrderInfo()
    }

    private fun validateForm() {
        _isValidForm.value = isValidForm()

        if (_isValidForm.value) calculatePriceCost()
    }

    private fun isValidForm(): Boolean {
        return model.isNotEmpty()
                && stateNumber.isNotEmpty()
                && email.isNotEmpty()
                && name.isNotEmpty()
                && employeeId != null
                && priceListId != null;
    }

    fun setField(field: FieldNames, value: String) {
        when(field) {
            FieldNames.NAME -> name = value
            FieldNames.EMAIL -> email = value
            FieldNames.MODEL -> model = value
            FieldNames.STATE_NUMBER -> stateNumber = value
        }

        validateForm()
    }

    fun setField(field: FieldNames, value: Number) {
        when(field) {
            FieldNames.EMPLOYEE_ID -> employeeId = value
            FieldNames.PRICE_ID -> priceListId = value
        }

        validateForm()
    }

    private fun calculatePriceCost() {
        val employee = getEmployeeById(employeeId!!)
        val priceList = getPriceListById(priceListId!!)

        if (employee != null && priceList != null) {
            price = employee.rate * priceList.cost + priceList.cost
        }
    }

    private fun getEmployeeById(id: Number): Employee? {
        return _orderInfo.value.info.employeesList.find { empl -> empl.id == id }
    }

    private fun getPriceListById(id: Number): PriceList? {
        return orderInfo.value.info.priceList.find { price -> price.id == id }
    }

    private fun getNewOrderInfo() {
        getNewOrderInfoUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _orderInfo.value = NewOrderInfoState(info = result.data ?: CreateOrderInfo())
                }
                is Resource.Error -> {
                    _orderInfo.value = NewOrderInfoState(
                        error = result.message ?: "An unexpected error occured"
                    )
                    _isValidForm.value = false
                }
                is Resource.Loading -> {
                    _orderInfo.value = NewOrderInfoState(isLoading = true)
                    _isValidForm.value = false
                }
            }
        }.launchIn(viewModelScope)
    }

    fun createNewOrder(
        onResult: (newOrder: CreatedOrderDto) -> Unit,
        onError: (message: String) -> Unit
    ) {
        val order = NewOrder(
            client = Client(email = email, name = name),
            car = Car(model = model, stateNumber = stateNumber),
            employeeId = employeeId,
            priceListId = priceListId,
            price = price
        )

        createNewOrderUseCase(order).onEach { result ->
            when(result) {
                is Resource.Success -> onResult(result.data!!)
                is Resource.Error ->  onError(result.message ?: "An unexpected error occured")
            }
        }.launchIn(viewModelScope)
    }
}