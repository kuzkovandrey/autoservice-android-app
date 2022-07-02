package com.example.autoservice.presentation.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.autoservice.presentation.shared.models.SelectableFieldOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectableField(
    label: String,
    options: List<SelectableFieldOptions>,
    onSelect: (selectedOption: SelectableFieldOptions) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        OutlinedTextField(
            readOnly = true,
            label = { Text(text = label) },
            value = selectedText,
            placeholder = { Text(text = label) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
        ) {
            options.forEach { selectOption ->
                DropdownMenuItem(
                    text = { Text(text = selectOption.value) },
                    onClick = {
                        onSelect(selectOption)
                        selectedText = selectOption.value
                        expanded = false
                    }
                )
            }
        }
    }
}