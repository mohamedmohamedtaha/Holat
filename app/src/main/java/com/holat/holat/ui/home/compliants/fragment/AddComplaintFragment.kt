package com.holat.holat.ui.home.compliants.fragment

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.holat.holat.R
import com.holat.holat.data.models.compliant.mainreasons.MainReasons
import com.holat.holat.data.models.fields.Field
import com.holat.holat.databinding.FragmentAddComplaintBinding
import com.holat.holat.ui.dynamic.enums.ViewType
import com.holat.holat.ui.dynamic.fragments.LookUpSearchFragment_FRAGMENT_RESULT_KEY
import com.holat.holat.ui.dynamic.models.GlobalView
import com.holat.holat.ui.dynamic.views.CustomEditText
import com.holat.holat.ui.dynamic.views.CustomSelectEditText
import com.holat.holat.ui.home.adapter.UploadImageOrFileAdapter
import com.holat.holat.ui.home.compliants.viewmodel.CompliantViewModel
import com.holat.holat.ui.home.sheetdialog.LookUpSearchFragment_FRAGMENT_DATA
import com.holat.login.base.BaseFragment
import com.holat.login.models.LookUpModel
import com.holat.login.models.UploadImageOrFile
import com.holat.login.network.NetworkResult
import com.holat.login.sheetdialog.UploadFileOrImageFragment
import com.holat.login.utils.ApplicationException
import com.holat.login.utils.Constants
import com.holat.login.utils.DialogUtil
import com.holat.login.utils.listener.ClickListener
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.safeNavigate
import com.holat.login.utils.showSnackBar
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.util.concurrent.CopyOnWriteArrayList

 const val MAIN_REASON_ID = "main_reason_id"
 const val HOSPITAL_ID = "hospital_id"
 const val DETAILS = "details"
const val ALL_TICKET_TYPES="all-ticket-types"
const val SUB_REASON_ID = " sub_reason_id"
const val SUB_SUB_REASON_ID = "sub_sub_reason_id"
const val SUB_SUB_SUB_REASON_ID = "sub_sub_sub_reason_id"
const val COUNTRY_ID = "country_id"
const val REGION_ID = " region_id"
const val CITY_ID = " city_id"


@AndroidEntryPoint
class AddComplaintFragment : BaseFragment() {
    private lateinit var binding: FragmentAddComplaintBinding
    private val compliantViewModel by activityViewModels<CompliantViewModel>()
    private lateinit var uploadImageOrFileAdapter: UploadImageOrFileAdapter
    private val currentLanguage = Lingver.getInstance().getLanguage()
    private val customEditText = CustomEditText()
    private var ticketTypeId = -1
    private var mainReasonId = -1
    private var hospitalId = -1
    private var subSubReasonId = -1
    private var subReasonId = -1
    private val customSelectEditText = CustomSelectEditText()
    private var globalAllQuestions = CopyOnWriteArrayList<GlobalView>()
    private suspend fun drawSelectEditText(field: Field) {
        val initGlobalView = GlobalView()
        val editTextView = customSelectEditText.drawSelectEditTextOnView(
            activity = requireActivity(),
            field = field,
            globalView = initGlobalView,
            globalViewListener = { globalView ->
                setFragmentListenerForLookUpsFragment(
                    globalView = globalView
                )
                val id = if (globalView.tablesModel.value.isEmpty()) 0 else globalView.tablesModel.value.toInt()
                val action =
                    AddComplaintFragmentDirections.actionComplaintFragmentToLookUpSearchFragment2(
                        id = id,
                        dependedOn = field.field
                    )
                findNavController().safeNavigate(action)

            }, drawView = { data ->
                if (data.editTextView != null) {
                    withContext(Dispatchers.Main) {
                        binding.linearLayoutCompat.addView(data.editTextView?.title)
                        binding.linearLayoutCompat.addView(data.editTextView?.typeView)
                    }
                }

            })
        globalAllQuestions.add(editTextView)
    }

//    //    private var files: ArrayList<UploadImageOrFile> = arrayListOf()
//    private fun drawSelectEditText(mainReasons: MainReasons) {
//        var customView = CustomView<EditText>()
//        customView = customEditText.drawEditTextOnView(
//            mainData = mainReasons,
//            activity = requireActivity(),
//            data = arrayListOf<String>(),
//            checkRuleMovementListener = object : SelectListener<CustomAttributes> {
//                override fun onClick(t: CustomAttributes) {
////                        setFragmentListenerForLookUpsFragment(
////                            customView = customView
////                        )
//                    val yesValues = ArrayList<String>()
//                    val action =
//                        AddComplaintFragmentDirections.actionComplaintFragmentToLookUpSearchFragment(
////                                lookUpId = mainData.questLookUpId.toInt(),
////                                lookUpRef = yesValues.toTypedArray(),
////                                questCode = ""
//                        )
//                    findNavController().safeNavigate(action)
//
//                }
//
//
//            })
//        binding.linearLayoutCompat.addView(customView.title)
//        binding.linearLayoutCompat.addView(customView.typeView)
//        addDataToGlobalArrayEditText(customView, mainData = mainReasons)
//    }
//
//    private fun addDataToGlobalArrayEditText(
//        customView: CustomView<EditText>,
//        mainData: MainReasons,
//    ) {
//        val global = GlobalView()
//        global.editTextView = customView
//        //  global.tablesModel = mainData
//        global.customAttributes = customView.customAttributes
//        global.title = customView.title
//        // globalAllQuestions.add(global)
//    }

