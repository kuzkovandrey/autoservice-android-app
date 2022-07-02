package com.example.autoservice.presentation.order_creation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.autoservice.presentation.Screen
import com.example.autoservice.presentation.order_creation.constants.FieldNames
import com.example.autoservice.presentation.shared.SelectableField
import com.example.autoservice.presentation.shared.Title
import com.example.autoservice.presentation.shared.TopAppBar
import com.example.autoservice.presentation.shared.models.SelectableFieldOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOrderScreen(
    navController: NavController,
    viewModel: CreateOrderViewModel = hiltViewModel()
) {
    val orderInfo = viewModel.orderInfo.value

    var isLoading by remember { mutableStateOf(false) }

    fun onClickCreationButton() {
        isLoading = true

        viewModel.createNewOrder(
            onResult = { order ->
                Log.i("TTT", order.toString())
                isLoading = false
                navController.navigate(Screen.OrderDetailScreen.route + "/${order.id}")
            },
            onError = { message ->
                Log.i("TTT", message)
                isLoading = false
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(title = "Create new order", onClickBackButton = {
                navController.popBackStack()
            })
        },
        bottomBar = {
            Button(
                enabled = viewModel.isValidForm.value && !isLoading,
                onClick = { onClickCreationButton() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "Create", fontSize = 20.sp)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            if (orderInfo.isLoading || isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            if (orderInfo.error.isNotBlank()) {
                Text(
                    text = orderInfo.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            if (orderInfo.info.employeesList.isNotEmpty() && orderInfo.info.priceList.isNotEmpty()) {
                Column(modifier = Modifier.padding(paddingValues)) {
                    Title(text = "1. Client info:")

                    OutlinedTextField(
                        value = viewModel.name,
                        onValueChange = { viewModel.setField(FieldNames.NAME, it) },
                        label = { Text("Name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = viewModel.email,
                        onValueChange = { viewModel.setField(FieldNames.EMAIL, it) },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Title(text = "2. Car info:")

                    OutlinedTextField(
                        value = viewModel.model,
                        onValueChange = { viewModel.setField(FieldNames.MODEL, it) },
                        label = { Text("Model") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = viewModel.stateNumber,
                        onValueChange = { viewModel.setField(FieldNames.STATE_NUMBER, it) },
                        label = { Text("State number") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Title(text = "3. Choose service and employee:")

                    SelectableField(
                        options = orderInfo.info.employeesList.map { employee ->
                            SelectableFieldOptions(value = employee.name, id = employee.id) },
                        onSelect = { viewModel.setField(FieldNames.EMPLOYEE_ID, it.id) },
                        label = "Employee"
                    )

                    SelectableField(
                        options = orderInfo.info.priceList.map { price ->
                            SelectableFieldOptions(value = "${price.description}, ${price.cost} $", id = price.id) },
                        onSelect = { viewModel.setField(FieldNames.PRICE_ID, it.id) },
                        label = "Service"
                    )

                    if (viewModel.price != null && viewModel.isValidForm.value) {
                        Title(text = "Total cost: ${viewModel.price} $")
                    }
                }
            }
        }
    }
}