package com.ssong_develop.learnjetpackcomposebyexample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ssong_develop.learnjetpackcomposebyexample.customview.CustomViewActivity
import com.ssong_develop.learnjetpackcomposebyexample.customview.CustomViewPaintActivity
import com.ssong_develop.learnjetpackcomposebyexample.image.ImageActivity
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SimpleButton(
                            activity = this@MainActivity,
                            className = CustomViewActivity::class.java
                        )

                        SimpleButton(
                            activity = this@MainActivity,
                            className = ImageActivity::class.java
                        )

                        SimpleButton(
                            activity = this@MainActivity,
                            className = CustomViewPaintActivity::class.java
                        )
                    }
                }
            }
        }
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