    override fun getBinding(): ViewBinding {
        binding = FragmentAddComplaintBinding.inflate(layoutInflater)
        return binding
    }

    override fun getData() {
        toolbar.changeTextTitle(getString(R.string.submit_complaint))
        toolbar.showSearchText(false)
        toolbar.showProfileImage(false)
        toolbar.showNotificationIcon(false)
        //   if (compliantViewModel.notCallSpinnerLotOfTime){
        getFields()
        //  }
    }

    override fun onClick() {
        binding.includeAttachFile.selectedFileBtn.onDebouncedListener {
            if (compliantViewModel.dataTicket.size < 5) {
                pickImage(object : UploadFileOrImageFragment.ImagePickerResult {
                    override fun onResult(data: UploadImageOrFile?) {
                        if (data != null) {
                            compliantViewModel.dataTicket.add(data)
                        }
                        if (compliantViewModel.dataTicket.isNotEmpty()) {

//                            compliantViewModel.dataTicket.forEach { data->
//                                files.add(data)
//                            }
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
        binding.SendBtn.onDebouncedListener {
            try {
                validator.apply {
                    if (ticketTypeId == -1 || mainReasonId == -1 || subReasonId == -1 || subSubReasonId == -1 || hospitalId == -1) {
                        binding.root.showSnackBar(getString(R.string.required))
                        return@onDebouncedListener
                    }
                    submit(binding.detailsEt).checkEmpty()
                        .errorMessage(getString(R.string.required))
                        .check()
                }
                //                    ticketRequestModel.setDescription(msgTv.getText().toString());
                //  disposable = remoteDataSourceImp.submitTicket(ticketRequestModel);
                val body = MultipartBody.Builder().setType(MultipartBody.FORM)
                body.addFormDataPart("ticket_type_id", ticketTypeId.toString())
                body.addFormDataPart("main_reason_id", mainReasonId.toString())
                body.addFormDataPart("hospital_id", hospitalId.toString())
                body.addFormDataPart("sub_sub_reason_id", subSubReasonId.toString())
                body.addFormDataPart("sub_reason_id", subReasonId.toString())
                body.addFormDataPart("details", binding.detailsEt.text.toString().trim())
                compliantViewModel.dataTicket.forEach { data ->
                    if (data.contentType == "application/pdf") {
                        data.fileData?.asRequestBody("application/pdf".toMediaTypeOrNull())
                            ?.let { it1 ->
                                body.addFormDataPart(
                                    "files[]", data.fileName,
                                    it1
                                )
                            }
                    } else {
                        data.fileData?.asRequestBody("image/*".toMediaTypeOrNull())?.let { it1 ->
                            body.addFormDataPart(
                                "files[]", data.fileName,
                                it1
                            )
                        }
                    }
                }
//                val storeTicket = StoreTicket(
//                    ticketTypeId = ticketTypeId,
//                    mainReasonId = mainReasonId,
//                    hospitalId = hospitalId,
//                    subSubReasonId = subSubReasonId,
//                    subReasonId = subReasonId,
//                    details = binding.detailsEt.text.toString().trim(), files = files
//                )
                val requestBody: RequestBody = body.build()

                storeTicket(requestBody)
            } catch (e: ApplicationException) {
                e.message?.let { binding.root.showSnackBar(it) }
            }

        }
    }

    private fun storeTicket(requestBody: RequestBody) {
        compliantViewModel.storeTicket(requestBody)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                compliantViewModel.storeTicket.collect {
                    when (it) {
                        is NetworkResult.Loading -> {
                            showProgressBar()
                        }

                        is NetworkResult.Success -> {
                            hideProgressBar()
                            val action =
                                AddComplaintFragmentDirections.actionComplaintFragmentToThanksFragment(
                                    ticketNumber = it.data.ticket_number.toString()
                                )
                            findNavController().safeNavigate(action)
                        }

                        is NetworkResult.Error -> {
                            showError(it.code, it.responseBody)
                            hideProgressBar()
                        }

                        is NetworkResult.ErrorEX -> {
                            binding.root.showSnackBar(it.exception?.message.toString())
                            hideProgressBar()
                        }

                        is NetworkResult.Exception -> {
                            hideProgressBar()
                        }
                    }
                }
            }
        }
    }

    private fun pickImage(pickerResult: UploadFileOrImageFragment.ImagePickerResult) {
        val imagePicker = UploadFileOrImageFragment()
        imagePicker.setImagePickerResult(pickerResult)
        imagePicker.show(childFragmentManager, imagePicker.javaClass.simpleName)
    }


    private fun getFields(
    ) {
        compliantViewModel.notCallSpinnerLotOfTime = false
        compliantViewModel.getFields()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                compliantViewModel.getFields.collect {
                    when (it) {
                        is NetworkResult.Loading -> {
                            showProgressBar()
                        }

                        is NetworkResult.Success -> {
                            val defaultFieldForTicketType = Field(0,0, emptyList(),"",
                                "","","","",0,ALL_TICKET_TYPES,null,0,0,0,"",0,
                                0,0,0,0,"",0,0,0,0,0,0,0,0,
                                0,0,"","","","","")
                            drawSelectEditText(defaultFieldForTicketType)
                            it.data.fields.forEach { data ->
                                when (data.form_field_type_id) {
                                    ViewType.DROPDOWN.type -> {
                                        when (data.field) {
                                            MAIN_REASON_ID,SUB_REASON_ID,SUB_SUB_REASON_ID,SUB_SUB_SUB_REASON_ID -> {
                                                drawSelectEditText(data)
//                                        if (currentLanguage == Constants.AR_LANGUAGE) {
//                                            binding.spinnerTicketType.floatingLabelText =
//                                                data.display_name_ar
//                                        } else {
//                                            binding.spinnerTicketType.floatingLabelText =
//                                                data.display_name_en
//                                        }
                                            }


                                            HOSPITAL_ID -> {
                                                if (currentLanguage == Constants.AR_LANGUAGE) {
                                                    binding.spinnerMainReasons.floatingLabelText =
                                                        data.display_name_ar
                                                } else {
                                                    binding.spinnerMainReasons.floatingLabelText =
                                                        data.display_name_en
                                                }
                                            }

                                            DETAILS -> {
                                                if (currentLanguage == Constants.AR_LANGUAGE) {
                                                    binding.spinnerSubReasons.floatingLabelText =
                                                        data.display_name_ar
                                                } else {
                                                    binding.spinnerSubReasons.floatingLabelText =
                                                        data.display_name_en
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                            hideProgressBar()
                        }

                        is NetworkResult.Error -> {
                            showError(it.code, it.responseBody)
                            hideProgressBar()
                        }

                        is NetworkResult.ErrorEX -> {
                            hideProgressBar()
                        }

                        is NetworkResult.Exception -> {
                            hideProgressBar()
                        }
                    }
                }

            }
        }
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                compliantViewModel.getFields.collect {
//                    when (it) {
//                        is NetworkResult.Loading -> {
//                            showProgressBar()
//                        }
//
//                        is NetworkResult.Success -> {
//                            it.data.fields.forEach { data ->
//                                when (data.field) {
//                                    MAIN_REASON_ID -> {
//
//                                        if (currentLanguage == Constants.AR_LANGUAGE) {
//                                            binding.spinnerTicketType.floatingLabelText =data.display_name_ar
//                                        } else {
//                                            binding.spinnerTicketType.floatingLabelText =data.display_name_en
//                                        }
//                                    }
//
//                                    HOSPITAL_ID -> {
//                                        if (currentLanguage == Constants.AR_LANGUAGE) {
//                                            binding.spinnerMainReasons.floatingLabelText =data.display_name_ar
//                                        } else {
//                                            binding.spinnerMainReasons.floatingLabelText =data.display_name_en
//                                        }
//                                    }
//
//                                    DETAILS -> {
//                                        if (currentLanguage == Constants.AR_LANGUAGE) {
//                                            binding.spinnerSubReasons.floatingLabelText =data.display_name_ar
//                                        } else {
//                                            binding.spinnerSubReasons.floatingLabelText =data.display_name_en
//                                        }
//                                    }
//                                }
//                            }
//                            allTicketType()
//                            hideProgressBar()
//                        }
//
//                        is NetworkResult.Error -> {
//                            showError(it.code, it.responseBody)
//                            hideProgressBar()
//                        }
//
//                        is NetworkResult.ErrorEX -> {
//                            hideProgressBar()
//                        }
//
//                        is NetworkResult.Exception -> {
//                            hideProgressBar()
//                        }
//                    }
//                }
//            }
//        }
    }

    private fun getAllOrganization(mainReasonId: Int) {
        compliantViewModel.getAllOrganization(mainReasonId)
        compliantViewModel.getAllOrganization.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    showProgressBar()
                }

                is NetworkResult.Success -> {
                    val mainReasonsText = mutableListOf<Any>()
                    it.data.data.forEach { title ->
                        val currentLanguage = Lingver.getInstance().getLanguage()
                        if (currentLanguage == Constants.AR_LANGUAGE) {
                            mainReasonsText.add(title.titleAr)
                        } else {
                            mainReasonsText.add(title.titleEn)
                        }
                    }
                    binding.spinnerSubSubSubReasons.item = mainReasonsText
                    binding.spinnerSubSubSubReasons.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                p0: AdapterView<*>?,
                                p1: View?,
                                p2: Int,
                                p3: Long
                            ) {
                                //result = mainReasons[p2].id
                                // subSubReasons(it.data.data[p2].active, it.data.data[p2].id)
                            }

                            override fun onNothingSelected(p0: AdapterView<*>?) {
                            }
                        }
                    hideProgressBar()
                }

                is NetworkResult.Error -> {
                    showError(it.code, it.responseBody)
                    hideProgressBar()
                }

                is NetworkResult.ErrorEX -> {
                    hideProgressBar()
                }

                is NetworkResult.Exception -> {
                    hideProgressBar()
                }
            }
        }
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                compliantViewModel.getAllOrganization.collect {
//                    when (it) {
//                        is NetworkResult.Loading -> {
//                            showProgressBar()
//                        }
//
//                        is NetworkResult.Success -> {
//                            val mainReasonsText = mutableListOf<Any>()
//                            it.data.data.forEach { title ->
//                                val currentLanguage = Lingver.getInstance().getLanguage()
//                                if (currentLanguage == Constants.AR_LANGUAGE) {
//                                    mainReasonsText.add(title.titleAr)
//                                } else {
//                                    mainReasonsText.add(title.titleEn)
//                                }
//                            }
//                            binding.spinnerSubSubSubReasons.item = mainReasonsText
//                            binding.spinnerSubSubSubReasons.onItemSelectedListener =
//                                object : AdapterView.OnItemSelectedListener {
//                                    override fun onItemSelected(
//                                        p0: AdapterView<*>?,
//                                        p1: View?,
//                                        p2: Int,
//                                        p3: Long
//                                    ) {
//                                        //result = mainReasons[p2].id
//                                       // subSubReasons(it.data.data[p2].active, it.data.data[p2].id)
//                                    }
//
//                                    override fun onNothingSelected(p0: AdapterView<*>?) {
//                                    }
//                                }
//                            hideProgressBar()
//                        }
//
//                        is NetworkResult.Error -> {
//                            showError(it.code, it.responseBody)
//                            hideProgressBar()
//                        }
//
//                        is NetworkResult.ErrorEX -> {
//                            hideProgressBar()
//                        }
//
//                        is NetworkResult.Exception -> {
//                            hideProgressBar()
//                        }
//                    }
//                }
//            }
//        }
    }

    private fun setFragmentListenerForLookUpsFragment(
        globalView: GlobalView
    ) {
        setFragmentResultListener(
            LookUpSearchFragment_FRAGMENT_RESULT_KEY
        ) { requestKey, result ->
            if (requestKey == LookUpSearchFragment_FRAGMENT_RESULT_KEY) {
                val bundle =
                    result.getParcelable<LookUpModel>(LookUpSearchFragment_FRAGMENT_DATA)
                if (bundle != null) {
                    globalView.editTextView?.typeView?.setText(bundle.listName)
                    globalView.tablesModel.value = bundle.lookUpListId.toString()
                }
            }
        }
    }
}