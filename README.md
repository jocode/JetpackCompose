# Jetpack Compose

## Layouts in Jetpack Compose

Un layout es un contenedor que nos permite alinear los componentes.
Los box, columns y rows son las vistas esenciales para trabajar con compose.

### Box
Un box es como un FrameLayout de los XML, son los layouts mas sencillos.
Con este layout, puedes alinear una vista en cualquier parte, por ejemplo centrada, arriba, abajo, derecha o izquierda.

```kotlin
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
```

### Column

Las columnas permiten alinear los elementos de forma vertical.

```kotlin
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
```

### Row

Las filas, permiten alinear el contenido de forma horizontal. Son como el linearLayout en XML de forma horizontal.

### Spacer

Los spacer es un componente, que nos permite crear espacios en las vistas. Ese esa es su finalidad.


## Estados en Compose

> :bulb: **Qué sn los estados?**
> Normalmente en android cambiabamos la vista a traves de ordenes. Los states por el contrario gestiona los cambios en la vista para que el componente haga el respectivo cambio.

### Recomposición

Es muy habitual en tipo de vistas declarativas. Lo que hace es que se crea nuevamente el componente cuando hay un cambio en la vista.

:star: Es muy importante entender los estados en Jetpack Compose

```kotlin
val counter = remember {
    mutableStateOf(0)
}
Column(
    Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Button(onClick = {
        counter.value += 1
    }) {
        Text(text = "Púlsame")
    }
    Text(text = "He sido pulsado ${counter.value} veces")
}
```


### RememberSaveable

Cuando la pantalla se gira, la activity de destruye y se vuelve a crear. Entonces el remember se resetea el valor y no lo conserva. El `rememberSaveable` mantiene el estado asi la vista se destruya.

```kotlin
val counter = rememberSaveable {
    mutableStateOf(0)
}
Column(
    Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Button(onClick = {
        counter.value += 1
    }) {
        Text(text = "Púlsame")
    }
    Text(text = "He sido pulsado ${counter.value} veces")
}
```

### State property

El state property, nos permtite modificar el valor del `mutableState` sin necesidad de acceder al *.value*.
Solamente reemplazando el `=` por `by` y importando el setter y getter.

```kotlin
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

var counter by rememberSaveable {
    mutableStateOf(0)
}

counter += 1
```

## Text

Un Text es básicamente un TextView en los XML, lo que nos permite mostrar texto.

```kotlin
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
```

## TextField

Los textField son básicamente EditText

```kotlin
var myText by remember {
    mutableStateOf("")
}
TextField(value = myText, onValueChange = {
    myText = it
})
```

## TextField avanzado

```kotlin
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
```

## OutlineTextField

Es un TextField que tiene un borde externo, como el del TextInput Layout en XML.

```kotlin
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
```

## State hoisting

La idea es hacer los componentes stateLess, es decir sin estados.
State hoisting es un patrón para quitar los estados de los composables.
La idea es colocarlo en el padre de todo, para que contenga el estado.

## Componente Button

Los botones, por defecto vienen con su lambda onClick, y se les debe definir el contenido que van a tener, como por ejemplo el texto.

```kotlin
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
}
```

## Outline Button

```kotlin
OutlinedButton(onClick = {
    /* Todo */
}) {
    Text(text = "Boton Outline")
}
```

## TextButton

Es un botón sin borde, es como el outline pero sin el borde.

## Componente Image

El componente imagen en android permite recibor in `painterResource` para obtener imágenes en mapas de bits y también se puede renderizar el contenido con iconos usando ``ImageVector para imagenes vectorizadas.

```kotlin
Image(imageVector = Icons.Default.Face, contentDescription = "ejemplo")
```

## Imágenes circulares

Con la propiedad de **clip** podemos personalizar nuestras imagenes por ejemplo, para hacer imagenes circulares sin necesidad de hacer uso de bibliotecas externas como lo tendríamos que hacer con XML.

```kotlin
Image(
    painter = painterResource(id = R.drawable.ic_launcher_foreground),
    contentDescription = "android",
    modifier = Modifier
        .clip(CircleShape)
        .border(5.dp, Color.Red, CircleShape)
        .background(Color.Blue)
)
```

## Icon

Jetpack compose trae otro componente, que es el `Icon` que ya viene con las especificaciones de material design para agregar íconos en la aplicación y nos permite cambiar el color de las imágenes de una forma más sencilla.

```kotlin
Icon(
    imageVector = Icons.Rounded.Star,
    contentDescription = "start",
    tint = Color.Blue
)
```

