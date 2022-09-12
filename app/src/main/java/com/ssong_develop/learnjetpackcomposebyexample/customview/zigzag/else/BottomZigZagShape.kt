package com.ssong_develop.learnjetpackcomposebyexample.customview.zigzag.`else`

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.ssong_develop.learnjetpackcomposebyexample.customview.zigzag.painter.BottomZigZagPainter

class BottomZigZagShape(
    private val zigZagType: ZigZagType,
    private val waveCount: Int = 15,
    private val dx: Float
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val pathPainter = BottomZigZagPainter(
            zigZagType = zigZagType,
            size = size,
            waveCount = waveCount,
            dx = dx
        )
        return Outline.Generic(
            path = pathPainter.drawZigZagBottomPath()
        )
    }
}