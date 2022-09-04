package com.ssong_develop.learnjetpackcomposebyexample.animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import com.ssong_develop.learnjetpackcomposebyexample.R
import java.util.Collections.rotate

class Animation1Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RotatingSquareComponent()
        }
    }

    @Composable
    fun RotatingSquareComponent() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                // rememberInfiniteTransition is used to create a transition that uses infinite
                // child animations. Animations typically get invoked as soon as they enter the
                // composition so don't need to explicitly started.
                val infiniteTransition = rememberInfiniteTransition()

                // Create a value that is altered by the transition based on the configuration.
                // We use the animated float value the returns and updates a float from the initial value to
                // target value and repeats it
                val rotation by infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 360f,
                    animationSpec = infiniteRepeatable(
                        animation = tween<Float>(
                            durationMillis = 3000,
                            easing = FastOutLinearInEasing
                        )
                    )
                )

                androidx.compose.foundation.Canvas(modifier = Modifier.size(200.dp)) {
                    rotate(rotation) {
                        drawRect(color = Color(255,138,128))
                    }
                }
            }
        )
    }
}

