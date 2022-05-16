package com.jp_funda.boxiful.views.intro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.jp_funda.boxiful.ui.theme.Background
import com.jp_funda.boxiful.ui.theme.BoxifulTheme

class IntroActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BoxifulTheme {
                Background()
                IntroMainContent()
            }
        }
    }

    @Composable
    fun IntroMainContent() {
        Text(text = "小日は", color = Color.White)
    }
}