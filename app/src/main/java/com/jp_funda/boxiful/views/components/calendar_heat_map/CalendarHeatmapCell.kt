package com.jp_funda.boxiful.views.components.calendar_heat_map

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.ui.theme.Green500
import com.jp_funda.boxiful.views.components.PopupWindowDialog
import kotlin.math.roundToInt

@Composable
fun CalendarHeatmapCell(
    cellSize: DpSize,
    cellPadding: Dp,
    roundSize: Dp,
    level: CalendarHeatmapLevel?,
    popupContent: @Composable () -> Unit = {},
) {
    val isShowDialog = remember { mutableStateOf(false) }

    with(LocalDensity.current) {
        Box(
            modifier = Modifier
                .padding(cellPadding)
                .size(cellSize)
                .clip(RoundedCornerShape(roundSize))
                .background(level?.backgroundAlpha?.let { Green500.copy(alpha = it) }
                    ?: Color.DarkGray.copy(alpha = 0.3f))
                .border(
                    width = 1.dp,
                    color = level?.backgroundAlpha?.let {
                        Green500.copy(alpha = minOf(1f, it + 0.3f))
                    } ?: Color.Transparent,
                    shape = RoundedCornerShape(roundSize)
                )
                .clickable { isShowDialog.value = true }
        ) {
            PopupWindowDialog(
                isShowDialog = isShowDialog,
                offset = IntOffset(
                    cellSize.width.toPx().roundToInt(),
                    cellSize.height.toPx().roundToInt(),
                )
            ) {
                popupContent()
            }
        }
    }
}