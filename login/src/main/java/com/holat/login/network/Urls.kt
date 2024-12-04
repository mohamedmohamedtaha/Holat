package com.holat.login.network

/**
Created by Mohamed Mohamed Taha on 07/11/2023
 */
object Urls {
    private const val MOBILE = "mobile/"
    private const val API = "api/$MOBILE"
     const val DROP_DOWN_LIST = API
    private const val SELF_SERVICE = "self_service/"
    //const val SELF_SERVICE_CONFIGS = "${API}${MOBILE}self_service_configs"
    const val SELF_SERVICE_CONFIGS = "${API}self_service_configs"
    const val ALL_NATIONAL_TYPES = "${API}all-national-types"
    const val RELOAD_CAPTCHA = "${API}reload-captcha"
    const val CREATE_ACCOUNT = "${API}createClient"
    const val SEND_OTP = "${API}sendOtp"
    const val VERIFY_OTP = "${API}verifyOtp"
    const val NAFATH = "${API}nafath"
    const val NAFATH_STATUS = "${API}nafath_status"
    const val NAFATH_SEND_VERIFY_CODE = "${API}sendVerifyCode"
    const val NAFATH_VERIFY_MOBILE = "${API}verifyMobile"
    const val CLIENT_PROFILE = "${API}client-profile"
    const val UPDATE_CLIENT_PROFILE = "${API}update-profile"
    const val LOGOUT = "${API}logout"

    const val ALL_COUNTRIES = "${API}all_countries"
    const val ALL_REGIONS = "${API}all_regions"
    const val ALL_CITIES = "${API}all_cities"


    const val OLD_TICKETS = "${API}old-tickets"
    const val STORE_TICKET = "${API}store-ticket"
    const val SEARCH_TICKET = "${API}search-ticket"
    const val UPDATE_TICKET_STATUS = "${API}update-ticket-status"
    const val FIELDS = "${API}selfService-fields"
    const val ALL_TICKET_TYPE = "${API}all-ticket-types"
    const val ALL_MAIN_REASONS = "${API}all-main-reasons"
    const val SUB_REASONS = "${API}all-sub-reasons"
    const val ALL_ORGANIZATION = "${API}all-organizations"
    const val SUB_SUB_REASONS = "${API}all-sub-sub-reasons"
    const val SUB_SUB_SUB_REASONS = "${API}all-sub-sub-sub-reasons"
    const val GET_RESPONSES = "${API}getToCustomerReplies"
    const val QUESTIONS = "${API}questions"
    const val DOWNLOAD_FILE = "${API}downloadFile"
    private const val ASSETS = "self_service/"

 }