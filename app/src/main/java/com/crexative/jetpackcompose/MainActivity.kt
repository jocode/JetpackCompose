package com.crexative.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crexative.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyBox()
                }
            }
        }
    }
}

@Composable
fun MyCombineLayuot() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Cyan),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Jetpack")
        }
        MySpacer(size = 5)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Red)
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Hola", color = Color.White)
            }
            Spacer(modifier = Modifier
                .width(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Blue)
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Mundo", color = Color.White)
            }
        }

        MySpacer(size = 5)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Green),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(text = "Compose")
        }
    }
}

@Composable
fun MySpacer(size: Int) {
    Spacer(modifier = Modifier.height(size.dp))
}

@Composable
fun MyRow() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "La fila")
        Text(text = "La fila")
        Text(text = "La fila")
        Text(text = "La fila")
    }
}

@Composable
fun MyColumn() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Hola 1", modifier = Modifier.background(Color.Red))
        Text(text = "Hola 2", modifier = Modifier.background(Color.Blue))
        Text(text = "Hola 3", modifier = Modifier.background(Color.Green))
        Text(text = "Hola 4", modifier = Modifier.background(Color.Black))
    }
}

@Composable
fun MyBox() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(Color.Cyan)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {
        MyCombineLayuot()
    }
}