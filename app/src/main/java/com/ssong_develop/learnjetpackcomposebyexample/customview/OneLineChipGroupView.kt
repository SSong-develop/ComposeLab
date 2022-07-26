package com.ssong_develop.learnjetpackcomposebyexample.customview

import android.content.Context
import android.util.TypedValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.*
import kotlin.math.roundToInt

@Composable
fun OneLineChipGroupView(
    modifier: Modifier = Modifier,
    spacing: Dp,
    callback: (Int, Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    // 한줄을 초과 했는지 대한 Trigger 값
    var isOverOneLine = remember { false }

    val context = LocalContext.current

    var removedViewWidth = remember { 0.dp }

    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->

        // 현재 라인의 정보
        var currentRow = 0
        // 최신 위치 값
        var currentOrigin = IntOffset.Zero
        // Chip 간의 간격
        val spacingValue = spacing.toPx().toInt()
        // View의 갯수
        var totalChipCount = measurables.size
        // 못보여주고 남은 Chip의 갯수를 알려주는 텍스트의 dp 크기
        var remainItemCountViewWidth = 0.dp

        val placeables = measurables.map { measurable ->
            val placeable = measurable.measure(constraints)

            if (currentOrigin.x > 0f && currentOrigin.x + placeable.width > constraints.maxWidth) {
                isOverOneLine = true
                currentRow += 1
                currentOrigin = currentOrigin.copy(x = 0, y = currentOrigin.y + placeable.height + spacingValue)
            }

            placeable to currentOrigin.also {
                currentOrigin = it.copy(x = it.x + placeable.width + spacingValue)
            }
        }

        // 한줄에 놓여질 viewList
        val oneLinePlaceable = placeables.filter { it.second.y == IntOffset.Zero.y }.toMutableList()

        // 남은 아이템의 갯수를 알려줄 view의 width
        remainItemCountViewWidth = context.pxToDp(context.spToPx(12.sp * "+${totalChipCount - oneLinePlaceable.size}".length)).dp

        if (isOverOneLine) {
            oneLinePlaceable.run {
                while (removedViewWidth.value <= remainItemCountViewWidth.value) {
                    removedViewWidth += oneLinePlaceable.last().first.width.toDp()
                    oneLinePlaceable.removeLast()
                }
            }
        }

        callback(totalChipCount - oneLinePlaceable.size, isOverOneLine)

        // 그리기
        layout(
            width = oneLinePlaceable.lastOrNull()?.run { second.x + first.width } ?: 0,
            height = oneLinePlaceable.lastOrNull()?.run { first.height + second.y } ?: 0
        ) {
            oneLinePlaceable.forEach { (placeable, origin) ->
                placeable.place(origin.x, origin.y)
            }
        }
    }
}

fun String.toDp(): Dp {
    return Dp(this.toFloat())
}

fun Context.spToPx(sp : TextUnit): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp.value,this.resources.displayMetrics).roundToInt()

fun Context.pxToDp(px: Int): Float {
    var density = resources.displayMetrics.density

    if (density == 1f) // mpdi (160dpi) -- xxxhdpi (density = 4)기준으로 density 값을 재설정 한다
        density *= 4f
    else if (density == 1.5f) // hdpi (240dpi)
        density *= (8 / 3)
    else if (density == 2.0f) // xhdpi (320dpi)
        density *= 2.0f

    return px / density; // dp 값 반환
}