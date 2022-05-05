package com.jp_funda.boxiful.views.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

@Composable
fun PopupWindowDialog(
    isShowDialog: MutableState<Boolean>,
    offset: IntOffset = IntOffset(30, 30),
    content: @Composable () -> Unit,
) {
    if (isShowDialog.value) {
        Box {
            Popup(
                alignment = Alignment.TopStart,
                properties = PopupProperties(),
                offset = offset,
                onDismissRequest = { isShowDialog.value = false }
            ) {
                content()
            }
        }
    }
}