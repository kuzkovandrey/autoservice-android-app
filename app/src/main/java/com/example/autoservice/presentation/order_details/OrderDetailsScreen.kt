package com.example.autoservice.presentation.order_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.autoservice.presentation.shared.TopAppBar

@Composable
fun OrderDetailsScreen(
    navController: NavController,
    viewModel: OrderDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    TopAppBar(title = "Order Details Screen", onClickBackButton = {  })
    
//    Box(modifier = Modifier.fillMaxSize()) {
//        state.order?.let { order ->
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                contentPadding = PaddingValues(20.dp)
//            ) {
//                item {
//
//                    Text(
//                        text = "Order id: ${order.id}",
//                        modifier = Modifier
//                            .padding(20.dp)
//                            .fillMaxWidth()
//                            .background(color = MaterialTheme.colorScheme.secondary)
//                    )
//
//
//                }
//            }
//
//        }
//    }
}