package com.holat.login.network.repository

import com.holat.login.models.nationaltypes.NationalTypes
import com.holat.login.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import com.holat.login.models.captcha.Captcha
import com.holat.login.models.createaccount.Response
import com.holat.login.models.createaccount.ResponseSendOtp
import com.holat.login.models.login.ResponseLogin
import com.holat.login.models.nafath.Nafath
import com.holat.login.models.nafath.NafathStatus
import com.holat.login.models.nafath.ResponseNafath
import com.holat.login.models.sendotp.SendOtp
import com.holat.login.models.verifyotp.VerifyOtp
import com.holat.login.models.clientprofile.ClientProfile
import com.holat.login.models.nafath.responsenafathstatus.ResponseNafathStatus

/**
Created by Mohamed Mohamed Taha on 07/11/2023
 */
interface LoginRepository {
    suspend fun getAllNationalTypes(): Flow<NetworkResult<NationalTypes>>
    suspend fun reloadCaptcha(): Flow<NetworkResult<Captcha>>
    suspend fun createAccount(
        key: String,
        captchaCode: String,
        nationalId: Long,
        name: String,
        mobile: String,
        email: String,
        birthDay: String,
        nationalIdTypeId: Short
    ): Flow<NetworkResult<Response>>

    suspend fun sendOtp(sendOtp: SendOtp): Flow<NetworkResult<ResponseSendOtp>>
    suspend fun verifyOtp(verifyOtp: VerifyOtp): Flow<NetworkResult<ResponseLogin>>

    suspend fun nafath(nafath: Nafath): Flow<NetworkResult<ResponseNafath>>
    suspend fun nafathStatus( nafathStatus: NafathStatus): Flow<NetworkResult<ResponseNafathStatus>>
    suspend fun nafazSendVerifyCode(mobile:String, email:String): Flow<NetworkResult<Response>>
    suspend fun nafazVerifyMobile(otpCode:String): Flow<NetworkResult<ResponseLogin>>

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
//    suspend fun logout(): Flow<NetworkResult<ResponseLogout>>
//    suspend fun oldTickets(perPage: Int): Flow<NetworkResult<Tickets>>
//    suspend fun storeTicket(requestBody: RequestBody): Flow<NetworkResult<StoreTicketResponse>>
//    suspend fun ticketSearch(ticketId:String): Flow<NetworkResult<DataTicket>>
//    suspend fun getFields(): Flow<NetworkResult<FieldsResponse>>
//    suspend fun allTicketType(active:Int): Flow<NetworkResult<TicketTypeResponse>>
//
//    suspend fun mainReasons( active:Int,ticketTypeId :Int): Flow<NetworkResult<ArrayList<MainReasons>>>
//
//    suspend fun subReasons(active:Int, mainReasonId :Int): Flow<NetworkResult<ArrayList<MainReasons>>>
//
//    suspend fun getAllOrganization(mainReasonId: Int): Flow<NetworkResult<OrganizationResponse>>
//    suspend fun subSubReasons(active:Int, subReasonId :Int): Flow<NetworkResult<ArrayList<MainReasons>>>
//
//    suspend fun subSubSubReasons(active:Int, subSubReasonId :Int): Flow<NetworkResult<ArrayList<MainReasons>>>
//
//    suspend fun getResponses(ticketId: Long, page: Int, perPage: Int): Flow<NetworkResult<ResponsesResponse>>
////    suspend fun getResponsesPaging(ticketId: Long, page: Int, perPage: Int): Flow<PagingData<ResponsesResponse>>
//    suspend fun evaluate(): Flow<NetworkResult<ResponsesResponse>>
//
//    suspend fun downloadFile(path:String): Flow<NetworkResult<Void>>
//
//    suspend fun getAllCountries(): Flow<NetworkResult<Countries>>
//    suspend fun getAllRegions( countryId: Int): Flow<NetworkResult<Regions>>
//    suspend fun getAllCities( countryId: Int,regionId: Int): Flow<NetworkResult<Cities>>
//    suspend fun getDropDownList(endPoint:String,active:Int,queryParams: Map<String,String>): Flow<NetworkResult<ArrayList<MainReasons>>>

}