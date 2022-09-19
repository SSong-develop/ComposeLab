package com.ssong_develop.learnjetpackcomposebyexample.customview.zoomableimage

import androidx.appcompat.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource

@Composable
fun ZoomableImage(
    maxScale: Float = 2.0f,
    minScale: Float = 0.7f,
    modifier: Modifier
) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(1f) }
    var offsetY by remember { mutableStateOf(1f) }

    val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
        val changeScale = Math.min(maxScale, scale * zoomChange)
        scale = if (changeScale <= minScale) {
            minScale
        } else {
            changeScale
        }
        offsetX += panChange.x
        offsetY += panChange.y
    }

    Image(
        painter = painterResource(id = com.ssong_develop.learnjetpackcomposebyexample.R.drawable.test),
        contentDescription = null,
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(onDoubleTap = {
                    scale = 1f
                })
            }
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                translationX = offsetX
                translationY = offsetY
            }
            .transformable(state = state)
            .fillMaxSize()
    )
}