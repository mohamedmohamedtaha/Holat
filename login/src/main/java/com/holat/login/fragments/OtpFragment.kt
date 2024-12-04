package com.holat.login.fragments

import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.holat.login.R
import com.holat.login.databinding.FragmentOtpBinding
import com.holat.login.base.BaseFragment

import com.holat.login.viewmodels.CreateAccountViewModel
import com.holat.login.viewmodels.LoginViewModel
import com.holat.login.models.sendotp.SendOtp
import com.holat.login.models.verifyotp.VerifyOtp
import com.holat.login.network.NetworkResult
import com.holat.login.utils.ApplicationException
import com.holat.login.utils.Constants.NAFAZ
import com.holat.login.utils.Constants.NORMAL
import com.holat.login.utils.FinishListener
import com.holat.login.utils.GeneralTextWatcher
import com.holat.login.utils.Timer
import com.holat.login.utils.gotToMainActivity
import com.holat.login.utils.gotToSpecificActivity
import com.holat.login.utils.showKeyboard
import com.holat.login.utils.showSnackBar
import kotlinx.coroutines.launch
import javax.inject.Inject

class OtpFragment : BaseFragment() {
    private lateinit var binding: FragmentOtpBinding
    private val createAccountViewModel by viewModels<CreateAccountViewModel>()
    private val loginViewModel by activityViewModels<LoginViewModel>()
    private val args: OtpFragmentArgs by navArgs()

    @Inject
    lateinit var timer: Timer

    override fun getBinding(): ViewBinding {
        binding = FragmentOtpBinding.inflate(layoutInflater)
        return binding
    }

    private fun showPhoneNumber(phoneNumber: String) {
        if (phoneNumber != "")
            binding.tvPhone.text = phoneNumber
    }

    override fun onClick() {
        binding.confirmBtn.setOnClickListener {
            try {
                val otpCode =
                    binding.etNumber1.text.toString().trim() + binding.etNumber2.text.toString()
                        .trim() + binding.etNumber3.text.toString()
                        .trim() + binding.etNumber4.text.toString()
                        .trim() + binding.etNumber5.text.toString()
                        .trim() + binding.etNumber6.text.toString()
                        .trim()
                validator.apply {
                    submit(binding.etNumber1).checkEmpty()
                        .errorMessage(getString(R.string.required)).check()
                    submit(binding.etNumber2).checkEmpty()
                        .errorMessage(getString(R.string.required)).check()
                    submit(binding.etNumber3).checkEmpty()
                        .errorMessage(getString(R.string.required)).check()
                    submit(binding.etNumber4).checkEmpty()
                        .errorMessage(getString(R.string.required)).check()
                    submit(binding.etNumber5).checkEmpty()
                        .errorMessage(getString(R.string.required)).check()
                    submit(binding.etNumber6).checkEmpty()
                        .errorMessage(getString(R.string.required)).check()
                }
                val verifyOtp = VerifyOtp(
                    otpCode = otpCode,
                    rememberMe = loginViewModel.rememberMy,
                    mobile = loginViewModel.phoneNumber,
                    nationalId = loginViewModel.nationalId
                )
                if (args.moveFrom == NAFAZ) {
                    nafazVerifyMobile(otpCode = otpCode)
                } else if (args.moveFrom == NORMAL) {
                    verifyOtp(verifyOtp)
                }

            } catch (e: ApplicationException) {
                e.message?.let { binding.root.showSnackBar(it) }
            }

        }
        binding.tvResend.setOnClickListener {
            if (args.moveFrom == NAFAZ) {
                resendNafzSendVerifyCode(mobile = loginViewModel.phoneNumber, email = loginViewModel.email)
            } else if (args.moveFrom == NORMAL) {
                resendCode()
            }

        }
    }

    private fun resendCode() {
        binding.tvResend.isEnabled = false
        binding.tvResend.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
        timer.startCountdownTimer(binding.tvTimer)
        val sendOtp = SendOtp(
            captchaCode = loginViewModel.captchaCode,
            key = loginViewModel.keyCaptcha,
            mobile = loginViewModel.phoneNumber,
            nationalId = loginViewModel.nationalId,
            rememberMy = loginViewModel.rememberMy
        )
        resendOtp(sendOtp)
    }

    override fun getData() {
        showPhoneNumber(loginViewModel.phoneNumber)
        setTimer()
        addTextChangedListenerForEditTexts()
    }

