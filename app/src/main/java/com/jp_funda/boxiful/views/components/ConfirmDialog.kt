package com.jp_funda.boxiful.views.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Green500
import com.jp_funda.boxiful.ui.theme.Yellow500

@Composable
fun ConfirmDialog(
    title: String,
    isShowDialog: MutableState<Boolean>,
    isShowNegativeButton: Boolean = false,
    onClickPositive: () -> Unit = {},
    onClickNegative: () -> Unit = {},
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {
            isShowDialog.value = false
            onDismiss()
        },
        title = {
            Text(text = title)
        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(1f))
                if (isShowNegativeButton) {
                    Button(
                        modifier = Modifier.width(120.dp),
                        onClick = {
                            isShowDialog.value = false
                            onClickNegative()
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Green500)
                    ) {
                        Text(text = stringResource(id = R.string.cancel))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
                Button(
                    modifier = Modifier.width(120.dp),
                    onClick = {
                        isShowDialog.value = false
                        onClickPositive()
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Yellow500)
                ) {
                    Text(text = stringResource(id = R.string.ok))
                }
            }
        }
    )
}