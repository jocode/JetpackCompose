package com.crexative.jetpackcompose.model

sealed class Routes(val route: String) {
    object SCREEN_1 : Routes("pantalla1")
    object SCREEN_2 : Routes("pantalla2")
    object SCREEN_3 : Routes("pantalla3")
    object SCREEN_4 : Routes("pantalla4/{age}") {
        fun createRoute(age: Int) = "pantalla4/$age"
    }
    object SCREEN_5 : Routes("pantalla5?name={name}") {
        fun createRoute(name: String) = "pantalla5?name=$name"
    }
}
