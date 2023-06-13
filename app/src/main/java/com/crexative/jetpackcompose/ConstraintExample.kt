package com.crexative.jetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ConstraintExample1() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (redBox, blueBox, yellowBox, magentaBox, darkGrayBox, greenBox, whiteBox) = createRefs()

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Blue)
            .constrainAs(blueBox) {
                top.linkTo(redBox.bottom)
                start.linkTo(parent.start)
                end.linkTo(redBox.start)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                top.linkTo(blueBox.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Magenta)
            .constrainAs(magentaBox) {
                top.linkTo(yellowBox.bottom)
                start.linkTo(yellowBox.end)
                end.linkTo(parent.end)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.DarkGray)
            .constrainAs(darkGrayBox) {
                top.linkTo(magentaBox.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Green)
            .constrainAs(greenBox) {
                top.linkTo(darkGrayBox.bottom)
                start.linkTo(parent.start)
                end.linkTo(darkGrayBox.start)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.White)
            .constrainAs(whiteBox) {
                top.linkTo(greenBox.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
    }
}


@Composable
fun ConstraintExampleGuide() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val startGuide = createGuidelineFromStart(0.5f)

        val (redBox, blueBox) = createRefs()

        Box(
            modifier = Modifier
                .size(125.dp)
                .background(Color.Red)
                .constrainAs(redBox) {
                    top.linkTo(parent.top)
                    start.linkTo(startGuide)
                }
        )

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Blue)
            .constrainAs(blueBox) {
                top.linkTo(redBox.bottom)
                end.linkTo(startGuide)
            })
    }
}

@Composable
fun ConstraintBarrier() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (yellowBox, blueBox, redBox) = createRefs()
        val barrier = createEndBarrier(yellowBox, blueBox)

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                start.linkTo(parent.start, margin = 16.dp)
            })

        Box(modifier = Modifier
            .size(225.dp)
            .background(Color.Blue)
            .constrainAs(blueBox) {
                top.linkTo(yellowBox.bottom)
                start.linkTo(parent.start, margin = 32.dp)
            })

        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
                start.linkTo(barrier)
            })
    }
}

@Preview
@Composable
fun ConstraintChainsExample() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (yellowBox, blueBox, redBox) = createRefs()

        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                start.linkTo(parent.start)
                end.linkTo(blueBox.start)
            })

        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Blue)
            .constrainAs(blueBox) {
                start.linkTo(yellowBox.end)
                end.linkTo(redBox.start)
            })

        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
                start.linkTo(blueBox.end)
                end.linkTo(parent.end)
            })

        createHorizontalChain(yellowBox, blueBox, redBox, chainStyle = ChainStyle.Packed)
    }
}