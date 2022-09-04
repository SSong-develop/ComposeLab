package com.ssong_develop.learnjetpackcomposebyexample.core

import androidx.compose.ui.graphics.Color

data class Person(
    val name: String,
    val age: Int,
    val profilePictureUrl: String? = null
)

fun getPersonList() = listOf(
    Person("Grace Hopper", 25),
    Person("Ada Lovelace", 29),
    Person("John Smith", 28),
    Person("Elon Musk", 41),
    Person("Will Smith", 31),
    Person("Robert James", 42),
    Person("Anthony Curry", 91),
    Person("Kevin Jackson", 22),
    Person("Robert Curry", 1),
    Person("John Curry", 9),
    Person("Ada Jackson", 2),
    Person("Joe Defoe", 35)
)

val colors = listOf(
    Color(0xFFffd7d7.toInt()),
    Color(0xFFffe9d6.toInt()),
    Color(0xFFfffbd0.toInt()),
    Color(0xFFe3ffd9.toInt()),
    Color(0xFFd0fff8.toInt())
)