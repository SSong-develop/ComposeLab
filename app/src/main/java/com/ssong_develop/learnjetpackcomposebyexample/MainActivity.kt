@file:OptIn(ExperimentalAnimationApi::class)

package com.ssong_develop.learnjetpackcomposebyexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ssong_develop.learnjetpackcomposebyexample.animation.Animation1Activity
import com.ssong_develop.learnjetpackcomposebyexample.animation.Animation2Activity
import com.ssong_develop.learnjetpackcomposebyexample.animation.ListAnimationActivity
import com.ssong_develop.learnjetpackcomposebyexample.customview.CustomViewActivity
import com.ssong_develop.learnjetpackcomposebyexample.customview.CustomViewPaintActivity
import com.ssong_develop.learnjetpackcomposebyexample.customview.OneLineChipGroupView
import com.ssong_develop.learnjetpackcomposebyexample.customview.zigzag.ZigZagViewActivity
import com.ssong_develop.learnjetpackcomposebyexample.image.ImageActivity
import com.ssong_develop.learnjetpackcomposebyexample.text.CustomTextActivity
import com.ssong_develop.learnjetpackcomposebyexample.ui.theme.LearnJetpackComposeByExampleTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnJetpackComposeByExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TestScreen(activity = this)
                }
            }
        }
    }
}

@Composable
fun TestScreen(
    activity: MainActivity
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleButton(
            activity = activity,
            className = CustomViewActivity::class.java
        )
        SimpleButton(
            activity = activity,
            className = ImageActivity::class.java
        )
        SimpleButton(
            activity = activity,
            className = CustomViewPaintActivity::class.java
        )
        SimpleButton(
            activity = activity,
            className = CustomTextActivity::class.java
        )
        SimpleButton(
            activity = activity,
            className = Animation1Activity::class.java
        )
        SimpleButton(
            activity = activity,
            className = Animation2Activity::class.java
        )
        SimpleButton(
            activity = activity,
            className = ListAnimationActivity::class.java
        )
        SimpleButton(
            activity = activity,
            className = ZigZagViewActivity::class.java
        )
    }
}

@Composable
fun <T> SimpleButton(
    activity: MainActivity,
    className: Class<T>,
) {
    val context = activity.baseContext.applicationContext
    Button(
        onClick = {
            activity.startActivity(Intent(context, className))
        }
    ) {
        Text(className.simpleName)
    }
}

