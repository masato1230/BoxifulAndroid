package com.jp_funda.boxful.views.components

import android.Manifest
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
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.jp_funda.boxful.R
import com.jp_funda.boxful.ui.theme.Yellow500

@ExperimentalPermissionsApi
@Composable
fun RequestCameraPermission(
    navController: NavController,
    content: @Composable () -> Unit = { }
) {
    val permissionState = rememberPermissionState(Manifest.permission.CAMERA)
    if (permissionState.status.isGranted) {
        content()
    } else {
        val isShowDialog = remember { mutableStateOf(true) }
        if (isShowDialog.value) {
            AlertDialog(
                onDismissRequest = { isShowDialog.value = false },
                title = {
                    Text(text = stringResource(id = R.string.permission_camera_title))
                },
                text = {
                    Column {
                        Text(text = stringResource(id = R.string.permission_camera_rationale))
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
                            onClick = {
                                isShowDialog.value = false
                                permissionState.launchPermissionRequest()
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Yellow500)
                        ) {
                            Text(text = stringResource(id = R.string.ok))
                        }
                    }
                }
            )
        } else {
            navController.popBackStack()
        }
    }
}