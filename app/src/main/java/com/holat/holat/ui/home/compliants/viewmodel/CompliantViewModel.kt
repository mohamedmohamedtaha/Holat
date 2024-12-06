package com.holat.holat.ui.home.compliants.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.holat.holat.data.models.compliant.mainreasons.MainReasons
import com.holat.holat.data.models.compliant.tickettype.TicketTypeResponse
import com.holat.holat.data.models.fields.FieldResponse
import com.holat.holat.data.models.organization.OrganizationResponse
import com.holat.holat.data.models.storeticket.StoreTicketResponse
import com.holat.holat.data.models.tickets.DataTicket
import com.holat.holat.data.models.tickets.Tickets
import com.holat.holat.data.network.MainRepository
import com.holat.login.base.BaseViewModel
import com.holat.login.models.UploadImageOrFile
import com.holat.login.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import javax.inject.Inject

/**
Created by Mohamed Mohamed Taha on 1/30/2024
 */
@HiltViewModel
class CompliantViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {
    var notCallSpinnerLotOfTime = true

    private val _oldTickets = MutableStateFlow<NetworkResult<Tickets>>(NetworkResult.Loading())
    val oldTickets get() = _oldTickets.asLiveData()
    fun oldTickets(perPage: Int) {
        viewModelScope.launch {
            //    _oldTickets.send()
            mainRepository.oldTickets(perPage).collect {
                _oldTickets.value = it
            }
        }
    }

    private val _storeTicket =
        MutableStateFlow<NetworkResult<StoreTicketResponse>>(NetworkResult.Loading())
    val storeTicket get() = _storeTicket
    fun storeTicket(requestBody: RequestBody) {
        viewModelScope.launch {
            mainRepository.storeTicket(requestBody).collect {
                _storeTicket.value = it
            }
        }
    }

    private val _ticketSearch = Channel<NetworkResult<DataTicket>>()
    val ticketSearch get() = _ticketSearch.receiveAsFlow()
    fun ticketSearch(ticketId: String) {
        viewModelScope.launch {
            _ticketSearch.send(NetworkResult.Loading())
            mainRepository.ticketSearch(ticketId).collect {
                _ticketSearch.send(it)
            }
        }
    }

    val dataTicket = ArrayList<UploadImageOrFile>()
    private val _getFields = Channel<NetworkResult<FieldResponse>>()
    val getFields get() = _getFields.receiveAsFlow()

    //Job for protect from concurrent callers
    private var job: Job? = null
    fun getFields() {
        if (job != null) return
        job = viewModelScope.launch {
            try {
                _getFields.send(NetworkResult.Loading())
                mainRepository.getFields().collect {
                    _getFields.send(it)
                }
            } catch (e: Exception) {
            } finally {
                job = null
            }
        }
    }

    private val _allTicketType = Channel<NetworkResult<TicketTypeResponse>>()
    val allTicketType get() = _allTicketType.receiveAsFlow()

    //Job for protect from concurrent callers
    private var jobTicket: Job? = null
    fun allTicketType(active: Int) {
        if (jobTicket != null) return
        jobTicket = viewModelScope.launch {
            try {
                _allTicketType.send(NetworkResult.Loading())
                mainRepository.allTicketType(active).collect {
                    _allTicketType.send(it)
                }
            } catch (e: Exception) {
            } finally {
                jobTicket = null
            }
        }
    }

    private val _mainReasons = Channel<NetworkResult<ArrayList<MainReasons>>>()
    val mainReasons get() = _mainReasons.receiveAsFlow()

    //Job for protect from concurrent callers
    private var jobmainReasons: Job? = null
    fun mainReasons(active: Int, ticketTypeId: Int) {
        if (jobmainReasons != null) return
        jobmainReasons = viewModelScope.launch {
            try {
                _mainReasons.send(NetworkResult.Loading())
                mainRepository.mainReasons(active, ticketTypeId).collect {
                    _mainReasons.send(it)
                }
            } catch (e: Exception) {
            } finally {
                jobmainReasons = null
            }
        }
    }

    private val _subReasons =
        MutableStateFlow<NetworkResult<ArrayList<MainReasons>>>(NetworkResult.Loading())
    val subReasons get() = _subReasons.asLiveData()

    //Job for protect from concurrent callers
    private var jobSubReasons: Job? = null
    fun subReasons(active: Int, mainReasonId: Int) {
        if (jobSubReasons != null) return
        jobSubReasons = viewModelScope.launch {
            try {
                mainRepository.subReasons(active, mainReasonId).collect {
                    _subReasons.value = it
                }
            } catch (e: Exception) {
            } finally {
                jobSubReasons = null
            }
        }
    }

    private val _getAllOrganization =
        MutableStateFlow<NetworkResult<OrganizationResponse>>(NetworkResult.Loading())
    val getAllOrganization get() = _getAllOrganization.asLiveData()

    //Job for protect from concurrent callers
    private var jobGetAllOrganization: Job? = null
    fun getAllOrganization(mainReasonId: Int) {
        if (jobGetAllOrganization != null) return
        jobGetAllOrganization = viewModelScope.launch {
            try {
                mainRepository.getAllOrganization(mainReasonId).collect {
                    _getAllOrganization.value = it
                }
            } catch (e: Exception) {
            } finally {
                jobGetAllOrganization = null
            }
        }
    }

    private val _subSubReasons =
        MutableStateFlow<NetworkResult<ArrayList<MainReasons>>>(NetworkResult.Loading())
    val subSubReasons get() = _subSubReasons.asLiveData()

    //Job for protect from concurrent callers
    private var jobSubSubReasons: Job? = null
    fun subSubReasons(active: Int, subReasonId: Int) {
        if (jobSubSubReasons != null) return
        jobSubSubReasons = viewModelScope.launch {
            try {
                mainRepository.subSubReasons(active, subReasonId).collect {
                    _subSubReasons.value = it
                }
            } catch (e: Exception) {
            } finally {
                jobSubSubReasons = null
            }
        }
    }

    private val _subSubSubReasons =
        MutableStateFlow<NetworkResult<ArrayList<MainReasons>>>(NetworkResult.Loading())
    val subSubSubReasons get() = _subSubSubReasons.asLiveData()

    //Job for protect from concurrent callers
    private var jobSubSubSubReasons: Job? = null
    fun subSubSubReasons(active: Int, subSubReasonId: Int) {
        if (jobSubSubSubReasons != null) return
        jobSubSubSubReasons = viewModelScope.launch {
            try {
                mainRepository.subSubSubReasons(active, subSubReasonId).collect {
                    _subSubSubReasons.value = it
                }
            } catch (e: Exception) {
            } finally {
                jobSubSubSubReasons = null
            }
        }
    }
    private val _getDropDownList =
        Channel<NetworkResult<ArrayList<MainReasons>>>()
    val getDropDownList get() = _getDropDownList.receiveAsFlow()

    //Job for protect from concurrent callers
    private var jobGetDropDownList: Job? = null
    fun getDropDownList(endPoint: String, active: Int, queryParams: Map<String, String>) {
        if (jobGetDropDownList != null) return
        jobGetDropDownList = viewModelScope.launch {
            try {
                _getDropDownList.send(NetworkResult.Loading())
                mainRepository.getDropDownList(endPoint, active, queryParams).collect {
                    _getDropDownList.send(it)
                }
            } catch (e: Exception) {
            } finally {
                jobGetDropDownList = null
            }
        }
    }
}