package com.holat.login.data.datastore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.holat.login.models.nafath.responsenafathstatus.Client
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ViewModel() {
    private val _getToken = MutableLiveData<String>()
    val getToken get() = _getToken

    val getLanguage get() = dataStoreRepository.getLanguage().asLiveData()
//    val getLanguaged get() = dataStoreRepository.getLanguage()

    fun saveLanguage(language: String) {
        viewModelScope.launch {
            dataStoreRepository.saveLanguage(language)
        }
    }

    fun getLanguage() = runBlocking { dataStoreRepository.getLanguage().first() }

    fun saveToken(token: String) {
        viewModelScope.launch {
            dataStoreRepository.saveToken(token)
        }
    }

    fun getToken() {
        viewModelScope.launch {
            dataStoreRepository.getToken().collect{token->
                _getToken.value = token
            }
        }
    }

    fun deleteToken() {
        viewModelScope.launch {
            dataStoreRepository.deleteToken()
        }
    }

    fun saveUserData(client: Client) {
        viewModelScope.launch {
            dataStoreRepository.saveUserData(client)
        }
    }
    private val _getUserData = MutableLiveData<Client>()
    val getUserData get() = _getUserData
    fun getUserData() {
        viewModelScope.launch {
            dataStoreRepository.getUserData().collect{token->
                _getUserData.value = token
            }
        }
    }

    fun deleteUserData() {
        viewModelScope.launch {
            dataStoreRepository.deleteUserData()
        }
    }
}
