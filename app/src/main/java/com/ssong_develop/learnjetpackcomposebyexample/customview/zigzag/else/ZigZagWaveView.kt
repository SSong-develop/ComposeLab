package com.ssong_develop.learnjetpackcomposebyexample.customview.zigzag.`else`

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun ZigZagWaveComposable(
    modifier: Modifier,
    waveHeight: Float
) {
    val deltaXAnimation = rememberInfiniteTransition()
    val dx by deltaXAnimation.animateFloat(
        initialValue = 0f,
        targetValue = waveHeight,
        animationSpec = infiniteRepeatable(animation = tween(500,easing = LinearEasing))
    )

    Card(
        modifier = modifier
            .size(300.dp, 200.dp)
            .graphicsLayer {
                shadowElevation = 0.dp.toPx()
                shape = BottomZigZagShape(
                    ZigZagType.ANIMATION,
                    15,
                    dx = dx
                )
                clip = true
            }
    ) {
        Column {
            Text("hello!")
        }
    }
}