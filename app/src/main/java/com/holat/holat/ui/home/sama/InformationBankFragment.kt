package com.holat.holat.ui.home.sama

import androidx.viewbinding.ViewBinding
import com.holat.holat.R
import com.holat.holat.databinding.FragmentInformationBankBinding
import com.holat.login.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationBankFragment : BaseFragment() {
    private lateinit var binding: FragmentInformationBankBinding

    override fun getBinding(): ViewBinding {
        binding = FragmentInformationBankBinding.inflate(layoutInflater)
        return binding
    }

    override fun getData() {
        toolbar.changeTextTitle(getString(R.string.information_bank))
        toolbar.showSearchText(false)
        toolbar.showProfileImage(false)
        toolbar.showNotificationIcon(false)
    }

    override fun onClick() {
    }

}