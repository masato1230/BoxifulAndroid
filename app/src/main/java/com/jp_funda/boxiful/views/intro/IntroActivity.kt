package com.jp_funda.boxiful.views.intro

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jp_funda.boxiful.ui.theme.BoxifulTheme

class IntroActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContent {
            BoxifulTheme {

            }
        }
    }


}