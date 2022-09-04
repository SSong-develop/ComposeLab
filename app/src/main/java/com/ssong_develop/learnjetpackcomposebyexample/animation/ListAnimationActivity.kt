package com.ssong_develop.learnjetpackcomposebyexample.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ssong_develop.learnjetpackcomposebyexample.core.Person
import com.ssong_develop.learnjetpackcomposebyexample.core.getPersonList

@ExperimentalAnimationApi
class ListAnimationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListAnimationComponent(personList = getPersonList())
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ListAnimationComponent(personList: List<Person>) {

    val deletedPersonList = remember { mutableStateListOf<Person>() }

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(
            items = personList,
            itemContent = { index, person ->
                // AnimatedVisibility is a pre-defined composable that automatically animates the
                // appearance and disappearance of it's content. This makes it super easy to animated
                // things like insertion/deletion of a list element. The visible property tells the
                // AnimatedVisibility about whether to show the composable that it wraps (in this case,
                // the Card that you see below). This is where you can add logic about whether a certain
                // element needs to either be shown or not. In our case, we want to show an element, only
                // if its not a part of the deletedPersonList list. As this list changes and a given
                // person is either shown or hidden from the screen, the "enter" and "exit" animations
                // are called for a given item. AnimatedVisibility also let's you specify the enter and
                // exit animation so that you have full control over how you'd like to animate it's enter
                // or exit. In the example below, since I also added functionality to delete an item, I
                // customize the exit animation to be an animation that shrinks vertically and gave the
                // animation a duration of 1000ms
                AnimatedVisibility(
                    visible = !deletedPersonList.contains(person),
                    enter = expandVertically(),
                    exit = shrinkVertically(
                        animationSpec = tween(
                            durationMillis = 1000
                        )
                    )
                ) {
                    Card(
                        shape = RoundedCornerShape(4.dp),
                        backgroundColor = com.ssong_develop.learnjetpackcomposebyexample.core.colors[index % com.ssong_develop.learnjetpackcomposebyexample.core.colors.size],
                        modifier = Modifier.fillParentMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillParentMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = person.name,
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center
                                ),
                                modifier = Modifier.padding(16.dp)
                            )
                            IconButton(
                                onClick = {
                                    deletedPersonList.add(person)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Delete"
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}