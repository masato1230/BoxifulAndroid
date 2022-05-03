package com.jp_funda.boxiful.di

import android.content.Context
import com.jp_funda.boxiful.AppUtils
import com.jp_funda.boxiful.data.shared_preference.AuthPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideAppUtils() = AppUtils()

    @Provides
    @Singleton
    fun provideAuthPreferences(@ApplicationContext appContext: Context) =
        AuthPreferences(appContext)
}