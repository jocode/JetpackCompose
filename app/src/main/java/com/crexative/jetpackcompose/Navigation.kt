package com.crexative.jetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.crexative.jetpackcompose.model.Routes

@Composable
fun Screen1(
    navigationController: NavController
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan)
    ) {
        Text(text = "Pantalla 1", modifier = Modifier.align(Alignment.Center).clickable {
            navigationController.navigate(Routes.SCREEN_2.route)
        })
    }
}

@Composable
fun Screen2(
    navigationController: NavController
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Green)
    ) {
        Text(text = "Pantalla 2", modifier = Modifier.align(Alignment.Center).clickable {
            navigationController.navigate(Routes.SCREEN_3.route)
        })
    }
}

@Composable
fun Screen3(
    navigationController: NavController
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow)
    ) {
        Text(text = "Pantalla 3", modifier = Modifier.align(Alignment.Center).clickable {
            navigationController.navigate(Routes.SCREEN_4.createRoute(22))
        })
    }
}

@Composable
fun Screen4(
    navigationController: NavController,
    age: Int
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)
    ) {
        Text(text = "Parámetro: $age", modifier = Modifier.align(Alignment.Center).clickable {
            navigationController.navigate(Routes.SCREEN_5.route)
        })
    }
}

@Composable
fun Screen5(
    navigationController: NavController,
    name: String?
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)
    ) {
        Text(text = "Parámetro: $name", modifier = Modifier.align(Alignment.Center).clickable {
            navigationController.navigate(Routes.SCREEN_1.route)
        })
    }
}