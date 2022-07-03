package com.example.autoservice.presentation.order_details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.autoservice.presentation.shared.Paragraph
import com.example.autoservice.presentation.shared.Subtitle
import com.example.autoservice.presentation.shared.Title
import com.example.autoservice.presentation.shared.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailsScreen(
    navController: NavController,
    viewModel: OrderDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = "Order Details Screen",
                withBackButton = true,
                onClickBackButton = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            if (state.error.isNotBlank()) {
                Text(text = state.error, modifier = Modifier.align(Alignment.Center))
            }

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                state.order?.let { order ->
                    Title(text = "Order #${order.id}.")

                    Subtitle(text = "Client:")
                    Paragraph(text = "1. Name: ${order.client.name}.")
                    Paragraph(text = "2. Email: ${order.client.email}.")
                    Paragraph(text = "3. Car info: ${order.carInfo}.")

                    Subtitle(text = "Service:")
                    Paragraph(text = "1. Price info: ${order.priceDescription}.")
                    Paragraph(text = "2. Price total cost: ${order.price}.")
                    Paragraph(text = "3. Employee: ${order.employeeName}.")
                    Paragraph(text = "4. Date: ${order.createdAt}.")
                }
            }
        }
    }
}