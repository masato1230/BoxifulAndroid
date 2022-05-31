package com.jp_funda.boxiful.models

data class SingleMenuResult(
    val singleMenu: SingleMenu,
    val scores: List<Int>,
    val instructions: List<Instruction>,
)