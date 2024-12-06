package com.holat.holat.ui.home.responses.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.holat.holat.R
import com.holat.holat.databinding.FragmentSendCommentBinding
import com.holat.holat.ui.home.adapter.UploadImageOrFileAdapter
import com.holat.holat.ui.home.compliants.viewmodel.CompliantViewModel
import com.holat.login.base.BaseBottomSheetDialogFragment
import com.holat.login.models.UploadImageOrFile
import com.holat.login.sheetdialog.UploadFileOrImageFragment
import com.holat.login.utils.DialogUtil
import com.holat.login.utils.listener.ClickListener
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendCommentFragment : BaseBottomSheetDialogFragment() {
    private lateinit var binding: FragmentSendCommentBinding
    private val compliantViewModel by viewModels<CompliantViewModel>()
    private var files: ArrayList<String> = arrayListOf()
    private lateinit var uploadImageOrFileAdapter: UploadImageOrFileAdapter

    override fun getBinding(): ViewBinding {
        binding = FragmentSendCommentBinding.inflate(layoutInflater)
        return binding
    }

    override fun getData() {
        isCancelable = true
    }

    override fun onClick() {
        binding.includeAttachFile.selectedFileBtn.onDebouncedListener {
            if (compliantViewModel.dataTicket.size < 5) {
                pickImage(object : UploadFileOrImageFragment.ImagePickerResult {

                    override fun onResult(data: UploadImageOrFile?) {
                        files = arrayListOf()
                        if (data != null) {
                            // val uploadImageOrFile = UploadImageOrFile()
                            // uploadImageOrFile.fileName = data.fileName
                            compliantViewModel.dataTicket.add(data)
                        }
                        if (compliantViewModel.dataTicket.isNotEmpty()) {

                            compliantViewModel.dataTicket.forEach { data ->
                                files.add(data.fileName)
                            }
                            binding.recyclerViewAttachFiles.visibility = View.VISIBLE
                            uploadImageOrFileAdapter =
                                UploadImageOrFileAdapter(object : ClickListener<Int> {
                                    override fun onClick(view: View, t: Int) {
                                        DialogUtil.showMessageWithYesNoMaterialDesign(
                                            requireActivity(),
                                            getString(R.string.exit),
                                            getString(R.string.do_you_want_remove)
                                        ) { _, _ ->
                                            compliantViewModel.dataTicket.removeAt(t)
                                            uploadImageOrFileAdapter.notifyItemRemoved(t)
                                        }
                                    }
                                })
                            binding.recyclerViewAttachFiles.adapter = uploadImageOrFileAdapter
                            uploadImageOrFileAdapter.submitList(compliantViewModel.dataTicket)
                        } else {
                            binding.recyclerViewAttachFiles.visibility = View.GONE
                        }
                    }
                })
            } else {
                binding.root.showSnackBar(getString(R.string.you_excced_max_limit))
            }
        }
    }

}