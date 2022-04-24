package com.jp_funda.boxful.views.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CameraOpenDialog(
    isShowDialog: MutableState<Boolean>,
    onClickOk: () -> Unit,
) {
    if (isShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
                isShowDialog.value = false
            },
            title = {
                Text(text = "Title")
            },
            text = {
                Column() {
                    Text("Custom Text")
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { isShowDialog.value = false }
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        )
    }
}