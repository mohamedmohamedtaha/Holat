package com.holat.login.network

import com.holat.login.models.ServiceConfig
import com.holat.login.models.captcha.Captcha
import com.holat.login.models.createaccount.Response
import com.holat.login.models.createaccount.ResponseSendOtp
import com.holat.login.models.nationaltypes.NationalTypes
import com.holat.login.models.sendotp.SendOtp
import com.holat.login.network.repository.LoginRepository
import com.holat.login.network.repository.SelfServiceConfigOnlineRepository
import com.holat.login.models.clientprofile.ClientProfile
import com.holat.login.models.login.ResponseLogin
import com.holat.login.models.nafath.Nafath
import com.holat.login.models.nafath.NafathStatus
import com.holat.login.models.nafath.ResponseNafath
import com.holat.login.models.nafath.responsenafathstatus.ResponseNafathStatus
import com.holat.login.models.verifyotp.VerifyOtp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.RequestBody
import javax.inject.Inject

/**
Created by Mohamed Mohamed Taha on 07/11/2023
 */
class OnlineApis @Inject constructor(private val apiServices: ApiServices) : LoginRepository,
    SelfServiceConfigOnlineRepository, ApiHandler {
    override suspend fun getSelfServiceConfigsOnline(): Flow<NetworkResult<ServiceConfig>> {
        return flow { emit(handleApi { apiServices.getSelfServiceConfigs() }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAllNationalTypes(): Flow<NetworkResult<NationalTypes>> {
        return flow { emit(handleApi { apiServices.getAllNationalTypes() }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun reloadCaptcha(): Flow<NetworkResult<Captcha>> {
        return flow { emit(handleApi { apiServices.reloadCaptcha() }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun createAccount(
        key: String,
        captchaCode: String,
        nationalId: Long,
        name: String,
        mobile: String,
        email: String,
        birthDay: String,
        nationalIdTypeId: Short
    ): Flow<NetworkResult<Response>> {
        return flow {
            emit(handleApi {
                apiServices.createAccount(
                    key,
                    captchaCode,
                    nationalId,
                    name,
                    mobile,
                    email,
                    birthDay,
                    nationalIdTypeId
                )
            })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun sendOtp(sendOtp: SendOtp): Flow<NetworkResult<ResponseSendOtp>> {
        return flow { emit(handleApi { apiServices.sendOtp(sendOtp) }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun verifyOtp(verifyOtp: VerifyOtp): Flow<NetworkResult<ResponseLogin>> {
        return flow { emit(handleApi { apiServices.verifyOtp(verifyOtp) }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun nafath(nafath: Nafath): Flow<NetworkResult<ResponseNafath>> {
        return flow { emit(handleApi { apiServices.nafath(nafath) }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun nafathStatus(nafathStatus: NafathStatus): Flow<NetworkResult<ResponseNafathStatus>> {
        return flow { emit(handleApi { apiServices.nafathStatus(nafathStatus) }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun nafazSendVerifyCode(
        mobile: String,
        email: String
    ): Flow<NetworkResult<Response>> {
        return flow { emit(handleApi { apiServices.nafathSendVerifyCode(mobile,email) }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun nafazVerifyMobile(otpCode: String): Flow<NetworkResult<ResponseLogin>> {
        return flow { emit(handleApi { apiServices.nafathVerifyMobile(otpCode) }) }.flowOn(Dispatchers.IO)
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
                    birthId)
            })
        }.flowOn(Dispatchers.IO)
    }

//    override suspend fun logout(): Flow<NetworkResult<ResponseLogout>> {
//        return flow { emit(handleApi { apiServices.logout() }) }.flowOn(Dispatchers.IO)
//    }
//
//    override suspend fun oldTickets(perPage: Int): Flow<NetworkResult<Tickets>> {
//        return flow { emit(handleApi { apiServices.oldTickets(perPage) }) }.flowOn(Dispatchers.IO)
//    }
//
//    override suspend fun storeTicket(requestBody: RequestBody): Flow<NetworkResult<StoreTicketResponse>> {
//        return flow { emit(handleApi { apiServices.storeTicket(requestBody) }) }.flowOn(Dispatchers.IO)
//    }
//
//    override suspend fun ticketSearch(ticketId: String): Flow<NetworkResult<DataTicket>> {
//        return flow { emit(handleApi { apiServices.ticketSearch(ticketId = ticketId) }) }.flowOn(Dispatchers.IO)
//    }
//
//    override suspend fun getFields(): Flow<NetworkResult<FieldsResponse>> {
//        return flow { emit(handleApi { apiServices.getFields() }) }.flowOn(Dispatchers.IO)
//    }
//
//    override suspend fun allTicketType(active: Int): Flow<NetworkResult<TicketTypeResponse>> {
//        return flow { emit(handleApi { apiServices.allTicketType(active) }) }.flowOn(Dispatchers.IO)
//    }
//
//    override suspend fun mainReasons(
//        active: Int,
//        ticketTypeId: Int
//    ): Flow<NetworkResult<ArrayList<MainReasons>>> {
//        return flow { emit(handleApi { apiServices.mainReasons(active,ticketTypeId) }) }.flowOn(Dispatchers.IO)
//    }
//
//    override suspend fun subReasons(
//        active: Int,
//        mainReasonId: Int
//    ): Flow<NetworkResult<ArrayList<MainReasons>>> {
//       return flow { emit(handleApi { apiServices.subReasons(active,mainReasonId) }) }.flowOn(Dispatchers.IO)
//    }
//
//    override suspend fun getAllOrganization(mainReasonId: Int): Flow<NetworkResult<OrganizationResponse>> {
//        return flow { emit(handleApi { apiServices.getAllOrganization(mainReasonId) }) }.flowOn(Dispatchers.IO)
//    }
//
//    override suspend fun subSubReasons(
//        active: Int,
//        subReasonId: Int
//    ): Flow<NetworkResult<ArrayList<MainReasons>>> {
//        return flow { emit(handleApi { apiServices.subSubReasons(active,subReasonId) }) }.flowOn(Dispatchers.IO)
//    }
//
//    override suspend fun subSubSubReasons(
//        active: Int,
//        subSubReasonId: Int
//    ): Flow<NetworkResult<ArrayList<MainReasons>>> {
//        return flow { emit(handleApi { apiServices.subSubSubReasons(active,subSubReasonId) }) }.flowOn(Dispatchers.IO)
//    }
//
//    override suspend fun getResponses(
//        ticketId: Long,
//        page: Int,
//        perPage: Int
//    ): Flow<NetworkResult<ResponsesResponse>> {
//        return flow { emit(handleApi { apiServices.getResponses(ticketId,page,perPage) }) }.flowOn(Dispatchers.IO)
//    }
//
////    override suspend fun getResponsesPaging(
////        ticketId: Long,
////        page: Int,
////        perPage: Int
////    ): Flow<PagingData<ResponsesResponse>> {
////        return Pager(config = PagingConfig(pageSize = 50, enablePlaceholders = false),
////            pagingSourceFactory = {ResponsesPagingSource(ticketId,page,perPage)}).flow
////    }
//
//    override suspend fun evaluate(): Flow<NetworkResult<ResponsesResponse>> {
//        return flow { emit(handleApi { apiServices.evaluate() }) }.flowOn(Dispatchers.IO)
//    }
//
//    override suspend fun downloadFile(path: String): Flow<NetworkResult<Void>> {
//        return flow { emit(handleApi { apiServices.downloadFile(path) }) }.flowOn(Dispatchers.IO)
//    }
//
//    override suspend fun getAllCountries(): Flow<NetworkResult<Countries>> {
//        return flow { emit(handleApi { apiServices.getAllCountries() }) }.flowOn(Dispatchers.IO)
//    }
//
//    override suspend fun getAllRegions(countryId: Int): Flow<NetworkResult<Regions>> {
//        return flow { emit(handleApi { apiServices.getAllRegions(countryId) }) }.flowOn(Dispatchers.IO)
//    }
//
//    override suspend fun getAllCities(countryId: Int, regionId: Int): Flow<NetworkResult<Cities>> {
//        return flow { emit(handleApi { apiServices.getAllCities(countryId,regionId) }) }.flowOn(Dispatchers.IO)
//    }
//
//    override suspend fun getDropDownList(
//        endPoint: String,
//        active: Int,queryParams: Map<String,String>): Flow<NetworkResult<ArrayList<MainReasons>>> {
//        return flow { emit(handleApi { apiServices.getDropDownList(endPoint,active,queryParams) }) }.flowOn(Dispatchers.IO)
//
//    }

}