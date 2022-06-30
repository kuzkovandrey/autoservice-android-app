package com.example.autoservice.presentation.orders_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.autoservice.domain.model.Order
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun OrderListItem(
    order: Order,
    onItemClick: (Order) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(order) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${order.id}. ${order.clientName}",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "${order.createdAt}",
            style = MaterialTheme.typography.labelSmall
        )
    }
}