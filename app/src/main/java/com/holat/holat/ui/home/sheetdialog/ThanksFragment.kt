package com.holat.holat.ui.home.sheetdialog

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.holat.holat.R
import com.holat.holat.databinding.FragmentThanksBinding
import com.holat.login.base.BaseBottomSheetDialogFragment
import com.holat.login.utils.onDebouncedListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThanksFragment : BaseBottomSheetDialogFragment() {
    private lateinit var binding: FragmentThanksBinding
    private val args: ThanksFragmentArgs by navArgs()
    override fun getBinding(): ViewBinding {
        binding = FragmentThanksBinding.inflate(layoutInflater)
        return binding
    }

    override fun getData() {
        if (args.ticketNumber != "") {
            binding.ticketNumber.text = args.ticketNumber
        }
    }

    override fun onClick() {
        binding.mainHomeBtn.onDebouncedListener {
            findNavController().popBackStack(R.id.main_home, false)
        }
    }
}