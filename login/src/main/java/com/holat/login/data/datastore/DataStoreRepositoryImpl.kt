package com.holat.login.data.datastore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.holat.login.models.nafath.responsenafathstatus.Client
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val PREFERENCES_NAME = "preferences"
private const val KEY_MODE = "KEY_MODE"
private const val LOCALE_KEY = "LANGUAGE"
private const val USER_TOKEN_KEY = "user_token"
private const val USER_NAME = "USER_NAME"
private const val CLIENT_ID = "CLIENT_ID"
private const val ADDRESS = "ADDRESS"
private const val PHONE = "PHONE"
private const val NATIONALITY_TYPE = "NATIONALITY_TYPE"
private const val GENDER = "GENDER"
private const val DATE_OF_BIRTH = "BIRTH_OF_DATE"
private const val NATIONAL_ID = "NATIONAL_ID"
class DataStoreRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    DataStoreRepository {
    private val preferenceKeyLanguage = stringPreferencesKey(LOCALE_KEY)
    private val preferenceKeyToken = stringPreferencesKey(USER_TOKEN_KEY)
    private val preferenceKeyUserName = stringPreferencesKey(USER_NAME)
    private val preferenceKeyClientId = intPreferencesKey(CLIENT_ID)
    private val preferenceKeyNationalId = stringPreferencesKey(NATIONAL_ID)
    private val preferenceKeyAddress = stringPreferencesKey(ADDRESS)
    private val preferenceKeyPhone = stringPreferencesKey(PHONE)
    private val preferenceKeyNationalityType = intPreferencesKey(NATIONALITY_TYPE)
    private val preferenceKeyGender = stringPreferencesKey(GENDER)
    private val preferenceKeyDateOfBirth = stringPreferencesKey(DATE_OF_BIRTH)
    override suspend fun saveLanguage(language: String) {
        dataStore.edit { preference ->
            preference[preferenceKeyLanguage] = language
        }
    }

    override fun getLanguage(): Flow<String> {
        return dataStore.data.map {
            it[preferenceKeyLanguage] ?: "ar"
        }
    }

    //    override fun getLanguage(): Flow<String> {
//        return dataStore.data.catch { exception->
//            //Datastore.data throw an IOException when an error is encountered when reading dara
//            if (exception is IOException)
//                emit(emptyPreferences())else
//                throw exception
//        }.map {
//            it[preferenceKeyLanguage] ?: "ar"
//        }
//    }


    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[preferenceKeyToken] = token
        }
    }

    override fun getToken(): Flow<String> {
        return dataStore.data.map {
            it[preferenceKeyToken] ?: ""
        }
    }
    override suspend fun deleteToken() {
        dataStore.edit {
            //Delete token from data store
            // it.clear()
            val preferenceKeyToken = preferenceKeyToken
            if (!it[preferenceKeyToken].isNullOrEmpty())
                it.remove(preferenceKeyToken)
        }
    }

    override suspend fun saveUserData(client: Client) {
        dataStore.edit { preferences->
            preferences[preferenceKeyAddress] = client.address ?: ""
            preferences[preferenceKeyGender] = client.gender
            preferences[preferenceKeyDateOfBirth] = client.birthdate
            preferences[preferenceKeyUserName] = client.name
            preferences[preferenceKeyNationalityType] = client.nationalIdTypeId ?: -1
            preferences[preferenceKeyClientId] = client.id
            preferences[preferenceKeyPhone] = client.mobile
            preferences[preferenceKeyNationalId] = client.nationalId
        }
    }

    override fun getUserData(): Flow<Client> {
        return dataStore.data.map {
            Client(id = it[preferenceKeyClientId]!!, name = it[preferenceKeyUserName]!!,
                gender = it[preferenceKeyGender]!!, birthdate = it[preferenceKeyDateOfBirth]!!,
                nationalIdTypeId = it[preferenceKeyNationalityType]!!,
                mobile = it[preferenceKeyPhone]!!,
                address = it[preferenceKeyAddress]!!,
                nationalId = it[preferenceKeyNationalId]!!)
        }
    }

    override suspend fun deleteUserData() {
        dataStore.edit {
            Client(id = it.remove(preferenceKeyClientId), name = it.remove(preferenceKeyUserName),
                gender = it.remove(preferenceKeyGender), birthdate = it.remove(preferenceKeyDateOfBirth),
                nationalIdTypeId = it.remove(preferenceKeyNationalityType),
                mobile = it.remove(preferenceKeyPhone),
                address = it.remove(preferenceKeyAddress),
                nationalId = it.remove(preferenceKeyNationalId))
        }
    }

//    override suspend fun saveUserCredential(userCensTable: UserCensTable) {
//        dataStore.edit { preferences ->
//            preferences[preferenceUserId] = userCensTable.userId ?: 0
//            preferences[preferenceDeviceId] = userCensTable.deviceId
//            preferences[preferenceEmpId] = userCensTable.empId
//            preferences[preferenceWorkAreaId] = userCensTable.workAreaId ?: 0
//            preferences[preferenceTablePassword] = userCensTable.tabletPassWord
//        }
//    }
//
//    override fun getUserCredential(): Flow<UserCensTable> {
//        return dataStore.data
//            .map {
//                UserCensTable(
//                    userId = it[preferenceUserId],
//                    deviceId = it[preferenceDeviceId] ?: "",
//                    empId = it[preferenceEmpId] ?: "",
//                    workAreaId = it[preferenceWorkAreaId],
//                    tabletPassWord = it[preferenceTablePassword] ?: ""
//                )
//            }
//    }

//    override suspend fun deleteUserCredential() {
//        dataStore.edit {
//            if (!it[preferenceEmpId].isNullOrEmpty())
//                UserCensTable(
//                    userId = it.remove(preferenceUserId),
//                    deviceId = it.remove(preferenceDeviceId),
//                    empId = it.remove(preferenceEmpId),
//                    workAreaId = it.remove(preferenceWorkAreaId),
//                    tabletPassWord = it.remove(preferenceTablePassword)
//                )
//        }
//    }

}