    private fun addTextChangedListenerForEditTexts() {
        binding.etNumber1.requestFocus()
        binding.etNumber1.isFocusable = true
        binding.etNumber1.showKeyboard()

        binding.etNumber1.addTextChangedListener(
            GeneralTextWatcher(
                binding.etNumber1,
                binding.etNumber1,
                binding.etNumber2,
                binding.etNumber3,
                binding.etNumber4, binding.etNumber5, binding.etNumber6
            )
        )
        binding.etNumber2.addTextChangedListener(
            GeneralTextWatcher(
                binding.etNumber2,
                binding.etNumber1,
                binding.etNumber2,
                binding.etNumber3,
                binding.etNumber4, binding.etNumber5, binding.etNumber6
            )
        )
        binding.etNumber3.addTextChangedListener(
            GeneralTextWatcher(
                binding.etNumber3,
                binding.etNumber1,
                binding.etNumber2,
                binding.etNumber3,
                binding.etNumber4, binding.etNumber5, binding.etNumber6
            )
        )
        binding.etNumber4.addTextChangedListener(
            GeneralTextWatcher(
                binding.etNumber4,
                binding.etNumber1,
                binding.etNumber2,
                binding.etNumber3,
                binding.etNumber4, binding.etNumber5, binding.etNumber6
            )
        )
        binding.etNumber5.addTextChangedListener(
            GeneralTextWatcher(
                binding.etNumber5,
                binding.etNumber1,
                binding.etNumber2,
                binding.etNumber3,
                binding.etNumber4, binding.etNumber5, binding.etNumber6
            )
        )
        binding.etNumber6.addTextChangedListener(
            GeneralTextWatcher(
                binding.etNumber6,
                binding.etNumber1,
                binding.etNumber2,
                binding.etNumber3,
                binding.etNumber4, binding.etNumber5, binding.etNumber6
            )
        )
    }

    private fun verifyOtp(verifyOtp: VerifyOtp) {
        createAccountViewModel.verifyOtp(verifyOtp)
        viewLifecycleOwner.lifecycleScope.launch {
            createAccountViewModel.verifyOtp.collect {
                when (it) {
                    is NetworkResult.Loading -> {
                        showProgressBar()
                    }

                    is NetworkResult.Success -> {
                        hideProgressBar()
                        // save token
                        dataStoreViewModel.saveToken(it.data.token)
                        //dataStoreViewModel.saveUserData(it.data)
                        requireActivity().gotToMainActivity("MainActivity")
                    }

                    is NetworkResult.Error -> {
                        showError(it.code, it.responseBody)
                        hideProgressBar()
                    }

                    is NetworkResult.ErrorEX -> {
                        binding.root.showSnackBar(it.exception?.message.toString())
                        hideProgressBar()
                    }

                    else -> {
                        hideProgressBar()
                    }
                }
            }
        }
    }

    private fun nafazVerifyMobile(otpCode: String) {
        loginViewModel.nafazVerifyMobile(otpCode)
        viewLifecycleOwner.lifecycleScope.launch {
            loginViewModel.nafazVerifyMobile.collect {
                when (it) {
                    is NetworkResult.Loading -> {
                        showProgressBar()
                    }

                    is NetworkResult.Success -> {
                        hideProgressBar()
                        // save token
                      //  dataStoreViewModel.saveToken(it.data.token)
                        requireActivity().gotToMainActivity("MainActivity")
                    }

                    is NetworkResult.Error -> {
                        showError(it.code, it.responseBody)
                        hideProgressBar()
                    }

                    is NetworkResult.ErrorEX -> {
                        binding.root.showSnackBar(it.exception?.message.toString())
                        hideProgressBar()
                    }

                    else -> {
                        hideProgressBar()
                    }
                }
            }
        }
    }

    private fun setTimer() {
        timer = Timer(object : FinishListener {
            override fun finish() {
                if (isAdded) {
                    binding.tvResend.isEnabled = true
                    binding.tvResend.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.red
                        )
                    )
                }
                timer.stopCountdownTimer()
            }

        })
        timer.startCountdownTimer(binding.tvTimer)
    }

    private fun resendOtp(
        sendOtp: SendOtp
    ) {
        loginViewModel.sendOtp(sendOtp)
        viewLifecycleOwner.lifecycleScope.launch {
            loginViewModel.sendOtp.collect {
                when (it) {
                    is NetworkResult.Loading -> {
                        showProgressBar()
                    }

                    is NetworkResult.Success -> {
                        hideProgressBar()
                        binding.root.showSnackBar(it.data.message)
                    }

                    is NetworkResult.Error -> {
                        showError(it.code, it.responseBody)
                        hideProgressBar()
                    }

                    is NetworkResult.ErrorEX -> {
                        hideProgressBar()
                    }

                    is NetworkResult.Exception -> {
                        hideProgressBar()
                    }

                    null -> {
                    }
                }
            }
        }
    }

    private fun resendNafzSendVerifyCode(
        mobile: String, email: String
    ) {
        binding.tvResend.isEnabled = false
        binding.tvResend.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
        timer.startCountdownTimer(binding.tvTimer)

        loginViewModel.nafazSendVerifyCode(mobile,email)
        viewLifecycleOwner.lifecycleScope.launch {
            loginViewModel.nafazSendVerifyCode.collect {
                when (it) {
                    is NetworkResult.Loading -> {
                        showProgressBar()
                    }

                    is NetworkResult.Success -> {
                        hideProgressBar()
                        binding.root.showSnackBar(it.data.message)
                    }

                    is NetworkResult.Error -> {
                        showError(it.code, it.responseBody)
                        hideProgressBar()
                    }

                    is NetworkResult.ErrorEX -> {
                        hideProgressBar()
                    }

                    is NetworkResult.Exception -> {
                        hideProgressBar()
                    }

                    null -> {
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.stopCountdownTimer()
    }

}