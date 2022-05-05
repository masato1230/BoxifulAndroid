package com.jp_funda.boxiful.extensions

import com.jp_funda.boxiful.utils.date.DateProgression
import java.time.LocalDate

fun LocalDate.rangeTo(endInclusive: LocalDate) = DateProgression(this, endInclusive)