Si quieres usar todos los íconos de MaterialDesign se puede incluir la biblioteca en gradle. Sin embargo, esta biblioteca le añade peso extra a la aplicación.

- `implementation "androidx.compose.material:material-icons-extended:$compose_version"`

## Componente ProgressBar

Es una barra de progreso, para darle un feedback al usuario.

```kotlin
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
```

## Componentes de control de selección

### Switch

```kotlin
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
```

### Checkbox

```kotlin
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
```

### Checkbox con texto

```kotlin
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

// Data class
data class CheckInfo(val title: String, var selected: Boolean = false, var onCheckedChange: (Boolean) -> Unit)


// Code to generate a list of checkbox
val myOptions = getOptions(
    listOf("Black Panter", "Iron Man", "Thor")
)

Column {
    myOptions.forEach {
        MyCheckBoxWithText(it)
    }
}
```

## Checkbox TriState

Hay una especie de checkbox que también puede ser indeterminado. Que puede tener 3 estados, seleccionado, no seleccionado o indeterminado.

```kotlin
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
```

### Radio Button

Los radio buttons nos permite seleccionar una opción entre multiples opciones.
Generalmente los radio buttons van tienen 2 o mas opciones.
Para usarlo con múltiples radio buttons se usa el state hoisting

```kotlin
// Uso
var selected by remember {
    mutableStateOf("Tierra")
}
MyRadioButtonList(selected) {
    selected = it
}


// Componente
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
```

## Otros Componentes

### Card

Las Cards son componentes visuales de material design que son redondedas y tienen una elevacion.
Son muy útiles para hacer diseños elegantes en las aplicaciones.

```kotlin
Card(modifier = Modifier
    .fillMaxWidth()
    .padding(16.dp),
    elevation = 12.dp,
    shape = MaterialTheme.shapes.small,
    border = BorderStroke(1.dp, Color.Black)
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Soy contenido 1")
        Text(text = "Soy contenido 2")
        Text(text = "Soy contenido 3")
    }
}
```

## Surface

Las cards y las surfaces con similares. Sin embargo, las cards muestran contenidos y acciones en un tema simple. Una card es una surface que ya viene con contenido elaborado. como elevación, background y border redondeados.

### BadgeBox

Nos permite crear un circulo de notificaciones dentro de nuestros íconos.

```kotlin
@Composable
fun MyBadgeBox() {
    BadgedBox(
        badge = {
            Text(text = "100", modifier = Modifier
                .clip(CircleShape)
                .background(Color.LightGray)
                .padding(horizontal = 2.dp)
            )
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Icon(imageVector = Icons.Default.Notifications, contentDescription = "notifications")
    }
}
```

### Dropdown Menu

Son los spinner que se usaban en XML.

```kotlin
@Composable
fun MyDropDownMenu() {
    var selectedText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val desserts = listOf("Cookies and cream", "Chocolate", "Cookies", "Oreo", "Coffee", "Strawberries")


    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable {
                    expanded = true
                }
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            desserts.forEach { dessert ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    selectedText = dessert
                }) {
                    Text(text = dessert)
                }
            }
        }
    }
}
```

## Componente Slider

### Slider

Los slider nos permiten seleccionar un rango, y que el usuario pueda seleccionar el valor como en el volumen o el brillo del teléfono.

```kotlin
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
```

### RangeSlider

Nos permite hacer un slider con una opción inicial y una final, con un rango.

```kotlin
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
```

## Diálogos en Jetpack Compose

Los diálogos nos permiten mostrar información importante y necesaria al usuario.

```kotlin
var show by remember { mutableStateOf(false) }
Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
    Button(onClick = { show = true }) {
        Text(text = "Mostrar diálogo")
    }
    MyDialog(
        show,
        onDismiss = {
            show = false
        },
        onConfirm = {
            show = false
            Log.i("Jocode", "Dialogo confirmado")
        })
}

/* Component */
@Composable
fun MyDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (!show) return
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Titulo")
        },
        text = {
            Text(text = "Descripción del diálogo")
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "Confirm Button")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Dismiss Button")
            }
        }
    )
}
```

## RecyclerView :star:

Los recyclerView en android nos permiten mostrar un listado de elementos. Cuando se trabaja con XML se deben definir un adaptador para suministrar los datos a cada item y configurar el widget en la vista que se va a usar. Siempre es arto trabajo el que se debe realizar para mostrar un listado usando las vistas con XML. Sin embargo, con Jetpack compose nos ahorramos todo este proceso siendo más sencillo de implementar las listas con tan solo usar el `LazyColum` o el `LazyRow`.

