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
import com.holat.login.network.Urls.ALL_CITIES
import com.holat.login.network.Urls.ALL_COUNTRIES
import com.holat.login.network.Urls.ALL_MAIN_REASONS
import com.holat.login.network.Urls.ALL_NATIONAL_TYPES
import com.holat.login.network.Urls.ALL_ORGANIZATION
import com.holat.login.network.Urls.ALL_REGIONS
import com.holat.login.network.Urls.ALL_TICKET_TYPE
import com.holat.login.network.Urls.CLIENT_PROFILE
import com.holat.login.network.Urls.DOWNLOAD_FILE
import com.holat.login.network.Urls.DROP_DOWN_LIST
import com.holat.login.network.Urls.FIELDS
import com.holat.login.network.Urls.GET_RESPONSES
import com.holat.login.network.Urls.LOGOUT
import com.holat.login.network.Urls.OLD_TICKETS
import com.holat.login.network.Urls.QUESTIONS
import com.holat.login.network.Urls.SEARCH_TICKET
import com.holat.login.network.Urls.STORE_TICKET
import com.holat.login.network.Urls.SUB_REASONS
import com.holat.login.network.Urls.SUB_SUB_REASONS
import com.holat.login.network.Urls.SUB_SUB_SUB_REASONS
import com.holat.login.network.Urls.UPDATE_CLIENT_PROFILE
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
interface ApiMainServices {

    @GET(ALL_NATIONAL_TYPES)
    suspend fun getAllNationalTypes(): NationalTypes


    @Headers("isAuthorize: true")
    @POST(LOGOUT)
    suspend fun logout(): ResponseLogout

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

    @Headers("isAuthorize: true")
    @GET(OLD_TICKETS)
    suspend fun oldTickets(@Query("per_page") perPage: Int): Tickets

    @Headers("isAuthorize: true")
    @POST(STORE_TICKET)
    suspend fun storeTicket(@Body requestBody: RequestBody): StoreTicketResponse

    @Headers("isAuthorize: true")
    @FormUrlEncoded
    @POST(SEARCH_TICKET)
    suspend fun ticketSearch(@Field("ticket_id") ticketId: String): DataTicket


    @Headers("isAuthorize: true")
    @GET(FIELDS)
    suspend fun getFields(): FieldResponse

    //    @Headers("isAuthorize: true")
//    @GET("$ALL_TICKET_TYPE/{active}")
//    suspend fun allTicketType(@Path ("active") active:Int): FieldsResponse
    @Headers("isAuthorize: true")
    @GET(ALL_TICKET_TYPE)
    suspend fun allTicketType(@Query("active") active: Int): TicketTypeResponse

    @Headers("isAuthorize: true")
    @GET(ALL_MAIN_REASONS)
    suspend fun mainReasons(
        @Query("active") active: Int,
        @Query("ticket_type_id") ticketTypeId: Int
    ): ArrayList<MainReasons>

    @Headers("isAuthorize: true")
    @GET(SUB_REASONS)
    suspend fun subReasons(
        @Query("active") active: Int,
        @Query("main_reason_id") mainReasonId: Int
    ): ArrayList<MainReasons>

    @Headers("isAuthorize: true")
    @GET(ALL_ORGANIZATION)
    suspend fun getAllOrganization(
        @Query("main_reason_id") mainReasonId: Int
    ): OrganizationResponse

    @Headers("isAuthorize: true")
    @GET(SUB_SUB_REASONS)
    suspend fun subSubReasons(
        @Query("active") active: Int,
        @Query("sub_reason_id") subReasonId: Int
    ): ArrayList<MainReasons>

    @Headers("isAuthorize: true")
    @GET(SUB_SUB_SUB_REASONS)
    suspend fun subSubSubReasons(
        @Query("active") active: Int,
        @Query("sub_sub_reason_id") subSubReasonId: Int
    ): ArrayList<MainReasons>

    @Headers("isAuthorize: true")
    @GET(GET_RESPONSES)
    suspend fun getResponses(
        @Query("ticket_id") ticketId: Long,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ResponsesResponse

    @Headers("isAuthorize: true")
    @GET(QUESTIONS)
    suspend fun evaluate(): ResponsesResponse

    @FormUrlEncoded
    @Headers("isAuthorize: true")
    @POST(DOWNLOAD_FILE)
    suspend fun downloadFile(@Field("path") path: String): Void

    @GET(ALL_COUNTRIES)
    suspend fun getAllCountries(): Countries

    @GET(ALL_REGIONS)
    suspend fun getAllRegions(@Query("country_id") countryId: Int): Regions

    @GET(ALL_CITIES)
    suspend fun getAllCities(
        @Query("country_id") countryId: Int,
        @Query("region_id") regionId: Int
    ): Cities

    @Headers("isAuthorize: true")
    @GET("$DROP_DOWN_LIST{endPoint}")
    suspend fun getDropDownList(
        @Path("endPoint") endPoint: String,
        @Query("active") active: Int,
        @QueryMap queryParams: Map<String, String>
    ): ArrayList<MainReasons>
}