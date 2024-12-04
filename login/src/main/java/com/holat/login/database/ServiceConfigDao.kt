package com.holat.login.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.holat.login.models.ServiceConfigDatabase
import kotlinx.coroutines.flow.Flow

/**
Created by Mohamed Mohamed Taha on 11/13/2023
 */
@Dao
interface ServiceConfigDao {
    @Query(" SELECT * FROM service_config order by id asc limit 1 ")
    fun getServiceConfig(): Flow<ServiceConfigDatabase>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServiceConfig(serviceConfigDatabase: ServiceConfigDatabase):Long

    @Query("DELETE FROM service_config")
    suspend fun deleteData():Int
}