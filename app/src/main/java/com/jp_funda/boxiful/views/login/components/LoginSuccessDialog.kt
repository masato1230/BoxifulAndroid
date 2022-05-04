package com.jp_funda.boxiful.views.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Yellow500

@Composable
fun LoginSuccessDialog(onDismiss: () -> Unit) {
    val isShowDialog = remember { mutableStateOf(true) }
    AlertDialog(
        onDismissRequest = {
            isShowDialog.value = false
            onDismiss()
        },
        title = {
            Text(text = stringResource(id = R.string.auth_success))
        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier.width(120.dp),
                    onClick = {
                        isShowDialog.value = false
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