package com.wolfspider.zeus.sdk.ui

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, message: String?, isLong: Boolean = false) {
    if (isLong) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    } else {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}