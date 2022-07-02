package com.example.autoservice.presentation.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.autoservice.presentation.ui.theme.Colors

@Composable
fun TopAppBar(
    title: String = "title",
    withBackButton: Boolean = false,
    onClickBackButton: () -> Unit = {}
) {
    SmallTopAppBar(
        title = { Text(text = title) },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Colors.Purple40,
            titleContentColor = Colors.White,
            navigationIconContentColor = Colors.White
        ),
        navigationIcon = {
            if (withBackButton) {
                IconButton(onClick = { onClickBackButton() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back icon"
                    )
                }
            }
        },

    )
}