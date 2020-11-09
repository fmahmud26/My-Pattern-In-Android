package com.example.my_pattern_in_android.di

import android.content.Context
import android.content.SharedPreferences
import com.example.my_pattern_in_android.BuildConfig
import com.example.my_pattern_in_android.network.MyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()


    @Provides
    @Singleton
    fun provideMyApi(retrofit: Retrofit): MyApi = retrofit.create(MyApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
            .callTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS).build()


    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context) : SharedPreferences = context.getSharedPreferences("app_preference", Context.MODE_PRIVATE)
}