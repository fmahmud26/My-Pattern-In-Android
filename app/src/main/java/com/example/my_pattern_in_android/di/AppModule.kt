package com.example.my_pattern_in_android.di

import com.example.my_pattern_in_android.BuildConfig
import com.example.my_pattern_in_android.network.MyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()


    @Provides
    @Singleton
    fun provideMyApi(retrofit: Retrofit): MyApi = retrofit.create(MyApi::class.java)

}