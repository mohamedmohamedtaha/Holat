package com.holat.login.data.datastore
import com.holat.login.models.nafath.responsenafathstatus.Client
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveLanguage(language: String)
    fun getLanguage(): Flow<String>

    suspend fun saveToken(token: String)
    fun getToken(): Flow<String>

    suspend fun deleteToken()
    suspend fun saveUserData(client: Client)
    fun getUserData():Flow<Client>
    suspend fun deleteUserData()

}