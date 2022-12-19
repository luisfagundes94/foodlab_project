package com.luisfagundes.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisfagundes.component.widget.ErrorView

fun LazyListScope.loadingView() {
    item {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    }
}

fun LazyListScope.errorView() {
    item {
        ErrorView()
    }
}

