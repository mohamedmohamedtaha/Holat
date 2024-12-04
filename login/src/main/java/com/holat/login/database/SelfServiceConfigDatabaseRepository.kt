package com.holat.login.database

import com.holat.login.models.ServiceConfigDatabase
import kotlinx.coroutines.flow.Flow

/**
Created by Mohamed Mohamed Taha on 12/23/2023
 */
interface SelfServiceConfigDatabaseRepository {
     fun getServiceConfig(): Flow<ServiceConfigDatabase>
     suspend fun insertServiceConfig(serviceConfigDatabase: ServiceConfigDatabase):Long
     suspend fun deleteData():Int
}