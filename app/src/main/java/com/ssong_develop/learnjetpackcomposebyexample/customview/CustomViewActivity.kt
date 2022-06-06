package com.ssong_develop.learnjetpackcomposebyexample.customview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class CustomViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout
        setContent {
            CustomViewComponent()
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun CustomViewComponent() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            color = Color.Red,
            radius = 300f
        )

        drawCircle(
            color = Color.Green,
            radius = 200f
        )

        drawCircle(
            color = Color.Blue,
            radius = 100f
        )
    }
}

@Preview
@Composable
fun CustomViewComponentPreview() {
    CustomViewComponent()
}