package com.ssong_develop.learnjetpackcomposebyexample.customview.zigzag.extension

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path

fun Path.drawCircularBottomZigZag(
    waveCount: Int,
    size: Size
) {
    val waveLength = size.width / (waveCount + 1)
    val waveHeight = waveLength / 3
    val gap = 3 * waveLength / 4

    reset()
    moveTo(0f, 0f)
    lineTo(size.width, 0f)
    lineTo(size.width, size.height)
    arcTo(
        rect = Rect(
            topLeft = Offset(
                size.width - gap,
                size.height - waveHeight
            ),
            bottomRight = Offset(size.width - waveLength / 4, size.height)
        ),
        startAngleDegrees = 0f,
        sweepAngleDegrees = -180f,
        forceMoveTo = false
    )
    for (i in 1..waveCount) {
        arcTo(
            rect = Rect(
                topLeft = Offset(
                    size.width - gap - waveLength * (i - 1) - waveLength / 2,
                    size.height - waveHeight
                ),
                bottomRight = Offset(
                    size.width - gap - waveLength * (i - 1),
                    size.height
                )
            ),
            startAngleDegrees = 0f,
            sweepAngleDegrees = 180f,
            forceMoveTo = false
        )
        arcTo(
            rect = Rect(
                topLeft = Offset(
                    size.width - gap - waveLength * i,
                    size.height - waveHeight
                ),
                bottomRight = Offset(
                    size.width - gap - waveLength * (i - 1) - waveLength / 2,
                    size.height
                )
            ),
            startAngleDegrees = 0f,
            sweepAngleDegrees = -180f,
            forceMoveTo = false
        )
    }
    arcTo(
        rect = Rect(
            topLeft = Offset(-waveLength / 4, size.height - waveHeight),
            bottomRight = Offset(waveLength / 4, size.height)
        ),
        startAngleDegrees = 0f,
        sweepAngleDegrees = 90f,
        forceMoveTo = false
    )
    lineTo(0f, 0f)
    close()
}

fun Path.drawLinearBottomZigZag(
    waveCount: Int,
    size: Size
) {
    val waveLength = size.width / (waveCount)
    val waveHeight = waveLength / 3

    var currentX: Float = 0f

    reset()
    moveTo(0f, 0f)
    lineTo(size.width, 0f)
    lineTo(size.width, size.height)
    lineTo(
        size.width - (waveLength / 2), size.height - waveHeight
    )
    currentX = size.width - (waveLength / 2)
    for (i in 1..waveCount) {
        currentX -= (waveLength / 2)
        lineTo(currentX, size.height)
        currentX -= (waveLength / 2)
        lineTo(currentX, size.height - waveHeight)
    }
    lineTo(currentX, size.height)
    lineTo(0f, 0f)
    close()
}

fun Path.drawSquareBottomZigZag(
    waveCount: Int,
    size: Size
) {
    val waveLength = size.width / waveCount
    val waveHeight = waveLength / 3

    var currentX: Float = 0f

    reset()
    moveTo(0f, 0f)
    lineTo(size.width, 0f)
    lineTo(size.width, size.height)
    currentX = size.width
    currentX -= (waveLength / 3)
    lineTo(
        currentX, size.height
    ) // initial
    for (i in 1..waveCount) {
        lineTo(currentX, size.height - waveHeight) // 1
        currentX -= (waveLength / 2)
        lineTo(currentX, size.height - waveHeight) // 2
        lineTo(currentX, size.height) // 3
        currentX -= (waveLength / 2)
        lineTo(currentX, size.height) // 4
    }
    lineTo(0f, 0f)
    close()
}

fun Path.drawCircularBottomZigZagAnimation(
    waveCount: Int,
    size: Size,
    waveLength: Float,
    dx: Float
) {
    val gap = 3 * waveLength / 4

    reset()
    moveTo(0f, 0f)
    lineTo(size.width, 0f)
    lineTo(size.width, size.height)
    arcTo(
        rect = Rect(
            topLeft = Offset(
                size.width - gap,
                size.height - dx
            ),
            bottomRight = Offset(size.width - waveLength / 4, size.height)
        ),
        startAngleDegrees = 0f,
        sweepAngleDegrees = -180f,
        forceMoveTo = false
    )
    for (i in 1..waveCount) {
        if (i % 2 == 1) {
            arcTo(
                rect = Rect(
                    topLeft = Offset(
                        size.width - gap - waveLength * (i - 1) - waveLength / 2,
                        size.height - dx
                    ),
                    bottomRight = Offset(
                        size.width - gap - waveLength * (i - 1),
                        size.height
                    )
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
            arcTo(
                rect = Rect(
                    topLeft = Offset(
                        size.width - gap - waveLength * i,
                        size.height - dx
                    ),
                    bottomRight = Offset(
                        size.width - gap - waveLength * (i - 1) - waveLength / 2,
                        size.height
                    )
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = -180f,
                forceMoveTo = false
            )
        } else {
            arcTo(
                rect = Rect(
                    topLeft = Offset(
                        size.width - gap - waveLength * (i - 1) - waveLength / 2,
                        size.height - dx
                    ),
                    bottomRight = Offset(
                        size.width - gap - waveLength * (i - 1),
                        size.height
                    )
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
            arcTo(
                rect = Rect(
                    topLeft = Offset(
                        size.width - gap - waveLength * i,
                        size.height - dx
                    ),
                    bottomRight = Offset(
                        size.width - gap - waveLength * (i - 1) - waveLength / 2,
                        size.height
                    )
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = -180f,
                forceMoveTo = false
            )
        }
    }
    arcTo(
        rect = Rect(
            topLeft = Offset(-waveLength / 4, size.height - dx),
            bottomRight = Offset(waveLength / 4, size.height)
        ),
        startAngleDegrees = 0f,
        sweepAngleDegrees = 90f,
        forceMoveTo = false
    )
    lineTo(0f, 0f)
    close()
}
