package com.holat.login.network.repository

import com.holat.login.models.ServiceConfig
import com.holat.login.network.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
Created by Mohamed Mohamed Taha on 12/9/2023
 */
interface SelfServiceConfigOnlineRepository {
    suspend fun getSelfServiceConfigsOnline(): Flow<NetworkResult<ServiceConfig>>

}