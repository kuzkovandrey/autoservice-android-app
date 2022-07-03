package com.example.autoservice.presentation.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.autoservice.presentation.ui.theme.Colors

@Composable
fun Paragraph(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight(400),
        fontSize = 16.sp,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth(),
        color = Colors.PurpleGrey40
    )
}