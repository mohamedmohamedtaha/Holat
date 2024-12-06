package com.holat.holat.ui.home.responses.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.holat.holat.data.network.MainRepository
import com.holat.login.base.BaseViewModel
import com.holat.login.models.responses.ResponsesResponse
import com.holat.login.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
Created by Mohamed Mohamed Taha on 3/1/2024
 */
private const val ITEMS_PER_PAGE = 50

@HiltViewModel
class ResponsesViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {
    private val _getResponses =
        MutableStateFlow<NetworkResult<ResponsesResponse>>(NetworkResult.Loading())
    val getResponses get() = _getResponses.asLiveData()
    fun getResponses(ticketId: Long, page: Int, perPage: Int) {
        viewModelScope.launch {
            //    _oldTickets.send()
            mainRepository.getResponses(ticketId, page, perPage).collect {
                _getResponses.value = it
            }
        }
    }


//    private val _getResponsesPaging : Flow<PagingData<NetworkResult<ResponsesResponse>>> =Pager(
//        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
//        pagingSourceFactory = {loginRepository.getResponses()}
//    ).flow

//    val getResponsesPaging get() = _getResponsesPaging.asLiveData()
//    fun getResponsesPaging(ticketId: Long, page: Int, perPage: Int) {
//        viewModelScope.launch {
//            //    _oldTickets.send()
//            loginRepository.getResponses(ticketId, page, perPage).collect {
//                _getResponsesPaging.value = it
//            }
//        }
//    }
}