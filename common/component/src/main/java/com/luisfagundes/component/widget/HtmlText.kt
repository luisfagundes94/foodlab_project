package com.luisfagundes.component.widget

import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat

@Composable
fun HtmlText(text: String) {
    AndroidView(factory = { context ->
        TextView(context).apply {
            textSize = 16f
            setText(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY))
        }
    })
}