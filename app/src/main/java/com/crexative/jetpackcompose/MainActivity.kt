package com.crexative.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.createBitmap
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

                    val myOptions = getOptions(
                        listOf("Black Panter", "Iron Man", "Thor")
                    )

                    var selected by remember {
                        mutableStateOf("Tierra")
                    }
                    MyRadioButtonList(selected) {
                        selected = it
                    }

                }
            }
        }
    }
}

@Composable
fun MyStateExample() {
    var counter by rememberSaveable {
        mutableStateOf(0)
    }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            counter += 1
        }) {
            Text(text = "Púlsame")
        }
        Text(text = "He sido pulsado $counter veces")
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
            Spacer(
                modifier = Modifier
                    .width(4.dp)
            )
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

@Composable
fun MyText() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Esto es un ejemplo")
        Text(text = "Esto es un ejemplo", color = Color.Blue)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.ExtraBold)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.Light)
        Text(text = "Esto es un ejemplo", fontFamily = FontFamily.Monospace)
        Text(text = "Esto es un ejemplo", style = TextStyle(fontFamily = FontFamily.Cursive))
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(textDecoration = TextDecoration.LineThrough)
        )
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        Text(
            text = "Esto es un ejemplo", style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(
                        TextDecoration.Underline,
                        TextDecoration.LineThrough
                    )
                )
            )
        )
        Text(text = "Esto es un ejemplo", fontSize = 30.sp)
    }
}


@Composable
fun MyTextField() {
    var myText by remember {
        mutableStateOf("")
    }
    TextField(value = myText, onValueChange = {
        myText = it
    })
}

@Composable
fun MyTextFieldAdvance() {
    var myText by remember { mutableStateOf("") }

    TextField(
        value = myText,
        onValueChange = {
            myText = it
        },
        label = {
            Text(text = "Introduce tu nombre")
        }
    )
}

@Composable
fun MyTexFieldOutline() {
    var myText by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = myText, onValueChange = {
            myText = it
        },
        modifier = Modifier.padding(24.dp),
        label = { Text(text = "Introduce tu nombre") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Magenta,
            unfocusedBorderColor = Color.Blue
        )
    )
}

@Composable
fun MyButtonExample() {
    var enabled by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White
            ),
            border = BorderStroke(3.dp, Color.Red),
            enabled = enabled,
            onClick = {
                enabled = !enabled
            }
        ) {
            Text(text = "Soy un botón")
        }

        OutlinedButton(onClick = {
            /* Todo */
        }) {
            Text(text = "Boton Outline")
        }

        MyImage()

        MyRoundedImage()

        MyIcon()
    }
}

@Composable
fun MyImage() {
    Image(imageVector = Icons.Default.Face, contentDescription = "ejemplo")
}

@Composable
fun MyRoundedImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "android",
        modifier = Modifier
            .clip(CircleShape)
            .border(5.dp, Color.Red, CircleShape)
            .background(Color.Blue)
    )
}

@Composable
fun MyIcon() {
    Icon(
        imageVector = Icons.Rounded.Star,
        contentDescription = "start",
        tint = Color.Blue
    )
}

@Composable
fun MyProgress() {
    var showLoading by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        if (showLoading) {
            CircularProgressIndicator(
                color = Color.Green,
                strokeWidth = 1.dp
            )
            LinearProgressIndicator(
                color = Color.White,
                backgroundColor = Color.Black
            )
        }

        Button(onClick = { showLoading = !showLoading }) {
            Text(text = "Mostrar progress bar")
        }

    }
}

@Composable
fun MyProgressAdvance() {

    var progressBarStatus by rememberSaveable {
        mutableStateOf(0f)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        CircularProgressIndicator(progress = progressBarStatus)

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                progressBarStatus -= 0.1f
            }) {
                Text(text = "Decrementar")
            }
            Button(onClick = {
                progressBarStatus += 0.1f
            }) {
                Text(text = "Incrementar")
            }
        }
    }
}

@Composable
fun MySwitch() {
    var state by rememberSaveable {
        mutableStateOf(true)
    }

    Switch(
        checked = state,
        onCheckedChange = {
            state = !state
        },
        enabled = true,
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Green,
            checkedThumbColor = Color.Blue,
            uncheckedTrackColor = Color.LightGray
        )
    )
}

@Composable
fun MyCheckBox() {
    var state by rememberSaveable {
        mutableStateOf(false)
    }

    Checkbox(
        checked = state,
        onCheckedChange = {
            state = !state
        },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Black,
            uncheckedColor = Color.LightGray,
            checkmarkColor = Color.Green
        )
    )
}

@Composable
fun MyCheckBoxWithText(checkInfo: CheckInfo) {

    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = checkInfo.onCheckedChange,

            )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = checkInfo.title, modifier = Modifier.clickable {
            checkInfo.onCheckedChange(!checkInfo.selected)
        })
    }
}

@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var state by rememberSaveable {
            mutableStateOf(false)
        }
        CheckInfo(
            title = it,
            selected = state,
            onCheckedChange = { myNewState -> state = myNewState }
        )
    }
}

@Composable
fun MyTriStateCheckbox() {
    var status by rememberSaveable {
        mutableStateOf(ToggleableState.Off)
    }

    TriStateCheckbox(state = status, onClick = {
        status = when (status) {
            ToggleableState.Off -> ToggleableState.On
            ToggleableState.On -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.Off
        }
    })
}

@Composable
fun MyRadioButton() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = false,
            onClick = {

            },
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Green,
                unselectedColor = Color.Black,
                disabledColor = Color.Gray
            )
        )
        Text(text = "Radio 1")
    }
}

@Composable
fun MyRadioButtonList(name: String, onItemSelected: (String) -> Unit) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            RadioButton(selected = name == "Mercurio", onClick = { onItemSelected("Mercurio") })
            Text(text = "Mercurio")
        }
        Row  {
            RadioButton(selected = name == "Venus", onClick = { onItemSelected("Venus") })
            Text(text = "Venus")
        }
        Row {
            RadioButton(selected = name == "Tierra", onClick = { onItemSelected("Tierra") })
            Text(text = "Tierra")
        }
        Row {
            RadioButton(selected = name == "Marte", onClick = { onItemSelected("Marte") })
            Text(text = "Marte")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {
        MyButtonExample()
    }
}