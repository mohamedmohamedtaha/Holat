package com.holat.holat.ui.home.sheetdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.viewbinding.ViewBinding
import com.holat.holat.R
import com.holat.holat.databinding.FragmentListWithSearchBinding
import com.holat.holat.ui.home.adapter.SearchAdapter
import com.holat.login.base.BaseBottomSheetDialogFragment
import com.holat.login.models.LookUpModel
import com.holat.login.utils.listener.ClickListener
import dagger.hilt.android.AndroidEntryPoint

const val LookUpSearchFragment_FRAGMENT_RESULT_KEY = "LookUpSearchFragment_RESULT_KEY"
const val LookUpSearchFragment_FRAGMENT_DATA = "LookUpSearchFragment_FRAGMENT_DATA"

@AndroidEntryPoint
class ListWithSearchFragment : BaseBottomSheetDialogFragment(), View.OnClickListener {
    private lateinit var binding: FragmentListWithSearchBinding

    //    private val lookupViewModel by viewModels<LookUpViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        return binding.root
    }

    override fun getBinding(): ViewBinding {
        binding = FragmentListWithSearchBinding.inflate(layoutInflater)
        binding.ivSearch.setOnClickListener(this)
        makeBottomSheetFullScreen()
        isCancelable = true
        return binding
    }

    override fun getData() {
        //Get lookup for adapter
//        if (args.lookUpId != -1 && args.questCode == "")
//            lookupViewModel.getLookUpBySearch("", "", args.lookUpId, langId =  currentLanguage)
//        else if (args.lookUpId != -1 && args.questCode == "302.1")
//            lookupViewModel.getLookUpBySearch("1", "", args.lookUpId, labelKey = args.lookUpRef, langId = currentLanguage)
//
//        lookupViewModel.getLookUpBySearch.observe(viewLifecycleOwner) {
//            hideProgressBar()
//            initRecyclerView(it)
//        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(qString: String): Boolean {
                // showProgressBar()
                callDetailsMethod(qString)
                return false
                //  return true
            }

            override fun onQueryTextSubmit(qString: String): Boolean {

                binding.searchView.clearFocus()


                //  showProgressBar()
                callDetailsMethod(qString)
                return true
            }
        })
    }

    override fun onClick() {
        TODO("Not yet implemented")
    }

    private fun callDetailsMethod(search: String) {
//        showProgressBar()
//        hideKeyBoard(binding.root.rootView)
//        if (args.lookUpId != -1)
//            lookupViewModel.getLookUpBySearch("", search, args.lookUpId, langId =  currentLanguage)
////        lookupViewModel.getLookUpBySearch.observe(viewLifecycleOwner) {
////            hideProgressBar()
////            initRecyclerView(it)
////        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ivSearch -> {
                val result = binding.editTextSearch.text.toString().trim()
                if (result.isNotEmpty()) {
                    //   showProgressBar()
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
            //   binding.includeCustomEmptySearch.linearLayoutNoData.visibility = View.INVISIBLE
            val adapter = SearchAdapter(object : ClickListener<LookUpModel> {
                override fun onClick(view: View, t: LookUpModel) {
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
            //   binding.includeCustomEmptySearch.linearLayoutNoData.visibility = View.VISIBLE
        }
    }

}