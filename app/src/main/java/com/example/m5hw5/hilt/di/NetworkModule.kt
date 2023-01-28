package com.example.m5hw5.hilt.di

import com.example.m5hw5.hilt.data.remote.RetrofitClient
import com.example.m5hw5.hilt.data.remote.api.PhotoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofitClient = RetrofitClient()

    @Provides
    @Singleton
    fun providePostApiService(): PhotoApiService {
        return retrofitClient.photoApiService
    }
}