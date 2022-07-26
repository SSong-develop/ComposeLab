package com.ssong_develop.learnjetpackcomposebyexample.customview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MeasuringScaleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Composable
fun ScaleLineComponent(index: Int) {
    val isDivisibleBy10 = index % 10 == 0

    val surfaceColor = MaterialTheme.colors.surface

    val onSurfaceColor = MaterialTheme.colors.onSurface

    Column(modifier = Modifier.background(color = surfaceColor)) {

        Canvas(
            modifier = Modifier.padding(5.dp).height(100.dp).width(3.dp)
        ) {
            drawLine(
                color = onSurfaceColor,
                start = Offset(0f, 0f),
                end = Offset(0f, if (isDivisibleBy10) size.height else size.height * 0.2f),
                strokeWidth = if (isDivisibleBy10) size.width else size.width * 0.3f
            )
        }

        Text(
            text = if (isDivisibleBy10) "${index / 10}" else "",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily.Monospace),
            color = onSurfaceColor,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ScaleCenterPointer() {
    // Primary color from the color palette specified by the applied Theme. In out case, its
    // what we specify in the CustomTheme Composable
    val primaryColor = MaterialTheme.colors.primary

    Column {
        // We use the Canvas composable that gives you access to a canvas that you can draw
        // into. We also pass it a modifier

        Canvas(
            modifier = Modifier.padding(5.dp).height(120.dp).width(3.dp)
        ) {
            drawLine(
                color = primaryColor,
                start = Offset(0f, 0f),
                end = Offset(0f, size.height),
                strokeWidth = size.width
            )
        }
    }
}

@Composable
fun CustomTheme(children: @Composable() () -> Unit) {
    val lightColors = lightColors()

    val darkColors = darkColors()

    val color = if (isSystemInDarkTheme()) darkColors else lightColors

    MaterialTheme(colors = color) {
        children
    }
}