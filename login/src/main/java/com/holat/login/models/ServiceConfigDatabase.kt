package com.holat.login.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
Created by Mohamed Mohamed Taha on 11/13/2023
 */
@Entity(tableName = "service_config")
class ServiceConfigDatabase{
    companion object{
        var serviceConfigDatabase = ServiceConfigDatabase()
    }
    @PrimaryKey(autoGenerate = true)
    @SerializedName("ID")
    var id: Int = 0
    @SerializedName("AGENT_ADD_TICKETS_WITH_SAME_CRITERIA")
    var agentAddTicketsWithSameCriteria: String=""
    @SerializedName("AmplitudeEnabled")
    var amplitudeEnabled: String=""
    @SerializedName("BeneficiaryMaxTicketsPerDay")
    var beneficiaryMaxTicketsPerDay: String=""
    @SerializedName("BeneficiaryMaxTicketsPerDayPerOrg")
    var beneficiaryMaxTicketsPerDayPerOrg: String=""
    @SerializedName("BirthDate_Hijri")
    var birthDateHijri: String=""
    @SerializedName("CHATBOT_ENABLED")
    var chatBotEnabled: String=""
    @SerializedName("CHATBOT_SRC_URL")
    var chatBotSrcUrl: String=""
    @SerializedName("MAIN_SITE_URL")
    var mainSiteURl: String=""
    @SerializedName("SMALL_IMAGE")
    var smallImage: String=""
    @SerializedName("CLIENT_NAME")
    var clientName: String=""
    @SerializedName("CLIENT_NAME_AR")
    var clientNameAr: String=""
    @SerializedName("Email")
    var email: String=""
    @SerializedName("SLA_Solving_Time")
    var slaSolvingTime: String=""
    @SerializedName("Unified_Number")
    var unifiedNumber: String=""
    @SerializedName("Working_Hours")
    var workingHours: String=""
    @SerializedName("UPLOAD_FILES_FROM_SELFSERVICE")
    var uploadFilesFromSelfService: String=""
    @SerializedName("FeedbackExpiredTimeInDays")
    var feedbackExpiredTimeInDays: String=""
    @SerializedName("SELFSERVICE_LOGIN_ENABLED")
    var selfServiceLoginEnabled: String=""
    @SerializedName("SELFSERVICE_LOGIN_WITH_MOBILE")
    var selfServiceLoginWithMobile: String=""
    @SerializedName("SELFSERVICE_LOGIN_WITH_NATIONAL_ID")
    var selfServiceLoginWithNationalId: String=""
    @SerializedName("SELF_SERVICE_LOGIN_WAY")
    var selfServiceLoginWay: String=""
    @SerializedName("MAINTENANCE_MODE")
    var maintenanceMode: String=""
    @SerializedName("MIN_CHARS_ON_DETAILS")
    var minCharsOnDetails: String=""
    @SerializedName("MOBILE_DEFAULT_COUNTRY")
    var mobileDefaultCountry: String=""
    @SerializedName("NAFATH_ENABLED")
    var nafathEnabled: String=""
    @SerializedName("RATE_CONFIG_ID")
    var rateConfigId: String=""
    @SerializedName("SELF_SERVICE_ADD_TICKET_WITH_SAME_CRITERIA")
    var selfServiceAddTicketWithSameCriteria: String=""
    @SerializedName("SELF_SERVICE_AFTER_FEEDBACK_MESSAGE_AR")
    var selfServiceAfterFeedbackMessageAr: String=""
    @SerializedName("SELF_SERVICE_AFTER_FEEDBACK_MESSAGE_EN")
    var selfServiceAfterFeedbackMessageEn: String=""
    @SerializedName("SELF_SERVICE_CLIENT_CAN_CHANGE_STATUS")
    var selfServiceClientCanChangeStatus: String=""
    @SerializedName(("SELF_SERVICE_OTP_BY"))
    var selfServiceOtpBy: String=""
    @SerializedName("SELF_SERVICE_SHOW_TERMS")
    var selfServiceShowTerms: String=""
    @SerializedName("SELF_SERVICE_SIMILAR_TICKET")
    var selfServiceSimilarTicket: String=""
    @SerializedName("SELF_SERVICE_TERMS_CONTENT")
    var selfServiceTermsContent: String=""
    @SerializedName("SHOW_NO_CASE_AT_RATING")
    var showNoCaseAtRating: String=""
    @SerializedName("FACEBOOK_URL")
    var facebookUrl: String=""
    @SerializedName("INSTAGRAM_URL")
    var instagramUrl: String=""
    @SerializedName("LINKEDIN_URL")
    var linkedinUrl: String=""
    @SerializedName("SNAPCHAT_URL")
    var snapChatUrl: String=""
    @SerializedName("TIKTOK_URL")
    var tiktokUrl: String=""
    @SerializedName("TWITTER_URL")
    var twitterUrl: String=""
    @SerializedName("YOUTUBE_URL")
    var youtubeURl: String=""
    @SerializedName("SelfServiceFeedbackQuestionnaire")
    var selfServiceFeedbackQuestionnaire: String=""
    @SerializedName("SelfServiceHospitalID")
    var selfServiceHospitalID: String=""
    @SerializedName("SelfServiceOTPEnabled")
    var selfServiceOTPEnabled: String=""
    @SerializedName("SelfServiceOTPExpirationTimePerMin")
    var selfServiceOTPExpirationTimePerMin: String=""
    @SerializedName("SelfService_BackgroundImage")
    var selfServiceBackgroundImage: String=""
    @SerializedName("SelfService_ContactUs_Display")
    var selfServiceContactUsDisplay: String=""
    @SerializedName("SelfService_ENABLED")
    var selfServiceEnabled: String=""
    @SerializedName("SelfServiceKBDisplay")
    var selfServiceKBDisplay: String=""
    @SerializedName("COPY_RIGHT_AR")
    var copyRightAr: String=""
    @SerializedName("COPY_RIGHT_EN")
    var copyRightEn: String=""
    @SerializedName("COPY_RIGHT_URL")
    var copyRightUrl: String=""
    @SerializedName("FavIcon")
    var favIcon: String=""
    @SerializedName("HEADER_COLOR")
    var headerColor: String=""
    @SerializedName("HEADER_HOVER_COLOR")
    var headerHoverColor: String=""
    @SerializedName("LOGO")
    var largeLogo: String=""
    @SerializedName("MINI_LOGO")
    var smallLogo: String=""
    @SerializedName("HEADER_COLOR")
    var primaryColor : String=""
    @SerializedName("SELF_SERVICE_DISPLAY_FONT_AR")
    var selfServiceDisplayFontAr: String=""
    @SerializedName("SELF_SERVICE_DISPLAY_FONT_EN")
    var selfServiceDisplayFontEn: String=""
    @SerializedName("BACK_GROUND_IMAGE")
    var backgroundImage:String =""
}