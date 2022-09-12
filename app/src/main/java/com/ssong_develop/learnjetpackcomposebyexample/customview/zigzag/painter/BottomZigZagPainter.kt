package com.ssong_develop.learnjetpackcomposebyexample.customview.zigzag.painter

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import com.ssong_develop.learnjetpackcomposebyexample.customview.zigzag.ZigZagType
import com.ssong_develop.learnjetpackcomposebyexample.customview.zigzag.extension.drawCircularBottomZigZag
import com.ssong_develop.learnjetpackcomposebyexample.customview.zigzag.extension.drawCircularBottomZigZagAnimation
import com.ssong_develop.learnjetpackcomposebyexample.customview.zigzag.extension.drawLinearBottomZigZag
import com.ssong_develop.learnjetpackcomposebyexample.customview.zigzag.extension.drawSquareBottomZigZag

class BottomZigZagPainter(
    private val zigZagType: ZigZagType,
    private val size: Size,
    private val waveCount: Int,
    private val dx: Float
) : BottomZigZagPathPainter {
    override fun drawZigZagBottomPath(): Path {
        val path = Path()
        when (zigZagType) {
            ZigZagType.CIRCULAR -> path.drawCircularBottomZigZag(waveCount, size)
            ZigZagType.LINEAR -> path.drawLinearBottomZigZag(waveCount, size)
            ZigZagType.SQUARE -> path.drawSquareBottomZigZag(waveCount, size)
            ZigZagType.ANIMATION -> path.drawCircularBottomZigZagAnimation(
                waveCount,
                size,
                size.width / (waveCount + 1),
                dx
            )
        }
        return path
    }
}