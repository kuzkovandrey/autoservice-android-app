package com.example.autoservice.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.autoservice.common.constants.Constants
import com.example.autoservice.presentation.order_creation.CreateOrderScreen
import com.example.autoservice.presentation.order_details.OrderDetailsScreen
import com.example.autoservice.presentation.orders_list.OrdersListScreen
import com.example.autoservice.presentation.ui.theme.AutoserviceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutoserviceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.OrdersListScreen.route
                    ) {
                        composable(
                            route = Screen.OrdersListScreen.route
                        ) {
                            OrdersListScreen(navController)
                        }
                        composable(
                            route = Screen.OrderDetailScreen.route + "/{${Constants.PARAM_ORDER_ID}}"
                        ) {
                            OrderDetailsScreen(navController)
                        }
                        composable(
                            route = Screen.CreateOrderScreen.route
                        ) {
                            CreateOrderScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

