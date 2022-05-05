package com.jp_funda.boxiful.views.components.calendar_heat_map

import androidx.compose.ui.graphics.Color
import com.jp_funda.boxiful.ui.theme.*

enum class CalendarHeatMapLevel(val color: Color) {
    Level0(Color.Transparent),
    Level1(Green50),
    Level2(Green100),
    Level3(Green200),
    Level4(Green300),
    Level5(Green400),
    Level6(Green500),
    Level7(Green600),
    Level8(Green700),
    Level9(Green800),
    Level10(Green900);
}