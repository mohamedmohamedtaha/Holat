package com.holat.holat.ui.home.compliants.fragment

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.holat.holat.R
import com.holat.holat.data.models.tickets.File
import com.holat.holat.databinding.FragmentCompliantDetailsBinding
import com.holat.holat.ui.home.compliants.adapter.CompliantFilesAdapter
import com.holat.login.base.BaseFragment
import com.holat.login.utils.Constants
import com.holat.login.utils.Formatter
import com.holat.login.utils.listener.ClickListener
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.safeNavigate
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompliantDetailsFragment : BaseFragment() {
    private lateinit var binding: FragmentCompliantDetailsBinding
    private val args: CompliantDetailsFragmentArgs by navArgs()

    override fun getBinding(): ViewBinding {
        binding = FragmentCompliantDetailsBinding.inflate(layoutInflater)
        return binding
    }

    override fun getData() {
        toolbar.changeTextTitle(getString(R.string.ticket_details))
        toolbar.showSearchText(false)
        toolbar.showNotificationIcon(false)
        toolbar.showProfileImage(false)
        showCompliantDetail(args)
    }

    private fun showCompliantDetail(args: CompliantDetailsFragmentArgs) {
        binding.tvDetails.text = args.dataTicket.details
        binding.tvDate.text = Formatter.format(
            args.dataTicket.created_at,
            Formatter.YYYY_MM_DD_HH_MM_SS,
            Formatter.EE_DD_MMMM_YYYY
        )
        binding.tvTime.text = Formatter.format(
            args.dataTicket.created_at,
            Formatter.YYYY_MM_DD_HH_MM_SS,
            Formatter.hh_mm_aa
        )
        binding.tvSectorReplay.text = args.dataTicket.sector_reply
        val currentLanguage = Lingver.getInstance().getLanguage()
        if (currentLanguage == Constants.AR_LANGUAGE) {
            binding.tvSectorValue.text = args.dataTicket.main_reason.title_ar
            binding.tvProductValue.text = args.dataTicket.sub_reason?.title_ar
            binding.tvPartyValue.text = args.dataTicket.hospital.title_ar
        } else {
            binding.tvSectorValue.text = args.dataTicket.main_reason.title_en
            binding.tvProductValue.text = args.dataTicket.sub_reason?.title_en
            binding.tvPartyValue.text = args.dataTicket.hospital.title_en
        }

        if (args.dataTicket.files.isNotEmpty()) {
            val adapter = CompliantFilesAdapter(object : ClickListener<File> {
                override fun onClick(view: View, t: File) {
                    //Download file
                }

            })
            binding.recyclerViewFiles.visibility = View.VISIBLE
            binding.recyclerViewFiles.adapter = adapter
            adapter.submitList(args.dataTicket.files)
        } else {
            binding.recyclerViewFiles.visibility = View.GONE
        }

    }

    override fun onClick() {
        binding.iconMessage.onDebouncedListener {
            val action =
                CompliantDetailsFragmentDirections.actionTicketDetailsFragmentToResponsesFragment(
                    args.dataTicket
                )
            findNavController().safeNavigate(action)
        }
    }
}