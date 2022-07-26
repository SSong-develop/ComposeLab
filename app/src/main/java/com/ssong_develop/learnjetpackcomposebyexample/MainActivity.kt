package com.ssong_develop.learnjetpackcomposebyexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ssong_develop.learnjetpackcomposebyexample.customview.CustomViewActivity
import com.ssong_develop.learnjetpackcomposebyexample.customview.CustomViewPaintActivity
import com.ssong_develop.learnjetpackcomposebyexample.customview.OneLineChipGroupView
import com.ssong_develop.learnjetpackcomposebyexample.image.ImageActivity
import com.ssong_develop.learnjetpackcomposebyexample.text.CustomTextActivity
import com.ssong_develop.learnjetpackcomposebyexample.ui.theme.LearnJetpackComposeByExampleTheme

class MainActivity : ComponentActivity() {

    private val itemView : @Composable (text : String) -> Unit = {
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .background(Color.Blue)
            ,
            text = it,
            color = Color.White
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnJetpackComposeByExampleTheme {
                // A surface container using the 'background' color from the theme
/*                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }*/
                val context = LocalContext.current
                var trigger by remember { mutableStateOf(false) }
                var text by remember { mutableStateOf("")}

                Row {
                    OneLineChipGroupView(
                        spacing = 4.dp,
                        callback = { _text, _trigger ->
                            trigger = _trigger
                            text = "+${_text}"
                        }
                    ) {
                        listOf("1dasfdas","2erqwereqwreqw","3fdsafdsafdasfdas","4","1","2","3","4","1","2","3","4","1","2","3","4","1","2","3","4").forEach {
                            itemView(it)
                        }
                    }

                    if (trigger) {
                        itemView(text)
                    }
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
            .fillMaxHeight(),
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

