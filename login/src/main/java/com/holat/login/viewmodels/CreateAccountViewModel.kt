package com.holat.login.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holat.login.models.createaccount.Response
import com.holat.login.models.login.ResponseLogin
import com.holat.login.models.verifyotp.VerifyOtp
import com.holat.login.network.NetworkResult
import com.holat.login.network.repository.LoginRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
Created by Mohamed Mohamed Taha on 12/10/2023
 */
@HiltViewModel
class CreateAccountViewModel @Inject constructor(private val loginRepository: LoginRepository):ViewModel() {
    var nationalIdTypeId:Short =-1
    private val _createAccount = Channel<NetworkResult<Response>>()
    val createAccount get() = _createAccount.receiveAsFlow()
    fun createAccount( key: String,
                       captchaCode: String,
                       nationalId: Long,
                       name: String,
                       mobile: String,
                       email: String,
                       birthDay: String,
                       nationalIdTypeId: Short){
        viewModelScope.launch {
            _createAccount.send(NetworkResult.Loading())
            loginRepository.createAccount(key, captchaCode, nationalId, name, mobile, email,birthDay,nationalIdTypeId).collect{
                _createAccount.send(it)
            }
        }
    }
//    private val _sendOtp = Channel<NetworkResult<ResponseSendOtp>>()
//    val sendOtp get() = _sendOtp.receiveAsFlow()
//    fun sendOtp(sendOtp: SendOtp){
//        viewModelScope.launch {
//            loginRepository.sendOtp(sendOtp).collect{
//                _sendOtp.send(it)
//            }
//        }
//    }
    private val _verifyOtp = Channel<NetworkResult<ResponseLogin>>()
    val verifyOtp get() = _verifyOtp.receiveAsFlow()
    fun verifyOtp(verifyOtp: VerifyOtp){
        viewModelScope.launch {
            _verifyOtp.send(NetworkResult.Loading())
            loginRepository.verifyOtp(verifyOtp).collect{
                _verifyOtp.send(it)
            }
        }
    }
}
