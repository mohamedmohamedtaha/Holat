package com.holat.holat.ui.home.notifications

import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.holat.holat.databinding.FragmentNotificationDetailsBinding
import com.holat.login.base.BaseFragment
import com.holat.login.utils.Formatter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationDetailsFragment : BaseFragment() {
    private lateinit var binding: FragmentNotificationDetailsBinding
    private val args: NotificationDetailsFragmentArgs by navArgs()

    override fun getBinding(): ViewBinding {
        binding = FragmentNotificationDetailsBinding.inflate(layoutInflater)
        return binding
    }

    override fun getData() {
        toolbar.showSearchText(false)
        toolbar.showProfileImage(false)
        toolbar.showNotificationIcon(false)
        notificationDetails(args)

    }

    private fun notificationDetails(detailsFragmentArgs: NotificationDetailsFragmentArgs) {
        binding.tvDetails.text = detailsFragmentArgs.dataTicket.details
        binding.tvGetDate.text = Formatter.format(
            detailsFragmentArgs.dataTicket.created_at,
            Formatter.YYYY_MM_DD_HH_MM_SS,
            Formatter.EE_DD_MMMM_YYYY
        )
        binding.tvTime.text = Formatter.format(
            detailsFragmentArgs.dataTicket.created_at,
            Formatter.YYYY_MM_DD_HH_MM_SS,
            Formatter.hh_mm_aa
        )
    }

    override fun onClick() {

    }

}