package com.holat.login.network
import com.holat.login.models.ServiceConfig
import com.holat.login.models.captcha.Captcha
import com.holat.login.models.clientprofile.ClientProfile
import com.holat.login.models.createaccount.Response
import com.holat.login.models.createaccount.ResponseSendOtp
import com.holat.login.models.login.ResponseLogin
import com.holat.login.models.nafath.Nafath
import com.holat.login.models.nafath.NafathStatus
import com.holat.login.models.nafath.ResponseNafath
import com.holat.login.models.nafath.responsenafathstatus.ResponseNafathStatus
import com.holat.login.models.nationaltypes.NationalTypes
import com.holat.login.models.sendotp.SendOtp
import com.holat.login.models.verifyotp.VerifyOtp
import com.holat.login.network.Urls.ALL_MAIN_REASONS
import com.holat.login.network.Urls.ALL_NATIONAL_TYPES
import com.holat.login.network.Urls.ALL_TICKET_TYPE
import com.holat.login.network.Urls.CLIENT_PROFILE
import com.holat.login.network.Urls.CREATE_ACCOUNT
import com.holat.login.network.Urls.FIELDS
import com.holat.login.network.Urls.LOGOUT
import com.holat.login.network.Urls.NAFATH
import com.holat.login.network.Urls.NAFATH_SEND_VERIFY_CODE
import com.holat.login.network.Urls.NAFATH_STATUS
import com.holat.login.network.Urls.NAFATH_VERIFY_MOBILE
import com.holat.login.network.Urls.OLD_TICKETS
import com.holat.login.network.Urls.RELOAD_CAPTCHA
import com.holat.login.network.Urls.SEARCH_TICKET
import com.holat.login.network.Urls.SELF_SERVICE_CONFIGS
import com.holat.login.network.Urls.SEND_OTP
import com.holat.login.network.Urls.STORE_TICKET
import com.holat.login.network.Urls.UPDATE_CLIENT_PROFILE
import com.holat.login.network.Urls.VERIFY_OTP
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
Created by Mohamed Mohamed Taha on 07/11/2023
 */
interface ApiServices {
    @GET(SELF_SERVICE_CONFIGS)
    suspend fun getSelfServiceConfigs(): ServiceConfig

    @GET(ALL_NATIONAL_TYPES)
    suspend fun getAllNationalTypes(): NationalTypes

    @GET(RELOAD_CAPTCHA)
    suspend fun reloadCaptcha(): Captcha

    @FormUrlEncoded
    @POST(CREATE_ACCOUNT)
    suspend fun createAccount(
        @Field("key") key: String,
        @Field("captcha_code") captchaCode: String,
        @Field("national_id") nationalId: Long,
        @Field("name") name: String,
        @Field("mobile") mobile: String,
        @Field("email") email: String,
        @Field("birthdate") birthDay: String,
        @Field("national_id_type_id") nationalIdTypeId: Short
    ): Response
//    @FormUrlEncoded
//    @POST(SEND_OTP)
//    suspend fun sendOtp(@Field("key") key: String,
//                        @Field("captcha_code") captchaCode: String,
//                        @Field("national_id") nationalId: Long,
//                        @Field("mobile") mobile: String): ResponseSendOtp

    @POST(SEND_OTP)
    suspend fun sendOtp(@Body sendOtp: SendOtp): ResponseSendOtp

    @POST(VERIFY_OTP)
    suspend fun verifyOtp(@Body verifyOtp: VerifyOtp): ResponseLogin

    @POST(NAFATH)
    suspend fun nafath(@Body nafath: Nafath): ResponseNafath

    @POST(NAFATH_STATUS)
    suspend fun nafathStatus(@Body nafathStatus: NafathStatus): ResponseNafathStatus

    @Headers("isAuthorize: true")
    @FormUrlEncoded
    @POST(NAFATH_SEND_VERIFY_CODE)
    suspend fun nafathSendVerifyCode(
        @Field("mobile") mobile: String,
        @Field("email") email: String
    ): Response

    @Headers("isAuthorize: true")
    @FormUrlEncoded
    @POST(NAFATH_VERIFY_MOBILE)
    suspend fun nafathVerifyMobile(@Field("otp_code") otpCode: String): ResponseLogin

    @Headers("isAuthorize: true")
    @GET(CLIENT_PROFILE)
    suspend fun clientProfile(): ClientProfile

