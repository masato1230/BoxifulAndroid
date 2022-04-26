package com.jp_funda.boxful.views.components

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
import com.jp_funda.boxful.R
import com.jp_funda.boxful.ui.theme.Green500
import com.jp_funda.boxful.ui.theme.Yellow500

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
                Text(text = stringResource(id = R.string.training_start_title))
            },
            text = {
                Column {
                    Text(text = stringResource(id = R.string.training_start_message))
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        modifier = Modifier.width(120.dp),
                        onClick = { isShowDialog.value = false },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Green500)
                    ) {
                        Text(text = stringResource(id = R.string.cancel))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        modifier = Modifier.width(120.dp),
                        onClick = {
                            isShowDialog.value = false
                            onClickOk()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Yellow500)
                    ) {
                        Text(text = stringResource(id = R.string.start))
                    }
                }
            }
        )
    }
}