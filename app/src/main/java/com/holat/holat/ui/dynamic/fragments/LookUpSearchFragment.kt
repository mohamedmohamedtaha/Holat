package com.holat.holat.ui.dynamic.fragments

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.holat.holat.R
import com.holat.holat.databinding.FragmentLookupSearchBinding
import com.holat.holat.ui.home.adapter.SearchAdapter
import com.holat.holat.ui.home.compliants.fragment.ALL_TICKET_TYPES
import com.holat.holat.ui.home.compliants.fragment.CITY_ID
import com.holat.holat.ui.home.compliants.fragment.COUNTRY_ID
import com.holat.holat.ui.home.compliants.fragment.HOSPITAL_ID
import com.holat.holat.ui.home.compliants.fragment.MAIN_REASON_ID
import com.holat.holat.ui.home.compliants.fragment.REGION_ID
import com.holat.holat.ui.home.compliants.fragment.SUB_REASON_ID
import com.holat.holat.ui.home.compliants.fragment.SUB_SUB_REASON_ID
import com.holat.holat.ui.home.compliants.fragment.SUB_SUB_SUB_REASON_ID
import com.holat.holat.ui.home.compliants.viewmodel.CompliantViewModel
import com.holat.holat.ui.profile.viewmodel.ClientProfileViewModel
import com.holat.login.base.BaseBottomSheetDialogFragment
import com.holat.login.models.LookUpModel
import com.holat.login.network.NetworkResult
import com.holat.login.utils.Constants
import com.holat.login.utils.changeLanguage
import com.holat.login.utils.listener.ClickListener
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

const val LookUpSearchFragment_FRAGMENT_RESULT_KEY = "LookUpSearchFragment_RESULT_KEY"
const val LookUpSearchFragment_FRAGMENT_DATA = "LookUpSearchFragment_FRAGMENT_DATA"

@AndroidEntryPoint
class LookUpSearchFragment : BaseBottomSheetDialogFragment(), View.OnClickListener {
    private lateinit var binding: FragmentLookupSearchBinding
    private val compliantViewModel by activityViewModels<CompliantViewModel>()
    private val clientProfileViewModel by viewModels<ClientProfileViewModel>()
    private val args: LookUpSearchFragmentArgs by navArgs()
    val data = ArrayList<LookUpModel>()


    override fun getBinding(): ViewBinding {
        binding = FragmentLookupSearchBinding.inflate(layoutInflater)
        binding.ivSearch.setOnClickListener(this)
        makeBottomSheetFullScreen()
        isCancelable = true
        return binding
    }

