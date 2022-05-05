package com.jp_funda.boxiful.views.components.calendar_heat_map

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.views.components.PopupWindowDialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun CalendarHeatmapCell(
    date: LocalDate,
    cellSize: DpSize,
    cellPadding: Dp,
    roundSize: Dp,
    level: CalendarHeatMapLevel,
    alpha: Float = 0.5f,
) {
    val isShowDialog = remember { mutableStateOf(false) }

    with(LocalDensity.current) {
        Box(
            modifier = Modifier
                .padding(cellPadding)
                .size(cellSize)
                .clip(RoundedCornerShape(roundSize))
                .background(level.color.copy(alpha = minOf(1f, alpha + 0.3f)))
                .border(width = 1.dp, color = level.color, shape = RoundedCornerShape(roundSize))
                .clickable { isShowDialog.value = true }
        ) {
            PopupWindowDialog(
                isShowDialog = isShowDialog,
                offset = IntOffset(
                    cellSize.width.toPx().roundToInt(),
                    cellSize.height.toPx().roundToInt(),
                )
            ) {
                // TODO content
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .widthIn(max = 200.dp)
                        .background(Color.DarkGray),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    val dateFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = dateFormatter.format(date),
                        color = Color.LightGray,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(horizontal = 20.dp),
                    )
                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp))
                    Text(
                        text = "消費したカロリー 22kcal",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(horizontal = 20.dp),
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Boxifulポイント 22ポイント",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(horizontal = 20.dp),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}