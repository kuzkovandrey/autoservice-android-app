package com.example.autoservice.presentation

import com.example.autoservice.common.constants.AppRoutes

sealed class Screen(val route: String, ) {
    object OrdersListScreen: Screen(AppRoutes.ORDERS_LIST)
    object OrderDetailScreen: Screen(AppRoutes.ORDER_DETAILS)
}