    @Headers("isAuthorize: true")
    @FormUrlEncoded
    @POST(UPDATE_CLIENT_PROFILE)
    suspend fun updateClientProfile(
        @Field("name") name: String,
        @Field("national_id") nationalId: Long,
        @Field("country_id") countryId: String,
        @Field("region_id") regionId: String,
        @Field("city_id") cityId: String,
        @Field("mobile") mobile: String,
        @Field("email") email: String,
        @Field("birthdate") birthDay: String,
        @Field("id_endDate") idEndDate: String
    ): Response

//    @Headers("isAuthorize: true")
//    @GET(OLD_TICKETS)
//    suspend fun oldTickets(@Query("per_page")perPage: Int): Tickets
//
//    @Headers("isAuthorize: true")
//    @POST(STORE_TICKET)
//    suspend fun storeTicket(@Body requestBody: RequestBody): StoreTicketResponse
//
//    @Headers("isAuthorize: true")
//    @FormUrlEncoded
//    @POST(SEARCH_TICKET)
//    suspend fun ticketSearch(@Field("ticket_id") ticketId: String): DataTicket
//
//
//    @Headers("isAuthorize: true")
//    @GET(FIELDS)
//    suspend fun getFields(): FieldsResponse
//
//    //    @Headers("isAuthorize: true")
////    @GET("$ALL_TICKET_TYPE/{active}")
////    suspend fun allTicketType(@Path ("active") active:Int): FieldsResponse
//    @Headers("isAuthorize: true")
//    @GET(ALL_TICKET_TYPE)
//    suspend fun allTicketType(@Query("active") active: Int): TicketTypeResponse
//
//    @Headers("isAuthorize: true")
//    @GET(ALL_MAIN_REASONS)
//    suspend fun mainReasons(
//        @Query("active") active: Int,
//        @Query("ticket_type_id") ticketTypeId: Int
//    ): ArrayList<MainReasons>
//
//    @Headers("isAuthorize: true")
//    @GET(SUB_REASONS)
//    suspend fun subReasons(
//        @Query("active") active: Int,
//        @Query("main_reason_id") mainReasonId: Int
//    ): ArrayList<MainReasons>
//
//    @Headers("isAuthorize: true")
//    @GET(ALL_ORGANIZATION)
//    suspend fun getAllOrganization(
//        @Query("main_reason_id") mainReasonId: Int
//    ): OrganizationResponse
//
//    @Headers("isAuthorize: true")
//    @GET(SUB_SUB_REASONS)
//    suspend fun subSubReasons(
//        @Query("active") active: Int,
//        @Query("sub_reason_id") subReasonId: Int
//    ): ArrayList<MainReasons>
//
//    @Headers("isAuthorize: true")
//    @GET(SUB_SUB_SUB_REASONS)
//    suspend fun subSubSubReasons(
//        @Query("active") active: Int,
//        @Query("sub_sub_reason_id") subSubReasonId: Int
//    ): ArrayList<MainReasons>
//
//    @Headers("isAuthorize: true")
//    @GET(GET_RESPONSES)
//    suspend fun getResponses(
//        @Query("ticket_id") ticketId: Long,
//        @Query("page") page: Int,
//        @Query("per_page") perPage: Int
//    ): ResponsesResponse
//
//    @Headers("isAuthorize: true")
//    @GET(QUESTIONS)
//    suspend fun evaluate(): ResponsesResponse
//
//    @FormUrlEncoded
//    @Headers("isAuthorize: true")
//    @POST(DOWNLOAD_FILE)
//    suspend fun downloadFile(@Field("path") path: String): Void
//
//    @GET(ALL_COUNTRIES)
//    suspend fun getAllCountries(): Countries
//
//    @GET(ALL_REGIONS)
//    suspend fun getAllRegions(@Query("country_id") countryId: Int): Regions
//
//    @GET(ALL_CITIES)
//    suspend fun getAllCities(
//        @Query("country_id") countryId: Int,
//        @Query("region_id") regionId: Int
//    ): Cities
//
//    @Headers("isAuthorize: true")
//    @GET("$DROP_DOWN_LIST{endPoint}")
//    suspend fun getDropDownList(
//        @Path("endPoint") endPoint:String,
//        @Query("active") active: Int,
//        @QueryMap  queryParams: Map<String,String>
//    ): ArrayList<MainReasons>
}