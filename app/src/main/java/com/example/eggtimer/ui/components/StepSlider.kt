package com.example.eggtimer.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

// Internally uses floats between steps. Calls onValueChange only if the new rounded! value != current value
@Composable
fun StepSlider(
    steps: Int,
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var sliderValue by remember { mutableStateOf(value.toFloat()) }
    val edgePadding = with(LocalDensity.current) { 10.dp.toPx() }
    val lineHeight = 10.dp
    val lineHeightPx = with(LocalDensity.current) { lineHeight.toPx() }
    val canvasHeight = 50.dp

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Canvas(
            modifier = Modifier
                .height(canvasHeight)
                .fillMaxWidth()
                .padding(
                    top = (canvasHeight / 2) - (lineHeight / 2)
                )
        ) {
            val yStart = 0f
            val spacePerOption = ((size.width - 2 * edgePadding) / (steps - 1))

            repeat(steps) {
                drawLine(
                    color = Color.DarkGray,
                    start = Offset(x = edgePadding + it.times(spacePerOption), yStart),
                    end = Offset(x = edgePadding + it.times(spacePerOption), y = lineHeightPx)
                )
            }
        }
        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = sliderValue,
            valueRange = 0f..(steps - 1).toFloat(),
            steps = steps - 2,
            onValueChange = {
                sliderValue = it
                val roundedValue = it.roundToInt()
                if(roundedValue != value) onValueChange(roundedValue)
            }
        )
    }
}
