package com.holat.login.models.nafath.responsenafathstatus

import com.google.gson.annotations.SerializedName
import com.holat.login.models.cities.CityData
import com.holat.login.models.countries.CountryData

import com.holat.login.models.regions.RegionData

data class Client(
    val id: Int, // 415270
    val name: String, // محمد محمد طه حسن
    val gender: String, // male
    val birthdate: String, // 1988-07-03
    val mobile: String, // 966535020871
    val address: String? = null, // null
    @SerializedName("national_id")
    val nationalId: String, // 2352946137
    @SerializedName("national_id_type_id")
    val nationalIdTypeId: Int? = null // 1
) {
    val active: Int? = null // 1
    val email: String? = null // null

    @SerializedName("client_id")
    val clientId: String? = null // null

    @SerializedName("added_by")
    val addedBy: String? = null // portal

    @SerializedName("albryd_alalktrony")
    val albrydAlalktrony: String? = null// null
    val city: CityData? = null // null

    @SerializedName("city_id")
    val cityId: String? = null // null
    val client: String? = null // null

    @SerializedName("client_type_id")
    val clientTypeId: String? = null // null
    val company: String? = null // null

    @SerializedName("company_id")
    val companyId: String? = null // null
    val country: CountryData? = null // null

    @SerializedName("country_id")
    val countryId: String? = null // null

    @SerializedName("created_at")
    val createdAt: String = "" // 2023-08-24 12:40:38

    @SerializedName("creator_id")
    val creatorId: String? = null // null

    @SerializedName("data_completed")
    val dataCompleted: Int? = null// 1

    @SerializedName("deleted_at")
    val deletedAt: String? = null // null
    val hospitals: List<Hospital>? = null

    @SerializedName("id_endDate")
    val idEndDate: String? = null // null

    @SerializedName("ministry_member")
    val ministryMember: String? = null // null

    @SerializedName("mobile_verified_at")
    val mobileVerifiedAt: String = "" // 2023-08-24 12:40:53

    @SerializedName("nationality_id")
    val nationalityId: String? = null // null

    @SerializedName("preferred_language")
    val preferredLanguage: String = "" // ar
    val region: RegionData? = null // null

    @SerializedName("region_id")
    val regionId: String? = null // null

    @SerializedName("social_users")
    val socialUsers: List<Any>? = null

    @SerializedName("updated_at")
    val updatedAt: String = "" // 2023-08-24 12:40:53

    @SerializedName("verified_by")
    val verifiedBy: String? = null // null

    @SerializedName("verified_by_nafath")
    val verifiedByNafath: Int? = null // 1
    val telephone: String? = null // null

}