    override fun getData() {
        getDropDownList(endPoint = args.dependedOn, id = args.id)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(qString: String): Boolean {
                showProgressBar()
                callDetailsMethod(qString)
                return false
                //  return true
            }

            override fun onQueryTextSubmit(qString: String): Boolean {

                binding.searchView.clearFocus()


                showProgressBar()
                callDetailsMethod(qString)
                return true
            }
        })
    }

    override fun onClick() {
    }

    private fun getDropDownList(
        endPoint: String,
        id: Int
    ) {
        when (endPoint) {
            ALL_TICKET_TYPES -> allTicketType()
            MAIN_REASON_ID -> mainReason()
            SUB_REASON_ID -> subReasons(1, id)
            SUB_SUB_REASON_ID -> subSubReasons(1, id)
            SUB_SUB_SUB_REASON_ID -> subSubSubReasons(1, id)
            COUNTRY_ID -> getAllCountries()
            REGION_ID -> getAllRegions(id)
            CITY_ID -> getAllCities(id,id)
//            HOSPITAL_ID->
        }

    }

    private fun allTicketType(
        active: Int = 1
    ) {
        compliantViewModel.allTicketType(active)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                compliantViewModel.allTicketType.collect {
                    when (it) {
                        is NetworkResult.Loading -> {
                            showProgressBar()
                        }

                        is NetworkResult.Success -> {
                            hideProgressBar()
                            val currentLanguage = Lingver.getInstance().getLanguage()
                            it.data.data.forEach { lookUp ->
                                data.add(
                                    LookUpModel(
                                        lookUpListId = lookUp.id,
                                        listName = if (currentLanguage == Constants.AR_LANGUAGE) lookUp.titleAr else
                                            lookUp.titleEn
                                    )
                                )
                            }
                            initRecyclerView(data)
                            // ticketTypeId = 1
                            //  mainReasons(1, 1)
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

    }

    private fun mainReason() {
        compliantViewModel.mainReasons(active = 1, ticketTypeId = 1)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                compliantViewModel.mainReasons.collect {
                    when (it) {
                        is NetworkResult.Loading -> {
                            showProgressBar()
                        }

                        is NetworkResult.Success -> {
                            hideProgressBar()
                            it.data.forEach { lookUp ->
                                data.add(
                                    LookUpModel(
                                        lookUpListId = lookUp.id,
                                        listName = changeLanguage(lookUp.titleAr, lookUp.titleEn)
                                    )
                                )
                            }
                            initRecyclerView(data)
                        }

                        is NetworkResult.Error -> {
                            // showError(it.code, it.responseBody)
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
    }

    private fun subReasons(
        active: Int, mainReasonId: Int
    ) {
        compliantViewModel.subReasons(active, mainReasonId)
        compliantViewModel.subReasons.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    showProgressBar()
                }

                is NetworkResult.Success -> {
                    hideProgressBar()
                    it.data.forEach { lookUp ->
                        data.add(
                            LookUpModel(
                                lookUpListId = lookUp.id,
                                listName = changeLanguage(lookUp.titleAr, lookUp.titleEn)
                            )
                        )
                    }
                    initRecyclerView(data)
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
//                compliantViewModel.subReasons.collect {
//                    when (it) {
//                        is NetworkResult.Loading -> {
//                            showProgressBar()
//                        }
//
//                        is NetworkResult.Success -> {
//                            val mainReasonsText = mutableListOf<Any>()
//                            it.data.forEach { title ->
//                                val currentLanguage = Lingver.getInstance().getLanguage()
//                                if (currentLanguage == Constants.AR_LANGUAGE) {
//                                    mainReasonsText.add(title.titleAr)
//                                } else {
//                                    mainReasonsText.add(title.titleEn)
//                                }
//                            }
//                            // binding.spinnerMainReasons.floatingLabelText
//                            binding.spinnerMainReasons.item = mainReasonsText
//                            binding.spinnerMainReasons.onItemSelectedListener =
//                                object : AdapterView.OnItemSelectedListener {
//                                    override fun onItemSelected(
//                                        p0: AdapterView<*>?,
//                                        p1: View?,
//                                        p2: Int,
//                                        p3: Long
//                                    ) {
//                                        //result = mainReasons[p2].id
//                                        subSubReasons(it.data[p2].active, it.data[p2].id)
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

    private fun subSubReasons(
        active: Int, subReasonId: Int
    ) {
        compliantViewModel.subSubReasons(active, subReasonId)
        compliantViewModel.subSubReasons.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    showProgressBar()
                }

                is NetworkResult.Success -> {
                    hideProgressBar()
                    it.data.forEach { lookUp ->
                        data.add(
                            LookUpModel(
                                lookUpListId = lookUp.id,
                                listName = changeLanguage(lookUp.titleAr, lookUp.titleEn)
                            )
                        )
                    }
                    initRecyclerView(data)
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
//                compliantViewModel.subSubReasons.collect {
//                    when (it) {
//                        is NetworkResult.Loading -> {
//                            showProgressBar()
//                        }
//
//                        is NetworkResult.Success -> {
//                            val mainReasonsText = mutableListOf<Any>()
//                            it.data.forEach { title ->
//                                val currentLanguage = Lingver.getInstance().getLanguage()
//                                if (currentLanguage == Constants.AR_LANGUAGE) {
//                                    mainReasonsText.add(title.titleAr)
//                                } else {
//                                    mainReasonsText.add(title.titleEn)
//                                }
//                            }
//
//                            binding.spinnerSubReasons.item = mainReasonsText
//                            binding.spinnerSubReasons.onItemSelectedListener =
//                                object : AdapterView.OnItemSelectedListener {
//                                    override fun onItemSelected(
//                                        p0: AdapterView<*>?,
//                                        p1: View?,
//                                        p2: Int,
//                                        p3: Long
//                                    ) {
//                                        subSubSubReasons(
//                                            active = it.data[p2].active,
//                                            subSubReasonId = it.data[p2].id
//                                        )
//
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

    private fun subSubSubReasons(
        active: Int, subSubReasonId: Int
    ) {
        compliantViewModel.subSubSubReasons(active, subSubReasonId)
        compliantViewModel.subSubSubReasons.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    showProgressBar()
                }

                is NetworkResult.Success -> {
                    hideProgressBar()
                    it.data.forEach { lookUp ->
                        data.add(
                            LookUpModel(
                                lookUpListId = lookUp.id,
                                listName = changeLanguage(lookUp.titleAr, lookUp.titleEn)
                            )
                        )
                    }
                    initRecyclerView(data)
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
//                compliantViewModel.subSubSubReasons.collect {
//                    when (it) {
//                        is NetworkResult.Loading -> {
//                            showProgressBar()
//                        }
//
//                        is NetworkResult.Success -> {
//                            hideProgressBar()
//                            val mainReasonsText = mutableListOf<Any>()
//                            it.data.forEach { title ->
//                                val currentLanguage = Lingver.getInstance().getLanguage()
//                                if (currentLanguage == Constants.AR_LANGUAGE) {
//                                    mainReasonsText.add(title.titleAr)
//                                } else {
//                                    mainReasonsText.add(title.titleEn)
//                                }
//                            }
//                            binding.spinnerSubSubReasons.item = mainReasonsText
//                            binding.spinnerSubSubReasons.onItemSelectedListener =
//                                object : AdapterView.OnItemSelectedListener {
//                                    override fun onItemSelected(
//                                        p0: AdapterView<*>?,
//                                        p1: View?,
//                                        p2: Int,
//                                        p3: Long
//                                    ) {
//
//                                    }
//
//                                    override fun onNothingSelected(p0: AdapterView<*>?) {
//                                    }
//                                }
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

    private fun getAllCountries() {
        clientProfileViewModel.getAllCountries()
        viewLifecycleOwner.lifecycleScope.launch {
            clientProfileViewModel.getAllCountries.collect {
                when (it) {
                    is NetworkResult.Loading -> {

                    }

                    is NetworkResult.Success -> {
                        hideProgressBar()
                        it.data.data.forEach { lookUp ->
                            data.add(
                                LookUpModel(
                                    lookUpListId = lookUp.id,
                                    listName = changeLanguage(lookUp.titleAr, lookUp.titleEn)
                                )
                            )
                        }
                        initRecyclerView(data)
                    }

                    is NetworkResult.Exception -> {

                    }

                    else -> {

                    }
                }
            }
        }
    }

    private fun getAllRegions(countryId: Int) {
        clientProfileViewModel.getAllRegions(countryId)
        viewLifecycleOwner.lifecycleScope.launch {
            clientProfileViewModel.getAllRegions.collect {
                when (it) {
                    is NetworkResult.Loading -> {

                    }

                    is NetworkResult.Success -> {
                        hideProgressBar()
                        it.data.data.forEach { lookUp ->
                            data.add(
                                LookUpModel(
                                    lookUpListId = lookUp.id,
                                    listName = changeLanguage(lookUp.titleAr, lookUp.titleEn)
                                )
                            )
                        }
                        initRecyclerView(data)
                    }

                    is NetworkResult.Exception -> {

                    }

                    else -> {

                    }
                }
            }
        }
    }

    private fun getAllCities(countryId: Int, regionId: Int) {
        clientProfileViewModel.getAllCities(countryId, regionId)
        viewLifecycleOwner.lifecycleScope.launch {
            clientProfileViewModel.getAllCities.collect {
                when (it) {
                    is NetworkResult.Loading -> {

                    }

                    is NetworkResult.Success -> {
                        hideProgressBar()
                        it.data.data.forEach { lookUp ->
                            data.add(
                                LookUpModel(
                                    lookUpListId = lookUp.id,
                                    listName = changeLanguage(lookUp.titleAr, lookUp.titleEn)
                                )
                            )
                        }
                        initRecyclerView(data)
                    }

                    is NetworkResult.Exception -> {

                    }

                    else -> {

                    }
                }
            }
        }
    }

    private fun getDropDownList(endPoint: String, active: Int, queryParams: Map<String, String>) {
        compliantViewModel.getDropDownList(endPoint, active, queryParams)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                compliantViewModel.getDropDownList.collect {
                    when (it) {
                        is NetworkResult.Loading -> {
                            showProgressBar()
                        }

                        is NetworkResult.Success -> {
                            hideProgressBar()
                            val currentLanguage = Lingver.getInstance().getLanguage()
                            it.data.forEach { lookUp ->
                                data.add(
                                    LookUpModel(
                                        lookUpListId = lookUp.id,
                                        listName = if (currentLanguage == Constants.AR_LANGUAGE) lookUp.titleAr else
                                            lookUp.titleEn
                                    )
                                )
                            }
                            initRecyclerView(data)
                        }

                        is NetworkResult.Error -> {
                            // showError(it.code, it.responseBody)
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
    }

    private fun callDetailsMethod(search: String) {
        showProgressBar()
        hideKeyBoard(binding.root.rootView)
        data.filter { it.listName == search }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ivSearch -> {
                val result = binding.editTextSearch.text.toString().trim()
                if (result.isNotEmpty()) {
                    showProgressBar()
                    callDetailsMethod(result)
                } else {
                    callDetailsMethod("")
                }
            }
        }
    }

    private fun initRecyclerView(lookUpList: ArrayList<LookUpModel>?) {
        if (lookUpList != null) {
            binding.recyclerView.visibility = View.VISIBLE
            binding.includeCustomEmptySearch.linearLayoutNoData.visibility = View.INVISIBLE
            val adapter = SearchAdapter(object : ClickListener<LookUpModel> {
                override fun onClick(view: View, t: LookUpModel) {
                    //    t.position = args.lookUpId
                    setFragmentResult(
                        LookUpSearchFragment_FRAGMENT_RESULT_KEY,
                        bundleOf(LookUpSearchFragment_FRAGMENT_DATA to t)
                    )
                    dismiss()
                }
            })
            binding.recyclerView.adapter = adapter
            adapter.submitList(lookUpList)
        } else {
            binding.recyclerView.visibility = View.INVISIBLE
            binding.includeCustomEmptySearch.linearLayoutNoData.visibility = View.VISIBLE
        }
    }

}