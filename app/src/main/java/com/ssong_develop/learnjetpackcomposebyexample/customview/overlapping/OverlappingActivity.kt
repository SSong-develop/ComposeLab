package com.ssong_develop.learnjetpackcomposebyexample.customview.overlapping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ssong_develop.learnjetpackcomposebyexample.R

class OverlappingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OverlappingComponent()
        }
    }
}

@Composable
fun OverlappingComponent() {
    OverlappingRow(
        overlapFactor = 0.7f
    ) {
        val images = intArrayOf(
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground
        )
        for (i in images.indices) {
            Image(
                painter = painterResource(id = images[i]),
                contentDescription = null,
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
                    .border(width = 1.dp, color = Color.White, shape = CircleShape)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop

            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(64.dp)
                .height(64.dp)
                .border(width = 1.dp, color = Color.Black, shape = CircleShape)
                .clip(CircleShape)
                .background(Color.White),

            ) {
            Text(
                text = "10+",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,

                )
        }
    }
}