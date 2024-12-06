package com.holat.holat.data.network

import com.holat.holat.data.models.compliant.mainreasons.MainReasons
import com.holat.holat.data.models.compliant.tickettype.TicketTypeResponse
import com.holat.holat.data.models.fields.FieldResponse
import com.holat.holat.data.models.logout.ResponseLogout
import com.holat.holat.data.models.organization.OrganizationResponse
import com.holat.holat.data.models.storeticket.StoreTicketResponse
import com.holat.holat.data.models.tickets.DataTicket
import com.holat.holat.data.models.tickets.Tickets
import com.holat.login.models.cities.Cities
import com.holat.login.models.clientprofile.ClientProfile
import com.holat.login.models.countries.Countries
import com.holat.login.models.createaccount.Response
import com.holat.login.models.nationaltypes.NationalTypes
import com.holat.login.models.regions.Regions
import com.holat.login.models.responses.ResponsesResponse
import com.holat.login.network.ApiHandler
import com.holat.login.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.RequestBody
import javax.inject.Inject

/**
Created by Mohamed Mohamed Taha on 07/11/2023
 */
class OnlineMainDataSource @Inject constructor(private val apiServices: ApiMainServices) : MainRepository,
    ApiHandler {


    override suspend fun getAllNationalTypes(): Flow<NetworkResult<NationalTypes>> {
        return flow { emit(handleApi { apiServices.getAllNationalTypes() }) }.flowOn(Dispatchers.IO)
    }


    override suspend fun clientProfile(): Flow<NetworkResult<ClientProfile>> {
        return flow { emit(handleApi { apiServices.clientProfile() }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateClientProfile(
        name: String,
        nationalId: Long,
        countryId: String,
        regionId: String,
        cityId: String,
        idEndDate: String,
        mobile: String,
        email: String,
        birthId: String
    ): Flow<NetworkResult<Response>> {
        return flow {
            emit(handleApi {
                apiServices.updateClientProfile(
                    name,
                    nationalId,
                    countryId,
                    regionId,
                    cityId,
                    idEndDate,
                    mobile,
                    email,
                    birthId
                )
            })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun logout(): Flow<NetworkResult<ResponseLogout>> {
        return flow { emit(handleApi { apiServices.logout() }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun oldTickets(perPage: Int): Flow<NetworkResult<Tickets>> {
        return flow { emit(handleApi { apiServices.oldTickets(perPage) }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun storeTicket(requestBody: RequestBody): Flow<NetworkResult<StoreTicketResponse>> {
        return flow { emit(handleApi { apiServices.storeTicket(requestBody) }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun ticketSearch(ticketId: String): Flow<NetworkResult<DataTicket>> {
        return flow { emit(handleApi { apiServices.ticketSearch(ticketId = ticketId) }) }.flowOn(
            Dispatchers.IO
        )
    }

    override suspend fun getFields(): Flow<NetworkResult<FieldResponse>> {
        return flow { emit(handleApi { apiServices.getFields() }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun allTicketType(active: Int): Flow<NetworkResult<TicketTypeResponse>> {
        return flow { emit(handleApi { apiServices.allTicketType(active) }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun mainReasons(
        active: Int,
        ticketTypeId: Int
    ): Flow<NetworkResult<ArrayList<MainReasons>>> {
        return flow { emit(handleApi { apiServices.mainReasons(active, ticketTypeId) }) }.flowOn(
            Dispatchers.IO
        )
    }

    override suspend fun subReasons(
        active: Int,
        mainReasonId: Int
    ): Flow<NetworkResult<ArrayList<MainReasons>>> {
        return flow { emit(handleApi { apiServices.subReasons(active, mainReasonId) }) }.flowOn(
            Dispatchers.IO
        )
    }

    override suspend fun getAllOrganization(mainReasonId: Int): Flow<NetworkResult<OrganizationResponse>> {
        return flow { emit(handleApi { apiServices.getAllOrganization(mainReasonId) }) }.flowOn(
            Dispatchers.IO
        )
    }

    override suspend fun subSubReasons(
        active: Int,
        subReasonId: Int
    ): Flow<NetworkResult<ArrayList<MainReasons>>> {
        return flow { emit(handleApi { apiServices.subSubReasons(active, subReasonId) }) }.flowOn(
            Dispatchers.IO
        )
    }

    override suspend fun subSubSubReasons(
        active: Int,
        subSubReasonId: Int
    ): Flow<NetworkResult<ArrayList<MainReasons>>> {
        return flow {
            emit(handleApi {
                apiServices.subSubSubReasons(
                    active,
                    subSubReasonId
                )
            })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getResponses(
        ticketId: Long,
        page: Int,
        perPage: Int
    ): Flow<NetworkResult<ResponsesResponse>> {
        return flow {
            emit(handleApi {
                apiServices.getResponses(
                    ticketId,
                    page,
                    perPage
                )
            })
        }.flowOn(Dispatchers.IO)
    }

//    override suspend fun getResponsesPaging(
//        ticketId: Long,
//        page: Int,
//        perPage: Int
//    ): Flow<PagingData<ResponsesResponse>> {
//        return Pager(config = PagingConfig(pageSize = 50, enablePlaceholders = false),
//            pagingSourceFactory = {ResponsesPagingSource(ticketId,page,perPage)}).flow
//    }

    override suspend fun evaluate(): Flow<NetworkResult<ResponsesResponse>> {
        return flow { emit(handleApi { apiServices.evaluate() }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun downloadFile(path: String): Flow<NetworkResult<Void>> {
        return flow { emit(handleApi { apiServices.downloadFile(path) }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAllCountries(): Flow<NetworkResult<Countries>> {
        return flow { emit(handleApi { apiServices.getAllCountries() }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAllRegions(countryId: Int): Flow<NetworkResult<Regions>> {
        return flow { emit(handleApi { apiServices.getAllRegions(countryId) }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAllCities(countryId: Int, regionId: Int): Flow<NetworkResult<Cities>> {
        return flow { emit(handleApi { apiServices.getAllCities(countryId, regionId) }) }.flowOn(
            Dispatchers.IO
        )
    }

    override suspend fun getDropDownList(
        endPoint: String,
        active: Int, queryParams: Map<String, String>
    ): Flow<NetworkResult<ArrayList<MainReasons>>> {
        return flow {
            emit(handleApi {
                apiServices.getDropDownList(
                    endPoint,
                    active,
                    queryParams
                )
            })
        }.flowOn(Dispatchers.IO)

    }

}