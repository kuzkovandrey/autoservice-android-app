package com.example.autoservice.presentation.orders_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.autoservice.presentation.Screen
import com.example.autoservice.presentation.orders_list.components.OrderListItem
import androidx.compose.material3.Scaffold


@Composable
fun OrdersListScreen(
    navController: NavController,
    viewModel: OrdersListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.orders) { order ->
                OrderListItem(
                    order = order,
                    onItemClick = {
                        navController.navigate(Screen.OrderDetailScreen.route + "/${it.id}")
                    }
                )
            }
        }
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        FloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.absolutePadding(bottom = 10.dp, right = 10.dp)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "New order")
        }
    }




}