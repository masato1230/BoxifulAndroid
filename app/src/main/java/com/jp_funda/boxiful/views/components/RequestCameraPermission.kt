package com.jp_funda.boxiful.views.components

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Green500
import com.jp_funda.boxiful.ui.theme.Yellow500

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
                onDismissRequest = {
                    isShowDialog.value = false
                    navController.popBackStack()
                },
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
        } else { // On permission denied & while permission dialog showing
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.permission_request_camera_permission),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { navController.popBackStack() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Green500),
                        modifier = Modifier.weight(0.5f),
                    ) {
                        Text(text = stringResource(id = R.string.permission_back_to_home))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = { permissionState.launchPermissionRequest() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Yellow500),
                        modifier = Modifier.weight(0.5f),
                    ) {
                        Text(text = stringResource(id = R.string.permission_allow))
                    }
                }
            }
        }
    }
}