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
import com.holat.login.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

/**
Created by Mohamed Mohamed Taha on 07/11/2023
 */
interface MainRepository {
    suspend fun getAllNationalTypes(): Flow<NetworkResult<NationalTypes>>

    suspend fun clientProfile(): Flow<NetworkResult<ClientProfile>>
    suspend fun updateClientProfile(
        name: String,
        nationalId: Long,
        countryId: String,
        regionId: String,
        cityId: String,
        idEndDate: String,
        mobile: String,
        email: String,
        birthId: String
    ): Flow<NetworkResult<Response>>

    suspend fun logout(): Flow<NetworkResult<ResponseLogout>>
    suspend fun oldTickets(perPage: Int): Flow<NetworkResult<Tickets>>
    suspend fun storeTicket(requestBody: RequestBody): Flow<NetworkResult<StoreTicketResponse>>
    suspend fun ticketSearch(ticketId: String): Flow<NetworkResult<DataTicket>>
    suspend fun getFields(): Flow<NetworkResult<FieldResponse>>
    suspend fun allTicketType(active: Int): Flow<NetworkResult<TicketTypeResponse>>

    suspend fun mainReasons(
        active: Int,
        ticketTypeId: Int
    ): Flow<NetworkResult<ArrayList<MainReasons>>>

    suspend fun subReasons(
        active: Int,
        mainReasonId: Int
    ): Flow<NetworkResult<ArrayList<MainReasons>>>

    suspend fun getAllOrganization(mainReasonId: Int): Flow<NetworkResult<OrganizationResponse>>
    suspend fun subSubReasons(
        active: Int,
        subReasonId: Int
    ): Flow<NetworkResult<ArrayList<MainReasons>>>

    suspend fun subSubSubReasons(
        active: Int,
        subSubReasonId: Int
    ): Flow<NetworkResult<ArrayList<MainReasons>>>

    suspend fun getResponses(
        ticketId: Long,
        page: Int,
        perPage: Int
    ): Flow<NetworkResult<ResponsesResponse>>

    //    suspend fun getResponsesPaging(ticketId: Long, page: Int, perPage: Int): Flow<PagingData<ResponsesResponse>>
    suspend fun evaluate(): Flow<NetworkResult<ResponsesResponse>>

    suspend fun downloadFile(path: String): Flow<NetworkResult<Void>>

    suspend fun getAllCountries(): Flow<NetworkResult<Countries>>
    suspend fun getAllRegions(countryId: Int): Flow<NetworkResult<Regions>>
    suspend fun getAllCities(countryId: Int, regionId: Int): Flow<NetworkResult<Cities>>
    suspend fun getDropDownList(
        endPoint: String,
        active: Int,
        queryParams: Map<String, String>
    ): Flow<NetworkResult<ArrayList<MainReasons>>>

}