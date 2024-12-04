package com.holat.login.database

import com.holat.login.models.ServiceConfigDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
Created by Mohamed Mohamed Taha on 12/23/2023
 */
class SelfServiceConfigDatabaseDataSource @Inject constructor(private val serviceConfigDao: ServiceConfigDao):SelfServiceConfigDatabaseRepository {
    override fun getServiceConfig(): Flow<ServiceConfigDatabase> {
        return serviceConfigDao.getServiceConfig()
    }

    override suspend fun insertServiceConfig(serviceConfigDatabase: ServiceConfigDatabase):Long {
       return serviceConfigDao.insertServiceConfig(serviceConfigDatabase)
    }

    override suspend fun deleteData(): Int {
        return serviceConfigDao.deleteData()
    }
}