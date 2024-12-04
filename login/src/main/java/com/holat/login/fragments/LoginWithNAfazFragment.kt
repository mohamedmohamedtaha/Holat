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
import com.holat.login.R
import com.holat.login.databinding.FragmentLoginWithNafathBinding
import com.holat.login.base.BaseFragment
import com.holat.login.LoginActivity
import com.holat.login.viewmodels.LoginViewModel
import com.holat.login.models.nafath.Nafath
import com.holat.login.network.NetworkResult
import com.holat.login.utils.ApplicationException
import com.holat.login.utils.Constants
import com.holat.login.utils.gotToSpecificActivity
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.safeNavigate
import com.holat.login.utils.showImage
import com.holat.login.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginWithNAfazFragment : BaseFragment() {
    private lateinit var binding: FragmentLoginWithNafathBinding
    private val loginViewModel by activityViewModels<LoginViewModel>()


    override fun getBinding(): ViewBinding {
        binding = FragmentLoginWithNafathBinding.inflate(layoutInflater)
        return binding
    }

    override fun getData() {
        showPermissionForNotification()
        getServiceConfigData()

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
//        binding.constraintNafaz.onDebouncedListener {
//            val action = LoginWithNAfazFragmentDirections.actionLoginFragmentToLoginWithIdFragment()
//            findNavController().safeNavigate(action)
//        }
        binding.loginBtn.onDebouncedListener {
            try {
                validator.apply {
                    submit(binding.NationalIdEt).checkEmpty()
                        .errorMessage(getString(R.string.required)).checkMinDigits(10)
                        .errorMessage(getString(R.string.err_id_number_not_equal_10_numbers))
                        .check()
                }
                loginViewModel.nationalId = binding.NationalIdEt.text.toString().trim().toLong()
                val nafath = Nafath(
                    id = loginViewModel.nationalId.toString()
                )
                loginWithNafath(nafath)
            } catch (e: ApplicationException) {
                e.message?.let { binding.root.showSnackBar(it) }
            }
        }
        binding.tvLanguage.setOnClickListener {
            val currentLanguage = dataStoreViewModel.getLanguage()
            if (currentLanguage == Constants.AR_LANGUAGE)
                changeLanguage(Constants.EN_LANGUAGE)
            else
                changeLanguage(Constants.AR_LANGUAGE)
        }
    }
    private fun showPermissionForNotification(){
        if (ActivityCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                requireActivity().requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),105)
            }
        }
    }
    private fun loginWithNafath(
        nafath: Nafath
    ) {
        loginViewModel.nafath(nafath)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                loginViewModel.nafath.collect {
                    when (it) {
                        is NetworkResult.Loading -> {
                            showProgressBar()
                        }

                        is NetworkResult.Success -> {
                            loginViewModel.random = it.data.random
                            loginViewModel.transId = it.data.transId
                            hideProgressBar()
                            val action =
                                LoginWithNAfazFragmentDirections.actionLoginWithNafathFragmentToNafazCodeFragment()
                            findNavController().safeNavigate(action)
                        }

                        is NetworkResult.Error -> {
                            showError(it.code,it.responseBody)
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
                    }
                }
            }
        }
    }
    private fun changeLanguage(language: String) {
        dataStoreViewModel.saveLanguage(language)
        updateResources(requireContext(),language)
        requireActivity().gotToSpecificActivity(LoginActivity::class.java)
    }
}