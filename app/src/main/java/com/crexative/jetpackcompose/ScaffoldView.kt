package com.crexative.jetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Preview
@Composable
fun ScreenPreviewScaffold() {
    ScaffoldExample()
}


@Composable
fun ScaffoldExample() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            MyTopAppBar(onClickIcon = { action ->
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Has pulsado $action")
                }
            },
                onClickDrawer = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                })
        },
        scaffoldState = scaffoldState,
        bottomBar = {
            MyBottomNavigation()
        },
        floatingActionButton = {
            MyFab()
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false,
        drawerContent = {
            MyDrawer {
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        },
        drawerGesturesEnabled = true
    ) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(Color.Blue)
        )
    }
}

@Composable
fun MyTopAppBar(onClickIcon: (String) -> Unit, onClickDrawer: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "Toolbar")
        },
        backgroundColor = Color.Black,
        contentColor = Color.White,
        elevation = 4.dp,
        navigationIcon = {
            IconButton(onClick = {
                onClickDrawer()
            }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = {
                onClickIcon("Buscar")
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            }
            IconButton(onClick = {
                onClickIcon("Agregar")
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
            IconButton(onClick = {
                onClickIcon("Menu")
            }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite")
            }
        }
    )
}

@Composable
fun MyBottomNavigation() {
    var index by remember {
        mutableStateOf(0)
    }
    BottomNavigation(
        backgroundColor = Color.Black,
        contentColor = Color.White
    ) {
        BottomNavigationItem(
            selected = index == 0,
            onClick = {
                index = 0
            },
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = "home")
            },
            label = {
                Text(text = "Home")
            }
        )
        BottomNavigationItem(
            selected = index == 1,
            onClick = {
                index = 1
            },
            icon = {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite")
            },
            label = {
                Text(text = "Favorite")
            }
        )
        BottomNavigationItem(
            selected = index == 2,
            onClick = {
                index = 2
            },
            icon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Person")
            },
            label = {
                Text(text = "Person")
            }
        )
    }
}

@Composable
fun MyFab() {
    FloatingActionButton(
        onClick = {},
        backgroundColor = Color.Green
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
    }
}

@Composable
fun MyDrawer(onCloseDrawer: () -> Unit) {
    val items = listOf(
        "Primer Opcion", "Segunda Opción", "Tercera Opción", "Cuarta Opción", "Etc..."
    )
    Column(modifier = Modifier.padding(8.dp)) {
        items.forEach { option ->
            Text(
                text = option,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        onCloseDrawer()
                    })
        }
    }
}