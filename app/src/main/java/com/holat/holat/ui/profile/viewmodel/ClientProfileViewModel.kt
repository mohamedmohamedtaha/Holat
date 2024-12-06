package com.holat.holat.ui.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holat.holat.data.network.MainRepository
import com.holat.login.models.cities.Cities
import com.holat.login.models.clientprofile.ClientProfile
import com.holat.login.models.countries.Countries
import com.holat.login.models.createaccount.Response
import com.holat.login.models.regions.Regions
import com.holat.login.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
Created by Mohamed Mohamed Taha on 8/13/2024
 */
@HiltViewModel
class ClientProfileViewModel @Inject constructor(private val loginRepository: MainRepository) :
    ViewModel() {

    private val _clientProfile = Channel<NetworkResult<ClientProfile>>()
    val clientProfile get() = _clientProfile.receiveAsFlow()
    fun clientProfile() {
        viewModelScope.launch {
            _clientProfile.send(NetworkResult.Loading())
            loginRepository.clientProfile().collect {
                _clientProfile.send(it)
            }
        }
    }

    private val _updateClientProfile = Channel<NetworkResult<Response>>()
    val updateClientProfile get() = _updateClientProfile.receiveAsFlow()
    fun updateClientProfile(
        name: String, nationalId: Long,
        countryId: String,
        regionId: String,
        cityId: String,
        idEndDate: String,
        mobile: String,
        email: String,
        birthId: String
    ) {
        viewModelScope.launch {
            _updateClientProfile.send(NetworkResult.Loading())
            loginRepository.updateClientProfile(
                name,
                nationalId,
                countryId,
                regionId,
                cityId,
                idEndDate,
                mobile,
                email,
                birthId
            ).collect {
                _updateClientProfile.send(it)
            }
        }
    }


    private val _getAllCountries = Channel<NetworkResult<Countries>>()
    val getAllCountries get() = _getAllCountries.receiveAsFlow()
    fun getAllCountries() {
        viewModelScope.launch {
            _getAllCountries.send(NetworkResult.Loading())
            loginRepository.getAllCountries().collect {
                _getAllCountries.send(it)
            }
        }
    }

    private val _getAllRegions = Channel<NetworkResult<Regions>>()
    val getAllRegions get() = _getAllRegions.receiveAsFlow()
    fun getAllRegions(countryId: Int) {
        viewModelScope.launch {
            _getAllRegions.send(NetworkResult.Loading())
            loginRepository.getAllRegions(countryId).collect {
                _getAllRegions.send(it)
            }
        }
    }

    private val _getAllCities = Channel<NetworkResult<Cities>>()
    val getAllCities get() = _getAllCities.receiveAsFlow()
    fun getAllCities(countryId: Int, regionId: Int) {
        viewModelScope.launch {
            _getAllCities.send(NetworkResult.Loading())
            loginRepository.getAllCities(countryId, regionId).collect {
                _getAllCities.send(it)
            }
        }
    }
}