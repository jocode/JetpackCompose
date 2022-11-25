package com.crexative.jetpackcompose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorAnimation(modifier: Modifier = Modifier) {

    var firstColor by rememberSaveable {
        mutableStateOf(false)
    }

    var showText by rememberSaveable { mutableStateOf(false) }

    val realColor by animateColorAsState(
        targetValue = if (firstColor) Color.Red else Color.Blue,
        animationSpec = tween(durationMillis = 500),
        finishedListener = {
            showText = true
        }
    )

    Box(
        modifier = modifier
            .size(100.dp)
            .background(realColor)
            .clickable { firstColor = !firstColor },
    )
    if (showText) {
        Text(text = "Hola me muestro luego de la animación")
    }
}

@Composable
fun SizeAnimation() {

    var smallSize by rememberSaveable { mutableStateOf(true) }
    val size by animateDpAsState(
        targetValue = if (smallSize) 50.dp else 100.dp,
        animationSpec = tween(500),
        finishedListener = {
            smallSize = true
        }
    )

    Box(modifier = Modifier
        .size(size)
        .background(Color.Blue)
        .clickable {
            smallSize = !smallSize
        }
    )
}

@Composable
fun VisibilityAnimation() {
    Column {
        var isVisible by remember { mutableStateOf(true) }

        Button(onClick = {
            isVisible = !isVisible
        }) {
            Text(text = "Mostrar/Ocultar")
        }
        Spacer(modifier = Modifier.size(50.dp))

        AnimatedVisibility (isVisible) {
            Box(modifier = Modifier
                .size(150.dp)
                .background(Color.Yellow))
        }
    }
}

