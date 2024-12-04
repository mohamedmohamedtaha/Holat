package com.holat.login.hilt

import android.content.Context
import androidx.room.Room
import com.holat.login.database.LocalDatabase
import com.holat.login.database.SelfServiceConfigDatabaseDataSource
import com.holat.login.database.SelfServiceConfigDatabaseRepository
import com.holat.login.database.ServiceConfigDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
Created by Mohamed Mohamed Taha on 11/13/2023
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideServiceConfigDao(localDatabase: LocalDatabase): ServiceConfigDao {
        return localDatabase.getServiceConfig()
    }


    @Provides
    fun provideServiceConfigDataSource(serviceConfigDao: ServiceConfigDao): SelfServiceConfigDatabaseRepository =
        SelfServiceConfigDatabaseDataSource(serviceConfigDao)

    @Provides
    @Singleton
    fun provideDatabaseRoom(
        @ApplicationContext context: Context
    ): LocalDatabase {
        return Room.databaseBuilder(
            context = context,
            LocalDatabase::class.java,
            "localDatabase"
        ).fallbackToDestructiveMigration().build()
    }
}