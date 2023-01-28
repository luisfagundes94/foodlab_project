package com.luisfagundes.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import com.luisfagundes.domain.models.Step
import com.luisfagundes.theme.spacing

@Composable
fun RecipeSteps(steps: List<Step>) {
    steps.forEach { step ->
        Step(step)
    }
}

@Composable
private fun Step(step: Step) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)
    ) {
        val backgroundNumberColor = MaterialTheme.colorScheme.surfaceVariant
        val backgroundNumberRadius = MaterialTheme.spacing.extraLarge.value

        Text(
            text = step.number.toString(),
            modifier = Modifier
                .padding(MaterialTheme.spacing.verySmall)
                .drawBehind {
                    drawCircle(
                        color = backgroundNumberColor,
                        radius = backgroundNumberRadius
                    )
                }
        )
        Text(
            text = step.step,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = MaterialTheme.spacing.small)
        )
    }
}