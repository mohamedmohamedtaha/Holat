package com.holat.login.models.clientprofile

import com.google.gson.annotations.SerializedName
import com.holat.login.models.cities.CityData
import com.holat.login.models.countries.CountryData
import com.holat.login.models.login.NationalIdType
import com.holat.login.models.regions.RegionData

data class ClientProfile(
    val active: Int, // 1
    @SerializedName("add_for_bene")
    val addForBene: String? = null, // null
    @SerializedName("added_by")
    val addedBy: String, // portal
    val address: String? = null, // null
    val ahahaaa: String? = null, // null
    @SerializedName("benif_ar")
    val benifAr: String? = null, // null
    @SerializedName("benif_english")
    val benifEnglish: String? = null, // null
    @SerializedName("birthdate")
    val birthDate: String, // 1988-07-03
    val city: CityData? = null, // null
    @SerializedName("city_id")
    val cityId: String? = null, // null
    val client: String? = null, // null
    @SerializedName("client_id")
    val clientId: String? = null, // null
    @SerializedName("client_type")
    val clientType: String? = null, // null
    @SerializedName("client_type_id")
    val clientTypeId: String? = null, // null
    val company: String? = null, // null
    @SerializedName("company_id")
    val companyId: String? = null, // null
    @SerializedName("constraint_benif_selfs")
    val constraintBenifSelfs: String? = null, // null
    val country: CountryData? = null, // null
    @SerializedName("country_id")
    val countryId: String? = null, // null
    @SerializedName("create_new_field")
    val createNewField: String? = null, // null
    @SerializedName("created_at")
    val createdAt: String, // 2024-08-13 19:26:17
    @SerializedName("creator_id")
    val creatorId: String? = null, // null
    @SerializedName("data_completed")
    val dataCompleted: Int, // 1
    @SerializedName("deleted_at")
    val deletedAt: String? = null, // null
    @SerializedName("diab_system_field")
    val diabSystemField: String? = null, // null
    val email: String, // mohamed.taha169@yahoo.com
    val gender: String? = null, // null
    val hospitals: List<Any>,
    val id: Int, // 2598
    @SerializedName("id_endDate")
    val idEndDate: String? = null, // null
    @SerializedName("ksddhd_field") // l
    val ksddhdField: String? = null, // null
    @SerializedName("ministry_member")
    val ministryMember: String? = null, // null
    val mobile: String, // +966535020871
    @SerializedName("mobile_verified_at")
    val mobileVerifiedAt: String? = null, // null
    val name: String, // Mohamed taha
    @SerializedName("national_id")
    val nationalId: String, // 2352946177
    @SerializedName("national_id_type")
    val nationalIdType: NationalIdType,
    @SerializedName("national_id_type_id")
    val nationalIdTypeId: Int, // 2
    @SerializedName("nationality_id")
    val nationalityId: String? = null, // null
    @SerializedName("new_bene_field")
    val newBeneField: String? = null, // null
    @SerializedName("new_benif")
    val newBenif: String? = null, // null
    @SerializedName("new_benif_constraint")
    val newBenifConstraint: String? = null, // null
    @SerializedName("preferred_language")
    val preferredLanguage: String, // ar
    val region: RegionData? = null, // null
    @SerializedName("region_id")
    val regionId: String? = null, // null
    val telephone: String? = null, // null
    @SerializedName("ticketfield")
    val ticketField: String? = null, // null
    @SerializedName("updated_at")
    val updatedAt: String, // 2024-08-13 19:26:17
    @SerializedName("verified_by")
    val verifiedBy: String? = null, // null
    @SerializedName("verified_by_nafath")
    val verifieBbyNafath: Int // 0
)