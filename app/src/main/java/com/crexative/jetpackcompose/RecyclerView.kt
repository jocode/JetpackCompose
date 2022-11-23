package com.crexative.jetpackcompose

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crexative.jetpackcompose.model.SuperHero
import kotlinx.coroutines.launch

@Composable
fun SimpleRecyclerView() {
    val myList = listOf("Juan", "Maria", "Felipe", "Emith", "Camilo", "Daniela", "Julián")
    LazyColumn {

        items(myList) {
            TwitterCardAlt()
        }

        items(7) {
            TwitterCard()
        }
    }
}

@Preview
@Composable
fun screenView() {
    SuperStickyView()
}

@Composable
fun SuperHeroView() {
    val context = LocalContext.current
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(superHeroes) { superHero ->
            ItemHero(superHero = superHero) { superHero ->
                Toast.makeText(context, superHero.superHeroName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun SuperHeroWithSpecialControlsView() {
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(superHeroes) { superHero ->
                ItemHero(superHero = superHero) { superHero ->
                    Toast.makeText(context, superHero.superHeroName, Toast.LENGTH_SHORT).show()
                }
            }
        }

        val showButton by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0
            }
        }

        if (showButton) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        rvState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            ) {
                Text(text = "Scroll Up")
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroGridView() {
    val context = LocalContext.current
    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(superHeroes) { superHero ->
            ItemHero(superHero = superHero) {
                Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperStickyView() {
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
}

@Composable
fun ItemHero(superHero: SuperHero, onItemSelected: (SuperHero) -> Unit) {
    Card(border = BorderStroke(2.dp, Color.Red), modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onItemSelected(superHero)
        }) {
        Column {
            Image(
                painter = painterResource(id = superHero.photo),
                contentDescription = superHero.superHeroName,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superHero.superHeroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superHero.superHeroName,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superHero.publisher,
                fontSize = 10.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp)
            )
        }
    }
}

val superHeroes = listOf(
    SuperHero("Spiderman", "Peter Parker", "Marvel", R.drawable.spiderman),
    SuperHero("Wolverine", "James Howllet", "Marvel", R.drawable.logan),
    SuperHero("Batman", "Bruce Wayne", "DC", R.drawable.batman),
    SuperHero("Thor", "Thor Odinson", "Marvel", R.drawable.thor),
    SuperHero("Flash", "Jay Garrick", "DC", R.drawable.flash),
    SuperHero("Green Lantern", "Alan Scott", "DC", R.drawable.green_lantern),
    SuperHero("Wonder Woman", "Diana", "DC", R.drawable.wonder_woman),
)

