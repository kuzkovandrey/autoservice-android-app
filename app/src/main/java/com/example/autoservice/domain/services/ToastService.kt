package com.example.autoservice.domain.services

import android.content.Context
import android.widget.Toast

class ToastService {
    companion object {
        fun show(message: String, context: Context) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}