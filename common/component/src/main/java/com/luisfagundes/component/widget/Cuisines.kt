package com.luisfagundes.component.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.luisfagundes.domain.models.Cuisine
import com.luisfagundes.theme.spacing

@Composable
fun Cuisines() {
    val cuisines = createCuisineList()
    cuisines.forEach { cuisine ->
        Cuisine(cuisine = cuisine)
    }
}

@Composable
fun Cuisine(cuisine: Cuisine) {
    Box(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.small)
            .clip(RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier.height(200.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(cuisine.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = cuisine.name,
            contentScale = ContentScale.Crop
        )
        Box(
            Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = 0.6f))
        )
        Text(
            text = cuisine.name,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
    Spacer(modifier = Modifier.padding(bottom = MaterialTheme.spacing.verySmall))
}


@Composable
private fun createCuisineList(): List<Cuisine> {
    val baseUrl = "https://luisfagundes94.github.io/foodlab-files"

    return listOf(
        Cuisine(
            name = "Mexican",
            imageUrl = "${baseUrl}/mexican.jpg"
        ),
        Cuisine(
            name = "Japanese",
            imageUrl = "${baseUrl}/japanese.jpg"
        ),
        Cuisine(
            name = "Italian",
            imageUrl = "${baseUrl}/italian.jpg"
        ),
        Cuisine(
            name = "Korean",
            imageUrl = "${baseUrl}/korean.jpg"
        )
    )
}