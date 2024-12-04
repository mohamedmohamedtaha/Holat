package com.holat.login.fragments

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.holat.login.R
import com.holat.login.databinding.FragmentNafathSendVerifyCodeBinding
import com.holat.login.base.BaseFragment
import com.holat.login.viewmodels.LoginViewModel
import com.holat.login.network.NetworkResult
import com.holat.login.utils.Constants.NAFAZ
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.safeNavigate
import com.holat.login.utils.showSnackBar
import com.holat.login.utils.ApplicationException

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NafazSendVerifyCodeFragment : BaseFragment() {
    private lateinit var binding: FragmentNafathSendVerifyCodeBinding
    private val loginViewModel by activityViewModels<LoginViewModel>()

    override fun getBinding(): ViewBinding {
        binding = FragmentNafathSendVerifyCodeBinding.inflate(layoutInflater)
        return binding
    }

    override fun getData() {

    }

    override fun onClick() {
        binding.SendVerifyCodeBtn.onDebouncedListener {
            try {
                validator.apply {
                    submit(binding.includeCountryCodeWithPhone.phoneEt).checkEmpty()
                        .errorMessage(getString(R.string.err_phone_number_not_correct)).checkMinDigits(9)
                        .errorMessage(getString(R.string.err_phone_number_not_correct))
                        .check()
                    submit(binding.emailEt).checkEmpty()
                        .errorMessage(getString(R.string.error_email)).checkValidEmail().errorMessage(getString(R.string.error_email)).check()
                }

                loginViewModel.phoneNumber = binding.includeCountryCodeWithPhone.phoneEt.text.toString().trim()
                loginViewModel.email = binding.emailEt.text.toString().trim()
                nafathSendVerifyCode(
                    mobile = binding.includeCountryCodeWithPhone.CountryCodePicker.selectedCountryCodeWithPlus + loginViewModel.phoneNumber,
                    email = loginViewModel.email
                )
            } catch (e: ApplicationException) {
                e.message?.let { binding.root.showSnackBar(it) }
            }

        }
    }
    private fun nafathSendVerifyCode(
        mobile: String,
        email: String
    ) {
        loginViewModel.nafazSendVerifyCode(mobile = mobile, email = email)
        viewLifecycleOwner.lifecycleScope.launch {
            loginViewModel.nafazSendVerifyCode.collect {
                when (it) {
                    is NetworkResult.Loading -> {
                        showProgressBar()
                    }

                    is NetworkResult.Success -> {
                        hideProgressBar()
                        binding.root.showSnackBar(it.data.message)
                        val action = NafazSendVerifyCodeFragmentDirections.actionNafathSendVerifyCodeFragmentToOtpFragment(moveFrom = NAFAZ)
                        findNavController().safeNavigate(action)
                    }

                    is NetworkResult.Error -> {
                        hideProgressBar()
                        showError(it.code, it.responseBody)

                    }

                    is NetworkResult.ErrorEX -> {
                        hideProgressBar()
                        binding.root.showSnackBar(it.exception?.message.toString())
                    }

                    else -> {
                        hideProgressBar()
                    }
                }
            }
        }
    }
}