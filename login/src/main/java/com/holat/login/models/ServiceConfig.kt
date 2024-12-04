package com.holat.login.models

import com.google.gson.annotations.SerializedName

data class ServiceConfig(
    @SerializedName("AGENT_ADD_TICKETS_WITH_SAME_CRITERIA")
    var agentAddTicketsWithSameCriteria: AgentAddTicketsWithSameCriteria,
    @SerializedName("AmplitudeEnabled")
    var amplitudeEnabled: AmplitudeEnabled,
    @SerializedName("BeneficiaryMaxTicketsPerDay")
    var beneficiaryMaxTicketsPerDay: BeneficiaryMaxTicketsPerDay,
    @SerializedName("BeneficiaryMaxTicketsPerDayPerOrg")
    var beneficiaryMaxTicketsPerDayPerOrg: BeneficiaryMaxTicketsPerDayPerOrg,
    @SerializedName("BirthDate_Hijri")
    var birthDateHijri: BirthDateHijri,
    @SerializedName("CHATBOT")
    var chatBot: ChatBot,
    @SerializedName("CLIENT_DETAILS")
    var clientDetails: ClientDetails,
    @SerializedName("CONTACT_US")
    var contactUs: ContactUs,
    @SerializedName("FILES_FEATURE")
    var filesFeature: FilesFeature,
    @SerializedName("FeedbackExpiredTimeInDays")
    var feedbackExpiredTimeInDays: FeedbackExpiredTimeInDays,
    @SerializedName("LOGIN")
    var login: Login,
    @SerializedName("MAINTENANCE_MODE")
    var maintenanceMode: MaintenanceMode,
    @SerializedName("MIN_CHARS_ON_DETAILS")
    var minCharsOnDetails: MinCharsOnDetails,
    @SerializedName("MOBILE_DEFAULT_COUNTRY")
    var mobileDefaultCountry: MobileDefaultCountry,
    @SerializedName("NAFATH_ENABLED")
    var nafathEnabled: NAFATHENABLED,
    @SerializedName("RATE_CONFIG_ID")
    var rateConfigId: RateConfigId,
    @SerializedName("SELF_SERVICE_ADD_TICKET_WITH_SAME_CRITERIA")
    var selfServiceAddTicketWithSameCriteria: SelfServiceAddTicketWithSameCriteria,
    @SerializedName("SELF_SERVICE_AFTER_FEEDBACK_MESSAGE_AR")
    var selfServiceAfterFeedbackMessageAr: SelfServiceAfterFeedbackMessageAr,
    @SerializedName("SELF_SERVICE_AFTER_FEEDBACK_MESSAGE_EN")
    var selfServiceAfterFeedbackMessageEn: SelfServiceAfterFeedbackMessageEn,
    @SerializedName("SELF_SERVICE_CLIENT_CAN_CHANGE_STATUS")
    var selfServiceClientCanChangeStatus: SelfServiceClientCanChangeStatus,
    @SerializedName(("SELF_SERVICE_OTP_BY"))
    var selfServiceOtpBy: SelfServiceOtpBy,
    @SerializedName("SELF_SERVICE_SHOW_TERMS")
    var selfServiceShowTerms: SelfServiceShowTerms,
    @SerializedName("SELF_SERVICE_SIMILAR_TICKET")
    var selfServiceSimilarTicket: SelfServiceSimilarTicket,
    @SerializedName("SELF_SERVICE_TERMS_CONTENT")
    var selfServiceTermsContent: SelfServiceTermsContent,
    @SerializedName("SHOW_NO_CASE_AT_RATING")
    var showNoCaseAtRating: ShowNoCaseAtRating,
    @SerializedName("SOCIAL_MEDIA")
    var socialMedia: SocialMedia,
    @SerializedName("SelfServiceFeedbackQuestionnaire")
    var selfServiceFeedbackQuestionnaire: SelfServiceFeedbackQuestionnaire,
    @SerializedName("SelfServiceHospitalID")
    var selfServiceHospitalID: SelfServiceHospitalID,
    @SerializedName("SelfServiceOTPEnabled")
    var selfServiceOTPEnabled: SelfServiceOTPEnabled,
    @SerializedName("SelfServiceOTPExpirationTimePerMin")
    var selfServiceOTPExpirationTimePerMin: SelfServiceOTPExpirationTimePerMin,
    @SerializedName("SelfService_BackgroundImage")
    var selfServiceBackgroundImage: SelfServiceBackgroundImage,
    @SerializedName("SelfService_ContactUs_Display")
    var selfServiceContactUsDisplay: SelfServiceContactUsDisplay,
    @SerializedName("SelfService_ENABLED")
    var selfServiceEnabled: SelfServiceEnabled,
    @SerializedName("SelfServiceKBDisplay")
    var selfServiceKBDisplay: SelfServiceKBDisplay?= null,
    @SerializedName("THEME")
    var theme: THEME
)