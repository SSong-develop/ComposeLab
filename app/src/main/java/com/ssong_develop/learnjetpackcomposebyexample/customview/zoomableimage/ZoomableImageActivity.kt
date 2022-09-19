package com.ssong_develop.learnjetpackcomposebyexample.customview.zoomableimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class ZoomableImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZoomableImageComponent()
        }
    }
}

@Composable
fun ZoomableImageComponent() {
    ZoomableImage(
        modifier = Modifier.fillMaxSize()
    )
}
