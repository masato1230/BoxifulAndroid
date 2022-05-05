package com.jp_funda.boxiful.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.navigation.NavigationRoutes
import com.jp_funda.boxiful.ui.theme.Green500
import com.jp_funda.boxiful.ui.theme.Yellow500

@Composable
fun ErrorView(
    navController: NavController,
    errorMessage: String,
    actionMessage: String? = null,
    onActionButtonClicked: () -> Unit = {},
) {
    Column(modifier = Modifier.padding(30.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_nothing),
            contentDescription = stringResource(id = R.string.desc_icon),
            modifier = Modifier.padding(50.dp),
        )
        Text(
            text = errorMessage,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    navController.popBackStack(
                        NavigationRoutes.HOME,
                        inclusive = false
                    )
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Green500),
                modifier = Modifier.weight(0.5f),
            ) {
                Text(text = stringResource(id = R.string.back_to_home))
            }
            Spacer(modifier = Modifier.width(10.dp))
            actionMessage?.let {
                Button(
                    onClick = { onActionButtonClicked() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Yellow500),
                    modifier = Modifier.weight(0.5f),
                ) {
                    Text(text = it)
                }
            }
        }
    }
}