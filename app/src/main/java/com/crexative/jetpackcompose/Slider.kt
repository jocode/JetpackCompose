package com.crexative.jetpackcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RangeSlider
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BasicSlider() {
    Column(
        Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var sliderPosition by remember {
            mutableStateOf(0f)
        }
        Slider(value = sliderPosition, onValueChange = {
            sliderPosition = it
        })
        Text(text = sliderPosition.toString())
    }
}

@Composable
fun AdvanceSlider() {
    Column(
        Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var sliderPosition by remember { mutableStateOf(0f) }
        var completedValue by remember { mutableStateOf("0f") }

        Slider(
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it
            },
            onValueChangeFinished = {
                completedValue = sliderPosition.toString()
            },
            valueRange = 0f..10f,
            steps = 9,
            enabled = false
        )
        Text(text = completedValue)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyRangeSlider() {
    var currentRange by remember { mutableStateOf(0f..10f) }
    RangeSlider(
        values = currentRange,
        onValueChange = { currentRange = it },
        valueRange = 0f..10f,
        steps = 9
    )
    Text(text = "Valor inferior ${currentRange.start}")
    Text(text = "Valor Superior ${currentRange.endInclusive}")
    Text(text = "Rango - $currentRange")
}