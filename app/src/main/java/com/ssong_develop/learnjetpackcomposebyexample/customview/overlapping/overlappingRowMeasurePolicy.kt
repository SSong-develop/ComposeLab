package com.ssong_develop.learnjetpackcomposebyexample.customview.overlapping

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy

// overlappingFactor decides how much of each child composable will be
// visible before next one overlaps it 1.0 means completely visible
// 0.7 means 70%
// 0.5 means 50% visible and so on...
fun overlappingRowMeasurePolicy(overlapFactor: Float) = MeasurePolicy { measurables, constraints ->
    val placeables = measurables.map { measurable -> measurable.measure(constraints) }
    val height = placeables.maxOf { it.height }
    val width = (placeables.subList(1, placeables.size)).sumOf { it.width }
    layout(width, height) {
        var xPosition = 0
        for (placeable in placeables) {
            placeable.placeRelative(xPosition, 0, 0f)
            xPosition += (placeable.width * overlapFactor).toInt()
        }
    }
}

@Composable
fun OverlappingRow(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.1, to = 1.0) overlapFactor: Float = 0.5f,
    content: @Composable () -> Unit
) {
    val measurePolicy = overlappingRowMeasurePolicy(overlapFactor)
    Layout(
        measurePolicy = measurePolicy,
        content = content,
        modifier = modifier
    )
}