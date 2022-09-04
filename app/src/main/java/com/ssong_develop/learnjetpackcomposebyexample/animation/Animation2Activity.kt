package com.ssong_develop.learnjetpackcomposebyexample.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

// Do not working
class Animation2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimateColorComponent()
        }
    }
}

@Composable
fun AnimateColorComponent() {
    var currentColor by remember { mutableStateOf(Color.Red) }
    // updateTransition creates a transition that is useful for developing animations.
    // It takes in a target value and it transition the child animations towards the target value.
    val transition = updateTransition(targetState = currentColor, label = "")

    val color by transition.animateColor(
        transitionSpec = { TweenSpec<Color>(durationMillis = 2000) },
        label = "animation2"
    ) { state ->
        when (state) {
            Color.Red -> Color.Green
            Color.Green -> Color.Blue
            Color.Blue -> Color.Red
            else -> Color.Black
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color)
    ){}
}

