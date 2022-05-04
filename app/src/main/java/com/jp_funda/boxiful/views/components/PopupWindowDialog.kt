package com.jp_funda.boxiful.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.jp_funda.boxiful.ui.theme.Yellow500

@Preview
@Composable
fun PopupWindowDialog() {
    val isShowDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = {
            isShowDialog.value = !isShowDialog.value
        }) {
            Text(text = "Show popup")
        }
    }

    Box {
        val popupWidth = 120.dp
        val popupHeight = 100.dp
        val cornerSize = 10.dp

        if (isShowDialog.value) {
            Popup(
                alignment = Alignment.TopStart,
                properties = PopupProperties()
            ) {
                Box(
                    modifier = Modifier
                        .size(popupWidth, popupHeight)
                        .padding(top = 5.dp)
                        .background(Yellow500, RoundedCornerShape(cornerSize))
                        .border(1.dp, Color.Black, RoundedCornerShape(cornerSize))
                ) {
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