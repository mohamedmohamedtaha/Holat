package com.holat.login.fragments

import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import coil.load
import com.holat.login.LoginActivity
import com.holat.login.R
import com.holat.login.base.BaseFragment
import com.holat.login.databinding.FragmentLoginWithIdBinding
import com.holat.login.viewmodels.LoginViewModel
import com.holat.login.models.sendotp.SendOtp
import com.holat.login.network.NetworkResult
import com.holat.login.utils.ApplicationException
import com.holat.login.utils.Constants
import com.holat.login.utils.Constants.NORMAL
import com.holat.login.utils.gotToSpecificActivity
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.safeNavigate
import com.holat.login.utils.showImage
import com.holat.login.utils.showSnackBar

import kotlinx.coroutines.launch

class LoginWithIdFragment : BaseFragment() {
    private lateinit var binding: FragmentLoginWithIdBinding

    private val loginViewModel by activityViewModels<LoginViewModel>()

    override fun getBinding(): ViewBinding {
        binding = FragmentLoginWithIdBinding.inflate(layoutInflater)
        showPermissionForNotification()
        return binding
    }
    override fun getData() {
        getServiceConfigData()

        reloadCaptcha()
        // setDefaultCountryCode(binding.CountryCodePicker)
//        requireActivity().onBackPressedDispatcher.addCallback(this,enabled = true){
//            findNavController().navigateUp()
//        }
    }

    private fun getServiceConfigData() {
        selfServiceConfigDatabaseViewModel.getServiceConfig()
        //Get data from local
        viewLifecycleOwner.lifecycleScope.launch {
            selfServiceConfigDatabaseViewModel.getServiceConfig.collect {
                if (it != null) {
                    selfServiceConfigDatabaseViewModel.nafathEnabled = it.nafathEnabled
                    binding.root.showImage(it.largeLogo, binding.includeSamaCare.logoSama)

                }
            }
        }
    }
    override fun onClick() {
        binding.constraintNafaz.onDebouncedListener {
//            val action = LoginWithIdFragmentDirections.actionLoginWithIdFragmentToLoginFragment()
//            findNavController().safeNavigate(action)
        }
        binding.loginBtn.onDebouncedListener {
            try {
//                if (binding.CountryCodePicker.selectedCountryNameCode == "") {
//                    binding.root.showSnackBar(getString(R.string.err_phone_number_not_correct))
//                    return@onDebouncedListener
//                }
                validator.apply {
                    submit(binding.includeCountryCodeWithPhone.phoneEt).checkEmpty()
                        .errorMessage(getString(R.string.err_phone_number_not_correct)).checkMinDigits(9)
                        .errorMessage(getString(R.string.err_phone_number_not_correct))
                        .check()
                    submit(binding.NationalIdEt).checkEmpty()
                        .errorMessage(getString(R.string.required)).checkMinDigits(10)
                        .errorMessage(getString(R.string.err_id_number_not_equal_10_numbers))
                        .check()
                    submit(binding.includeCaptcha.checkCodeEt).checkEmpty()
                        .errorMessage(getString(R.string.captcha_required)).check()
                }
                loginViewModel.nationalId = binding.NationalIdEt.text.toString().trim().toLong()
                loginViewModel.phoneNumber = binding.includeCountryCodeWithPhone.CountryCodePicker.selectedCountryCodeWithPlus + binding.includeCountryCodeWithPhone.phoneEt.text.toString().trim()
                loginViewModel.captchaCode =
                    binding.includeCaptcha.checkCodeEt.text.toString().trim()
                loginViewModel.rememberMy = binding.rememberMy.isChecked
                val sendOtp = SendOtp(
                    captchaCode = loginViewModel.captchaCode,
                    key = loginViewModel.keyCaptcha,
                    mobile = loginViewModel.phoneNumber,
                    nationalId = loginViewModel.nationalId,
                    rememberMy = loginViewModel.rememberMy
                )
                login(sendOtp)
            } catch (e: ApplicationException) {
                e.message?.let { binding.root.showSnackBar(it) }
            }
        }
        binding.includeCaptcha.reloadCaptcha.onDebouncedListener {
            reloadCaptcha()
        }
        binding.creatAccountBtn.onDebouncedListener {
            val action =
                LoginWithIdFragmentDirections.actionLoginWithIdFragmentToCreateAccountFragment()
            findNavController().safeNavigate(action)
        }
        binding.tvLanguage.setOnClickListener {
            val currentLanguage = dataStoreViewModel.getLanguage()
            if (currentLanguage == Constants.AR_LANGUAGE)
                changeLanguage(Constants.EN_LANGUAGE)
            else
                changeLanguage(Constants.AR_LANGUAGE)
        }
    }


    private fun changeLanguage(language: String) {
        dataStoreViewModel.saveLanguage(language)
        updateResources(requireContext(), language)
        requireActivity().gotToSpecificActivity(LoginActivity::class.java)
    }

    private fun login(
        sendOtp: SendOtp
    ) {
        loginViewModel.sendOtp(sendOtp)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                loginViewModel.sendOtp.collect {
                    when (it) {
                        is NetworkResult.Loading -> {
                            showProgressBar()
                        }

                        is NetworkResult.Success -> {
                            hideProgressBar()
                            //loginViewModel.keyCaptcha = it.data.key
                            binding.root.showSnackBar(it.data.message)
                            val action =
                                LoginWithIdFragmentDirections.actionLoginWithIdFragmentToOtpFragment(moveFrom = NORMAL)
                            findNavController().safeNavigate(action)

//                            binding.captcha.load(it.data.img) {
//                                crossfade(true)
//                                transformations(CircleCropTransformation())
//                                    .error(R.drawable.loading_animation)
//                            }

                        }

                        is NetworkResult.Error -> {
                            showError(it.code, it.responseBody)
                            reloadCaptcha()
                            hideProgressBar()
                        }

                        is NetworkResult.ErrorEX -> {
                            hideProgressBar()
                            // showError(it)
                            //reloadCaptcha()
                        }

                        is NetworkResult.Exception -> {
                            hideProgressBar()
                        }

                        else -> {

                        }
                    }
                }

            }
        }
    }

    private fun showPermissionForNotification() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requireActivity().requestPermissions(
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    105
                )
            }
        }
    }

    private fun reloadCaptcha() {
        binding.includeCaptcha.checkCodeEt.setText("")
        loginViewModel.reloadCaptcha()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.reloadCaptcha.collect {
                    when (it) {
                        is NetworkResult.Loading -> {
                            binding.includeCaptcha.captcha.load(R.drawable.loading_animation)
                        }

                        is NetworkResult.Success -> {
                            loginViewModel.keyCaptcha = it.data.key
                            binding.root.showImage(it.data.img, binding.includeCaptcha.captcha)

//                            binding.captcha.load(it.data.img) {
//                                crossfade(true)
//                                transformations(CircleCropTransformation())
//                                    .error(R.drawable.loading_animation)
//                            }

                        }

                        is NetworkResult.Exception -> {

                        }
                        is NetworkResult.Error -> {
                            showError(it.code, it.responseBody)
                            hideProgressBar()
                        }

                        else -> {

                        }
                    }
                }
            }
        }
    }
}