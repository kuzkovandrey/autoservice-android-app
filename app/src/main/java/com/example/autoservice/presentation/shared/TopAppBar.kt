package com.example.autoservice.presentation.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.autoservice.presentation.ui.theme.Purple80

@Composable
fun TopAppBar(
    title: String = "title",
    withBackButton: Boolean = true,
    onClickBackButton: () -> Unit
) {
    SmallTopAppBar(
        title = { Text(text = title) },
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
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Purple80)
    )
}