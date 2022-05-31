package com.jp_funda.boxiful.di

import android.content.Context
import com.jp_funda.boxiful.AppUtils
import com.jp_funda.boxiful.data.network.AuthService
import com.jp_funda.boxiful.data.network.TrainingResultService
import com.jp_funda.boxiful.data.repository.auth.AuthRepository
import com.jp_funda.boxiful.data.repository.training_result.TrainingResultRepository
import com.jp_funda.boxiful.data.shared_preference.AuthPreferences
import com.jp_funda.boxiful.data.shared_preference.SettingsPreferences
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideAppUtils(authPreferences: AuthPreferences) =
        AppUtils(authPreferences = authPreferences)

    @Provides
    @Singleton
    fun provideAuthPreferences(@ApplicationContext appContext: Context) =
        AuthPreferences(appContext)

    @Provides
    @Singleton
    fun provideSettingsPreferences(@ApplicationContext appContext: Context) =
        SettingsPreferences(appContext)

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit {
        val interpolator = HttpLoggingInterceptor()
        interpolator.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interpolator)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://masato.pythonanywhere.com")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideAuthRepository(authService: AuthService): AuthRepository =
        AuthRepository(authService = authService)

    @Provides
    @Singleton
    fun provideTrainingResultService(retrofit: Retrofit): TrainingResultService =
        retrofit.create(TrainingResultService::class.java)

    @Provides
    @Singleton
    fun provideTrainingResultRepository(trainingResultService: TrainingResultService): TrainingResultRepository =
        TrainingResultRepository(trainingResultService)
}