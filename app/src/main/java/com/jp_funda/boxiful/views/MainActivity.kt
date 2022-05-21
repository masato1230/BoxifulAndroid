package com.jp_funda.boxiful.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.jp_funda.boxiful.BuildConfig
import com.jp_funda.boxiful.ui.theme.BoxifulTheme
import com.jp_funda.boxiful.views.intro.IntroActivity
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@ExperimentalAnimationApi
@ExperimentalPermissionsApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    var interstitialAd: InterstitialAd? = null
    private lateinit var adRequest: AdRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        // AdMob initialization
        initializeInterstitialAd()

        // Refresh auth tokens
        viewModel.refreshAuthTokens()

        // Show intro when first time app launch
        if (viewModel.isFirstAppLaunch) {
            viewModel.markAsFirstAppLaunchFinished()
            startActivity(Intent(this, IntroActivity::class.java))
        }

        setContent {
            BoxifulTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    LaunchedEffect(key1 = "") {
                        showInterstitial()
                    }
                    MainScreen(viewModel)
                }
            }
        }
    }

    private fun initializeInterstitialAd() {
        // Load Interstitial Ad
        // Initialize Mobile Ads SDK
        MobileAds.initialize(this)
        // Specify test device
        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
                .setTestDeviceIds(listOf("2680A52322369B561BAF8E9FAEC62987"))
                .build()
        )
        adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this, BuildConfig.interstitialUnitId, adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    interstitialAd = null
                }

                override fun onAdLoaded(loadedAd: InterstitialAd) {
                    interstitialAd = loadedAd
                    interstitialAd?.show(this@MainActivity)
                }
            })
    }

    fun showInterstitial() {
        if (interstitialAd != null) {
            interstitialAd?.show(this)
        }
    }
}