package com.jp_funda.boxiful.views.settings

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jp_funda.boxiful.BuildConfig
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.navigation.NavigationRoutes
import com.jp_funda.boxiful.ui.theme.PagesBackground
import com.jp_funda.boxiful.views.components.header.Header
import com.jp_funda.boxiful.views.settings.component.CopyrightSection
import com.jp_funda.boxiful.views.settings.component.SettingRowOnlyText
import com.jp_funda.boxiful.views.settings.component.SettingRowWithNext
import com.jp_funda.boxiful.views.settings.component.SettingsGroup

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(topBar = { Header(navController) }) {
        PagesBackground()
        SettingsMainContent(navController = navController)
    }
}

@Composable
fun SettingsMainContent(navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        // About boxiful
        SettingsGroup(title = stringResource(id = R.string.settings_about_boxiful)) {
            // App Version
            SettingRowOnlyText(
                icon = Icons.Default.Build,
                title = stringResource(id = R.string.settings_app_version),
                value = BuildConfig.VERSION_NAME,
            )
            Divider(color = Color.LightGray)

            // Privacy & Policy
            SettingRowWithNext(
                icon = Icons.Default.Person,
                title = stringResource(id = R.string.settings_privacy_policy)
            ) {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://boxiful.jp/privacy"),
                )
                context.startActivity(intent)
            }
            Divider(color = Color.LightGray)

            // OSS licences
            SettingRowWithNext(
                icon = Icons.Default.List,
                title = stringResource(id = R.string.settings_oss_licences),
            ) {
                navController.navigate(NavigationRoutes.OSS_LICENSES)
            }
        }

        // Contact form
        SettingsGroup(title = stringResource(id = R.string.settings_contact)) {
            SettingRowWithNext(
                icon = Icons.Default.Email,
                title = stringResource(id = R.string.settings_contact_form),
            ) {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://boxiful.jp/contact_form"),
                )
                context.startActivity(intent)
            }
        }

        CopyrightSection()
    }
}