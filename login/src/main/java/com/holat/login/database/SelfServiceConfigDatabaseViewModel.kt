package com.holat.login.database

import androidx.lifecycle.viewModelScope
import com.holat.login.base.BaseViewModel
import com.holat.login.models.ServiceConfig
import com.holat.login.models.ServiceConfigDatabase
import com.holat.login.network.NetworkResult

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
Created by Mohamed Mohamed Taha on 12/23/2023
 */
@HiltViewModel
class SelfServiceConfigDatabaseViewModel @Inject constructor(private val selfServiceConfigDatabaseRepository: SelfServiceConfigDatabaseRepository) :
    BaseViewModel() {
    var nafathEnabled = "0"
    private val _getServiceConfig = Channel<ServiceConfigDatabase>()
//    private val _errorInDatabase = Channel<String>()
    val getServiceConfig get() = _getServiceConfig.receiveAsFlow()

    fun getServiceConfig() {
        viewModelScope.launch {
            selfServiceConfigDatabaseRepository.getServiceConfig().collect {
                _getServiceConfig.send(it)
            }
        }
    }
    private  suspend fun insertServiceConfig(serviceConfigDatabase: ServiceConfigDatabase):Long {
        return callDatabaseGeneric{ selfServiceConfigDatabaseRepository.insertServiceConfig(serviceConfigDatabase) }
    }

    private  suspend fun deleteData():Int {
        return callDatabaseGeneric{ selfServiceConfigDatabaseRepository.deleteData() }
    }

     suspend fun  setConfigService(it: NetworkResult.Success<ServiceConfig>) {
        val serviceConfigDatabase = ServiceConfigDatabase()
        val theme = it.data.theme
        serviceConfigDatabase.largeLogo = theme.LOGO.value ?: ""
        serviceConfigDatabase.smallLogo = theme.MINI_LOGO.value ?: ""
        serviceConfigDatabase.primaryColor = theme.HEADER_COLOR.value ?: ""
        serviceConfigDatabase.smallImage = theme.SMALL_IMAGE.value ?: ""
        serviceConfigDatabase.backgroundImage = it.data.selfServiceBackgroundImage.value ?: ""
        //------------------  about us /contact us Config ----------------------------
        // Copy Rights arabic value
        serviceConfigDatabase.copyRightAr = theme.COPY_RIGHT_AR.value ?: ""
        serviceConfigDatabase.copyRightEn =theme.COPY_RIGHT_EN.value ?: ""
        val contactUs = it.data.contactUs
        serviceConfigDatabase.unifiedNumber = contactUs.Unified_Number.value ?: ""
        serviceConfigDatabase.workingHours = contactUs.Working_Hours.value ?: ""
        serviceConfigDatabase.email = contactUs.Email.value ?: ""
        serviceConfigDatabase.mainSiteURl = it.data.chatBot.MAIN_SITE_URL.value ?: ""
        val socialMedia = it.data.socialMedia
        serviceConfigDatabase.facebookUrl = socialMedia.FACEBOOK_URL.value ?: ""
        serviceConfigDatabase.instagramUrl = socialMedia.INSTAGRAM_URL.value ?: ""
        serviceConfigDatabase.linkedinUrl = socialMedia.LINKEDIN_URL.value ?: ""
        serviceConfigDatabase.snapChatUrl = socialMedia.SNAPCHAT_URL.value ?: ""
        serviceConfigDatabase.tiktokUrl = socialMedia.TIKTOK_URL.value ?: ""
        serviceConfigDatabase.twitterUrl = socialMedia.TWITTER_URL.value ?: ""
        serviceConfigDatabase.youtubeURl = socialMedia.YOUTUBE_URL.value ?: ""
        serviceConfigDatabase.selfServiceKBDisplay = it.data.selfServiceKBDisplay?.value ?: ""
        serviceConfigDatabase.selfServiceContactUsDisplay = it.data.selfServiceContactUsDisplay.value ?: ""

        //  ------------------------- MAINTENANCE Config ----------------------------
        serviceConfigDatabase.maintenanceMode = it.data.maintenanceMode.value ?: ""

        serviceConfigDatabase.mobileDefaultCountry = it.data.mobileDefaultCountry.value ?: ""
//                        serviceConfigDatabase.mobileDefaultCountry = it.data.da
//                            configs.DatePickerMinimumDate.value
        serviceConfigDatabase.birthDateHijri = it.data.filesFeature.UPLOAD_FILES_FROM_SELFSERVICE.value ?: ""
        serviceConfigDatabase.minCharsOnDetails = it.data.minCharsOnDetails.value ?: ""

        //---------------------------- Login Config ----------------------------
        serviceConfigDatabase.nafathEnabled = it.data.nafathEnabled.value ?: ""
        serviceConfigDatabase.selfServiceOTPEnabled = it.data.selfServiceOTPEnabled.value ?: ""
        serviceConfigDatabase.selfServiceOTPExpirationTimePerMin = it.data.selfServiceOTPExpirationTimePerMin.value ?: ""

        //   ---------------------------- Feedback/Rating Configs ----------------------------
        serviceConfigDatabase.feedbackExpiredTimeInDays = it.data.feedbackExpiredTimeInDays.value ?: ""
        serviceConfigDatabase.selfServiceFeedbackQuestionnaire = it.data.selfServiceFeedbackQuestionnaire.value ?: ""
        serviceConfigDatabase.rateConfigId = it.data.rateConfigId.value ?: ""
         //For use this object in all app lifecycle
         ServiceConfigDatabase.serviceConfigDatabase = serviceConfigDatabase

         val deleteData = deleteData()
         if (deleteData == 1){
             val insert = insertServiceConfig(serviceConfigDatabase)
//             if ( insert > -1){
//                 getServiceConfig()
//             }else{
//                 // _errorInDatabase.("Error happened when We save data in database.")
//             }
         }else
             insertServiceConfig(serviceConfigDatabase)

    }

}