package com.holat.holat.data.hilt

import com.holat.holat.data.network.ApiMainServices
import com.holat.holat.data.network.MainRepository
import com.holat.holat.data.network.OnlineMainDataSource
import com.holat.login.network.ApiHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
Created by Mohamed Mohamed Taha on 11/12/2023
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkMainModule {

    @Provides
    @Singleton
    fun provideMainApis(
        retrofit: Retrofit): ApiMainServices = retrofit.create(ApiMainServices::class.java)

    @Provides
    @Singleton
    fun provideOnlineMainApiDataSource(onlineMainDataSource: OnlineMainDataSource): MainRepository =
        onlineMainDataSource

    @Provides
    @Singleton
    fun provideApiHandlerDataSource(onlineMainDataSource: OnlineMainDataSource): ApiHandler =
        onlineMainDataSource

}