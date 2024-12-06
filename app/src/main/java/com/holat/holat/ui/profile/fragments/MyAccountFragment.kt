package com.holat.holat.ui.profile.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.holat.holat.R
import com.holat.holat.databinding.FragmentMyAccountBinding
import com.holat.holat.ui.home.activity.MainActivity
import com.holat.holat.ui.profile.viewmodel.ClientProfileViewModel
import com.holat.holat.ui.profile.viewmodel.LogoutViewModel
import com.holat.login.LoginActivity
import com.holat.login.base.BaseFragment
import com.holat.login.models.clientprofile.ClientProfile
import com.holat.login.network.NetworkResult
import com.holat.login.utils.Constants
import com.holat.login.utils.DialogUtil
import com.holat.login.utils.gotToSpecificActivity
import com.holat.login.utils.listener.NotificationListener
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.safeNavigate
import com.holat.login.utils.showSnackBar
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyAccountFragment : BaseFragment() {
    private lateinit var binding: FragmentMyAccountBinding
    private val logoutViewModel by viewModels<LogoutViewModel>()
    private val clientProfileViewModel by viewModels<ClientProfileViewModel>()

    override fun getBinding(): ViewBinding {
        binding = FragmentMyAccountBinding.inflate(layoutInflater)
        return binding
    }

    override fun onClick() {
        (requireActivity() as MainActivity).setNotificationListener(object : NotificationListener {
            override fun openNotificationPage() {
                val action = MyAccountFragmentDirections.actionMyAccountToNotificationsFragment()
                findNavController().safeNavigate(action)
            }

        })

        binding.tvLogOut.onDebouncedListener {
            DialogUtil.showMessageWithYesNoMaterialDesign(
                requireActivity(),
                getString(R.string.exit),
                getString(R.string.warning_exit_app)
            ) { _, _ ->
                logout()
            }

        }
        binding.tvContactUs.onDebouncedListener {
            val action = MyAccountFragmentDirections.actionMyAccountToContactUsFragment()
            findNavController().safeNavigate(action)
        }
        binding.tvChangeLanguage.onDebouncedListener {
            val action = MyAccountFragmentDirections.actionMyAccountToLanguageFragment()
            findNavController().safeNavigate(action)
        }
        binding.tvChangeData.onDebouncedListener {
            val action = MyAccountFragmentDirections.actionMyAccountToUpdateClientProfileFragment()
            findNavController().safeNavigate(action)
        }
    }

    private fun logout(
    ) {
        logoutViewModel.logout()
        viewLifecycleOwner.lifecycleScope.launch {
            logoutViewModel.logout.collect {
                when (it) {
                    is NetworkResult.Loading -> {
                        showProgressBar()
                    }

                    is NetworkResult.Success -> {
                        binding.root.showSnackBar(it.data.message)
                        dataStoreViewModel.deleteToken()
                        //  dataStoreViewModel.deleteUserData()
                        requireActivity().gotToSpecificActivity(LoginActivity::class.java)
                    }

                    is NetworkResult.Error -> {
                        showError(it.code, it.responseBody)
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

    private fun clientProfile(
    ) {
        clientProfileViewModel.clientProfile()
        viewLifecycleOwner.lifecycleScope.launch {
            clientProfileViewModel.clientProfile.collect {
                when (it) {
                    is NetworkResult.Loading -> {
                        showProgressBar()
                    }

                    is NetworkResult.Success -> {
                        hideProgressBar()
                        getClientProfile(it.data)
                    }

                    is NetworkResult.Error -> {
                        showError(it.code, it.responseBody)
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

    override fun getData() {
        toolbar.changeTextTitle(getString(R.string.title_my_account))
        toolbar.showSearchText(false)
        toolbar.showProfileImage(false)
        toolbar.showNotificationIcon(true)
        clientProfile()
    }

    private fun getClientProfile(data: ClientProfile) {
        binding.tvNameUser.text = data.name
        binding.tvGetPhone.text = data.mobile
        binding.tvCode.text = data.nationalId
        binding.tvGender.text = data.gender
        binding.tvDateOdBirth.text = data.birthDate
        binding.tvGetAddress.text = data.address

        val currentLanguage = Lingver.getInstance().getLanguage()
        if (currentLanguage == Constants.AR_LANGUAGE) {
            binding.tvType.text = data.nationalIdType.titleAr
        } else {
            binding.tvType.text = data.nationalIdType.titleEn
        }
    }
}