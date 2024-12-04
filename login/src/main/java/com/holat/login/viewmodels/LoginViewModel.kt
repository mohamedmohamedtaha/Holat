package com.holat.login.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holat.login.models.captcha.Captcha
import com.holat.login.models.createaccount.Response
import com.holat.login.models.createaccount.ResponseSendOtp
import com.holat.login.models.login.ResponseLogin
import com.holat.login.models.nafath.Nafath
import com.holat.login.models.nafath.NafathStatus
import com.holat.login.models.nafath.ResponseNafath
import com.holat.login.models.nafath.responsenafathstatus.ResponseNafathStatus
import com.holat.login.models.nationaltypes.NationalTypes
import com.holat.login.models.sendotp.SendOtp
import com.holat.login.network.NetworkResult
import com.holat.login.network.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
Created by Mohamed Mohamed Taha on 07/11/2023
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {
    var keyCaptcha = ""
    var captchaCode = ""
    var rememberMy = false
    var phoneNumber = ""
    var email = ""
    var nationalId = 0L
    var random = ""
    var transId = ""
    private val _getAllNationalTypes = Channel<NetworkResult<NationalTypes>>()
    val getAllNationalTypes get() = _getAllNationalTypes.receiveAsFlow()
    fun getAllNationalTypes() {
        viewModelScope.launch {
            loginRepository.getAllNationalTypes().collect {
                _getAllNationalTypes.send(it)
            }
        }
    }

    private val _reloadCaptcha = Channel<NetworkResult<Captcha>>()
    val reloadCaptcha get() = _reloadCaptcha.receiveAsFlow()
    fun reloadCaptcha() {
        viewModelScope.launch {
            _reloadCaptcha.send(NetworkResult.Loading())
            _reloadCaptcha.send(NetworkResult.Loading())
            loginRepository.reloadCaptcha().collect {
                _reloadCaptcha.send(it)
            }
        }
    }


    private val _sendOtp = Channel<NetworkResult<ResponseSendOtp>>()
    val sendOtp get() = _sendOtp.receiveAsFlow()

    //Job for protect from concurrent callers
    private var job: Job? = null
    fun sendOtp(sendOtp: SendOtp) {
        if (job != null) return
        job = viewModelScope.launch {
            _sendOtp.send(NetworkResult.Loading())
//            loginRepository.sendOtp(sendOtp).collect{
//                _sendOtp.send(it)
//            }
            try {
//                val response  = loginRepository.sendOtp(sendOtp)
//                _sendOtp.update { response }

                loginRepository.sendOtp(sendOtp).collect {
                    _sendOtp.send(it)
                }
            } catch (e: Exception) {
                // _sendOtp.update { e }
            } finally {
                job = null
            }
        }
    }

    private val _nafath = MutableStateFlow<NetworkResult<ResponseNafath>>(NetworkResult.Loading())
    val nafath get() = _nafath.asStateFlow()
    fun nafath(nafath: Nafath) {
        if (job != null) return

        job = viewModelScope.launch {
            try {
                loginRepository.nafath(nafath).collect {
                    _nafath.value = it
                }
            } catch (e: Exception) {
                // _sendOtp.update { e }
            } finally {
                job = null
                // _nafath.value = NetworkResult.Success(ResponseNafath("",""))
            }
        }

//        viewModelScope.launch {
//            _nafath.send(NetworkResult.Loading())
//            loginRepository.nafath(nafath).collect{
//                _nafath.send(it)
//            }
//        }
    }

    private val _nafathStatus = Channel<NetworkResult<ResponseNafathStatus>>()
    val nafathStatus get() = _nafathStatus.receiveAsFlow()
    fun nafathStatus(nafathStatus: NafathStatus) {
        viewModelScope.launch {
            loginRepository.nafathStatus(nafathStatus).collect {
                _nafathStatus.send(it)
            }
        }
    }

    private val _nafazSendVerifyCode = Channel<NetworkResult<Response>>()
    val nafazSendVerifyCode get() = _nafazSendVerifyCode.receiveAsFlow()

    //Job for protect from concurrent callers
    // private var job: Job? = null
    fun nafazSendVerifyCode(mobile: String, email: String) {
        if (job != null) return
        job = viewModelScope.launch {
            _nafazSendVerifyCode.send(NetworkResult.Loading())
//            loginRepository.sendOtp(sendOtp).collect{
//                _sendOtp.send(it)
//            }
            try {
//                val response  = loginRepository.sendOtp(sendOtp)
//                _sendOtp.update { response }

                loginRepository.nafazSendVerifyCode(mobile, email).collect {
                    _nafazSendVerifyCode.send(it)
                }
            } catch (e: Exception) {
                // _sendOtp.update { e }
            } finally {
                job = null
            }
        }
    }

    private val _nafazVerifyMobile = Channel<NetworkResult<ResponseLogin>>()
    val nafazVerifyMobile get() = _nafazVerifyMobile.receiveAsFlow()

    //Job for protect from concurrent callers
    // private var job: Job? = null
    fun nafazVerifyMobile(otpCode: String) {
        if (job != null) return
        job = viewModelScope.launch {
            _nafazVerifyMobile.send(NetworkResult.Loading())
//            loginRepository.sendOtp(sendOtp).collect{
//                _sendOtp.send(it)
//            }
            try {
//                val response  = loginRepository.sendOtp(sendOtp)
//                _sendOtp.update { response }

                loginRepository.nafazVerifyMobile(otpCode = otpCode).collect {
                    _nafazVerifyMobile.send(it)
                }
            } catch (e: Exception) {
                // _sendOtp.update { e }
            } finally {
                job = null
            }
        }
    }
}