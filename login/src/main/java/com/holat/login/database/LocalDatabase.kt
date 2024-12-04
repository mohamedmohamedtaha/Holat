package com.holat.login.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.holat.login.models.ServiceConfigDatabase

/**
Created by Mohamed Mohamed Taha on 11/13/2023
 */
@Database(entities = [ServiceConfigDatabase::class], version = 3, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getServiceConfig(): ServiceConfigDao

}