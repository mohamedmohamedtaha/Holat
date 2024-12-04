package com.holat.login.fragments

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.holat.login.databinding.FragmentSplashBinding
import com.holat.login.base.BaseFragment
import com.holat.login.utils.gotToMainActivity
import com.holat.login.utils.gotToSpecificActivity
import com.holat.login.utils.safeNavigate
import com.holat.login.utils.showImage

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SplashFragment : BaseFragment() {
    private lateinit var binding: FragmentSplashBinding
    override fun getBinding(): ViewBinding {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        getServiceConfigData()
        return binding
    }

    override fun getData() {
    }

    private fun getServiceConfigData() {
        selfServiceConfigDatabaseViewModel.getServiceConfig()
        //Get data from local
        viewLifecycleOwner.lifecycleScope.launch {
            selfServiceConfigDatabaseViewModel.getServiceConfig.collect {
                if (it != null) {
                    selfServiceConfigDatabaseViewModel.nafathEnabled = it.nafathEnabled
                    binding.root.showImage(it.largeLogo, binding.logoSama)
                    withContext(Dispatchers.IO) {
                        Thread.sleep(1500)
                    }
                    getToken()

                }
            }
        }
    }

    private fun getToken() {
        dataStoreViewModel.getToken()
        dataStoreViewModel.getToken.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                requireActivity().gotToMainActivity("MainActivity")
            } else {
             //   if (selfServiceConfigDatabaseViewModel.nafathEnabled == "1") {
//                    val normalLogin =
//                        SplashFragmentDirections.actionSplashFragmentToLoginWithNafathFragment()
//                    findNavController().safeNavigate(normalLogin)
//                } else {
                    val nafazLogin =
                        SplashFragmentDirections.actionSplashFragmentToLoginWithIdFragment()
                    findNavController().safeNavigate(nafazLogin)
//                }
            }
        }
    }


    override fun onClick() {
        //TODO("Not yet implemented")
    }

}