package com.holat.holat.ui.profile.viewmodel

import androidx.lifecycle.viewModelScope
import com.holat.holat.data.models.logout.ResponseLogout
import com.holat.holat.data.network.MainRepository
import com.holat.login.base.BaseViewModel
import com.holat.login.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
Created by Mohamed Mohamed Taha on 1/28/2024
 */
@HiltViewModel
class LogoutViewModel @Inject constructor(private val loginRepository: MainRepository) :
    BaseViewModel() {

    private val _logout = Channel<NetworkResult<ResponseLogout>>()
    val logout get() = _logout.receiveAsFlow()
    fun logout() {
        viewModelScope.launch {
            _logout.send(NetworkResult.Loading())
            loginRepository.logout().collect {
                _logout.send(it)
            }
        }
    }
}