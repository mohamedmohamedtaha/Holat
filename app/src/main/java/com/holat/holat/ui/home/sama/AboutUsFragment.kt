package com.holat.holat.ui.home.sama

import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.holat.holat.R
import com.holat.holat.databinding.FragmentAboutUsBinding
import com.holat.login.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AboutUsFragment : BaseFragment() {
    private lateinit var binding: FragmentAboutUsBinding

    override fun getBinding(): ViewBinding {
        binding = FragmentAboutUsBinding.inflate(layoutInflater)
        return binding
    }

    override fun getData() {
        toolbar.changeTextTitle(getString(R.string.about_us))
        toolbar.showSearchText(false)
        toolbar.showProfileImage(false)
        toolbar.showNotificationIcon(true)
    }

    override fun onClick() {
    }

    private fun getServiceConfigData() {
        selfServiceConfigDatabaseViewModel.getServiceConfig()
        //Get data from local
        viewLifecycleOwner.lifecycleScope.launch {
            selfServiceConfigDatabaseViewModel.getServiceConfig.collect {
                if (it != null) {
                    selfServiceConfigDatabaseViewModel.nafathEnabled = it.nafathEnabled

                }
            }
        }
    }
}