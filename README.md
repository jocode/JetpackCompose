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
### Combinando layouts