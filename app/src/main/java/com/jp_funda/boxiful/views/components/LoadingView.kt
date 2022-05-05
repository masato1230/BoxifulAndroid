package com.jp_funda.boxiful.views.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.ui.theme.Green500

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(150.dp)
                .height(150.dp),
            color = Green500,
            strokeWidth = 10.dp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.h5,
            color = Color.White
        )
    }
}