package com.holat.login.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.viewbinding.ViewBinding
import com.holat.login.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.holat.login.data.datastore.DataStoreViewModel
import com.holat.login.sheetdialog.UploadFileOrImageFragment
import com.holat.login.utils.Validator


abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var binding:ViewBinding? = null

    protected val dataStoreViewModel by activityViewModels<DataStoreViewModel>()
    protected lateinit var validator: Validator
    //Make background transparent
    override fun getTheme(): Int {
        return R.style.BottomSheetDialogTransparent
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBinding()
        return binding?.root
    }
    protected abstract fun getBinding():ViewBinding
    protected abstract fun getData()
    protected abstract fun onClick()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        onClick()
    }
    fun showProgressBar() {
        val progressBar = requireActivity().findViewById(R.id.progressBar) as? View
        if (progressBar != null) {
            requireActivity().window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            progressBar.visibility = View.VISIBLE
            progressBar.animation =
                AnimationUtils.loadAnimation(requireContext(), R.anim.custom_progress_bar)
        }

    }

    fun hideProgressBar() {
        val progressBar = requireActivity().findViewById(R.id.progressBar) as? View
        if (progressBar != null) {
            progressBar.clearAnimation()
            progressBar.visibility = View.GONE
            requireActivity().window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        dataStoreViewModel.getLanguage.observe(viewLifecycleOwner) {
//            if (it == "ar")
//                currentLanguage = "1"
//            else
//                currentLanguage = "2"
//        }
//    }

//    fun showProgressBar() {
//        val progressBar = requireActivity().findViewById(R.id.progressBar) as View
//
//        progressBar.visibility = View.VISIBLE
//        progressBar.animation =
//            AnimationUtils.loadAnimation(requireContext(), R.anim.custom_progress_bar)
//    }
//
//    fun hideProgressBar() {
//        val progressBar = requireActivity().findViewById(R.id.progressBar) as View
//        progressBar.clearAnimation()
//        progressBar.visibility = View.GONE
//    }

    fun hideKeyBoard(view: View) {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            view.windowToken,
            SOFT_INPUT_STATE_ALWAYS_HIDDEN
        )
    }

    fun showKeyBoard() {
        // Check if no view has focus:
        // requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        val view = requireActivity().currentFocus
        if (view != null) {
            val inputManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.showSoftInput(
                view, 0
            )
        }
    }
    fun makeBottomSheetFullScreen(){
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenBottomSheetDialogFragmentTheme)
        dialog?.setOnShowListener { dialog ->
            val layout: FrameLayout? =
                (dialog as BottomSheetDialog).findViewById(com.google.android.material.R.id.design_bottom_sheet)
            layout?.let {
                val behavior = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.skipCollapsed = true
            }
        }
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    fun pickImage(pickerResult: UploadFileOrImageFragment.ImagePickerResult) {
        val imagePicker = UploadFileOrImageFragment()
        imagePicker.setImagePickerResult(pickerResult)
        imagePicker.show(childFragmentManager, imagePicker.javaClass.simpleName)
    }
}