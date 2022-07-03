package com.example.autoservice.presentation.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autoservice.presentation.ui.theme.Colors

@Composable
fun Subtitle(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight(400),
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        color = Colors.Pink40
    )
}