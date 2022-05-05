package com.jp_funda.boxiful.views.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

@Composable
fun PopupWindowDialog(
    isShowDialog: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    offset: IntOffset = IntOffset(30, 30),
) {
    if (isShowDialog.value) {
        Box {
            Popup(
                alignment = Alignment.TopStart,
                properties = PopupProperties(),
                offset = offset,
                onDismissRequest = { isShowDialog.value = false }
            ) {
                Box(modifier = modifier) {
                    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Text(
                            text = "Edit",
                            modifier = Modifier.padding(vertical = 5.dp),
                            fontSize = 16.sp,
                        )
                        Divider()
                        Text(text = "Settings")
                    }
                }
            }
        }
    }
}