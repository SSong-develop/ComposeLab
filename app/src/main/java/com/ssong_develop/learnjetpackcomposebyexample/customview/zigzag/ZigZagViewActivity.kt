package com.ssong_develop.learnjetpackcomposebyexample.customview.zigzag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

// Show ZigZag
// And Path with Animation
class ZigZagViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZigZagComponent()
        }
    }
}

@Composable
fun ZigZagComponent() {
    ZigZagWaveComposable(
        modifier = Modifier.background(color = Color.Green),
        waveHeight = 50f
    )
}