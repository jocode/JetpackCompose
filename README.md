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