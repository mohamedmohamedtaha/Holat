package com.holat.holat.ui.home.sama

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.holat.holat.R
import com.holat.holat.databinding.FragmentSamaBinding
import com.holat.holat.ui.home.activity.MainActivity
import com.holat.login.base.BaseFragment
import com.holat.login.utils.listener.NotificationListener
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SamaFragment : BaseFragment() {
    private lateinit var binding: FragmentSamaBinding

    override fun getBinding(): ViewBinding {
        binding = FragmentSamaBinding.inflate(layoutInflater)
        return binding
    }

    override fun getData() {
        toolbar.changeTextTitle(getString(R.string.title_sama))
        toolbar.showSearchText(false)
        toolbar.showProfileImage(false)
        toolbar.showNotificationIcon(true)
        getServiceConfigData()

    }

    private fun whoAreWe() {
        binding.whoAreWe.tvHeader.text = getString(R.string.who_are_we)
        binding.whoAreWe.image.setBackgroundDrawable(
            ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.bg_bottom_dialog_green
            )
        )
        binding.whoAreWe.image.setImageResource(R.drawable.sama)
        binding.whoAreWe.cardView.onDebouncedListener {
            val action = SamaFragmentDirections.actionSamaFragmentToAboutUsFragment()
            findNavController().safeNavigate(action)
        }
    }

    private fun informationBank() {
        binding.informationBank.tvHeader.text = getString(R.string.information_bank)
        binding.informationBank.image.setBackgroundDrawable(
            ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.bg_bottom_dialog_green
            )
        )
        binding.informationBank.image.setImageResource(R.drawable.info)
        binding.informationBank.cardView.onDebouncedListener {
            val action = SamaFragmentDirections.actionSamaFragmentToInformationBankFragment()
            findNavController().safeNavigate(action)
        }

    }

    private fun contactUs() {
        binding.contactUs.tvHeader.text = getString(R.string.contact_us)
        binding.contactUs.image.setBackgroundDrawable(
            ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.bg_bottom_dialog_green
            )
        )
        binding.contactUs.image.setImageResource(R.drawable.phone)
        binding.contactUs.cardView.onDebouncedListener {
            val action = SamaFragmentDirections.actionSamaFragmentToContactUsFragment()
            findNavController().safeNavigate(action)
        }

    }

    private fun getServiceConfigData() {
        selfServiceConfigDatabaseViewModel.getServiceConfig()
        //Get data from local
        viewLifecycleOwner.lifecycleScope.launch {
            selfServiceConfigDatabaseViewModel.getServiceConfig.collect {
                if (it != null) {
                    whoAreWe()

                    if (it.selfServiceKBDisplay == "1")
                        informationBank()
                    else
                        binding.informationBank.cardView.visibility = View.GONE

                    if (it.selfServiceContactUsDisplay == "0")
                        contactUs()
                    else
                        binding.contactUs.cardView.visibility = View.GONE
                }
            }
        }
    }

    override fun onClick() {
        (requireActivity() as MainActivity).setNotificationListener(object : NotificationListener {
            override fun openNotificationPage() {
                val action = SamaFragmentDirections.actionSamaFragmentToNotificationsFragment()
                findNavController().safeNavigate(action)
            }

        })
    }

}