```kotlin
val myList = listOf("Juan", "Maria", "Felipe", "Emith", "Camilo", "Daniela", "Julián")
LazyColumn {
    items(myList) {
        TwitterCardAlt()
    }
}
```

Para usar un recyclerView en forma de grilla solo debemos usar el `LazyVerticalGrid`

```kotlin
val context = LocalContext.current
LazyVerticalGrid(cells = GridCells.Fixed(2)) {
    items(superHeroes) { superHero ->
        ItemHero(superHero = superHero) {
            Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
        }
    }
}
```

Para controlar el desplazamiento del recyclerView lo podemos hacer con `val rvState = rememberLazyListState()`

## Cabeceras (Sticky Header)

El sticky header nos permite mantener una vista mientras se hace scroll para mostrar la siguiente. Es como las letras en la aplicación de teléfono, que al buscar un contacto los ordena por orden alfabético y dependiendo de la posición en la que se encuentre muestra la respectiva letra en la parte superior.

Con compose es muy sencillo de hacer con un nuevo componenete que es el **`stickyHeader`**:

```kotlin
val context = LocalContext.current
val superHeroesMap: Map<String, List<SuperHero>> = superHeroes.groupBy { it.publisher }

LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {

    superHeroesMap.forEach { (publisher, data) ->
        stickyHeader {
            Text(
                text = publisher,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
        items(data) { superHero ->
            ItemHero(superHero = superHero) { superHeroItem ->
                Toast.makeText(context, superHeroItem.superHeroName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
```

## Componentes en Jetpack Compose

### Scaffold

El Scaffold es un layout más, es como otro contenedor y está creado para facilitar la creación de vistas.


### TopAppBar

En compose se tiene un widget para la Toolbar que se llama `TopAppBar`

```kotlin
TopAppBar(
    title = {
        Text(text = "Toolbar")
    },
    backgroundColor = Color.Black,
    contentColor = Color.White,
    elevation = 4.dp,
    navigationIcon = {
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
        }
    },
    actions = {
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "search")
        }
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "add")
        }
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "menu")
        }
    }
)
```

### Snackbar

El snackbar cambia un poco respecto al android normal.
Para ello se necesita el estado del scaffold y usaremos `rememberScaffoldState()`


```kotlin
val scaffoldState = rememberScaffoldState()
val coroutineScope = rememberCoroutineScope()

Scaffold(
    topBar = {
        MyTopAppBar { action ->
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar("Has pulsado $action")
            }
        }
    },
    scaffoldState = scaffoldState
) {
    Box(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(Color.Blue)
    )
}
```

### BottomBar

En compose, el bottom navigation se puede implementar de una forma sencilla. Usando los componetes que ya nos provee android con compose.

```kotlin
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
```

### Floating Action Button

Todos estos componentes se pueden ubicar fácilmente en la pantalla usando el Scaffold.

```kotlin
FloatingActionButton(
    onClick = {},
    backgroundColor = Color.Green
) {
    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
}
```

### Modal Drawer

El Drawer en compose, se puede diseñar como se quiera. Ahí lo importante es usarlo en el scaffold dentro de las propiedades de `drawerContent` para que éste componente lo adecúe de la forma correcta. 
En caso de que se quieran manejar los gestos, es necesario hacer uso del `scaffoldState` para abrir o cerrar el drawer.

```kotlin
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
```

## Navegación

Ahora en compose la idea 
principal es usar una signleActivity y que cada vista se encargue de la lógica.
La navegación se trabaja con rutas, usando el state `rememberNavController`

```kotlin
val navigationController = rememberNavController()

NavHost(navController = navigationController, startDestination = "screen1") {
    composable("screen1") {
        Screen1(navigationController)
    }
    composable("screen2") {
        Screen2(navigationController)
    }
    composable("screen3") {
        Screen3(navigationController)
    }
}
```

### Navegar con argumentos

Los argumentos se pasan como parámetros dentro de la URi especificada en la ruta.

```kotlin
composable(
    Routes.SCREEN_4.route,
    arguments = listOf(
        navArgument("age") {
            type = NavType.IntType
        }
    )
) { backStackEntry ->

    Screen4(
        navigationController,
        backStackEntry.arguments?.getInt("age") ?: 0
    )
}
```

## Arquitectura MVVM con Jetpack Compose

Una arquitectura permite organizar el código haciendo que las aplicaciones sean escalables.

Model-View-ViewModel
- *Modelo*: Representa toda la parte de datos
- *View*: Las screen de compose, activities
- *ViewModel*: Es el que conecta el model con las vistas, el viewModel gestiona la información que se suministra a la vista.