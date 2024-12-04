package com.holat.login.hilt

import com.holat.login.network.ApiServices
import com.holat.login.network.OnlineApis
import com.holat.login.network.repository.LoginRepository
import com.holat.login.network.repository.SelfServiceConfigOnlineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
Created by Mohamed Mohamed Taha on 11/12/2023
 */
@Module
@InstallIn(SingletonComponent::class)
class OnlineModule {
    @Provides
    @Singleton
    fun provideLoginRepository(apiServices: ApiServices): LoginRepository = OnlineApis((apiServices))
    @Provides
    @Singleton
    fun provideSelfServiceConfigRepository(apiServices: ApiServices) : SelfServiceConfigOnlineRepository = OnlineApis(apiServices)
}