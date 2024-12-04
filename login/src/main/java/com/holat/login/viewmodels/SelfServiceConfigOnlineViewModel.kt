package com.holat.login.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holat.login.models.ServiceConfig
import com.holat.login.network.NetworkResult
import com.holat.login.network.repository.SelfServiceConfigOnlineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
Created by Mohamed Mohamed Taha on 12/9/2023
 */
@HiltViewModel
class SelfServiceConfigOnlineViewModel @Inject constructor(private val selfServiceConfigOnlineRepository: SelfServiceConfigOnlineRepository):
    ViewModel(){
    private val _getSelfServiceConfigs = Channel<NetworkResult<ServiceConfig>>()
    val getSelf get() = _getSelfServiceConfigs.receiveAsFlow()
    fun getSelfServiceConfigsOnline(){
        viewModelScope.launch {
            selfServiceConfigOnlineRepository.getSelfServiceConfigsOnline().collect{
                _getSelfServiceConfigs.send(it)
            }
        }
    }
}