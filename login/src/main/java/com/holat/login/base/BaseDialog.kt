package com.holat.login.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.holat.login.R

abstract class BaseDialog:DialogFragment() {
    private var binding: ViewBinding? = null

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

    protected abstract fun getBinding(): ViewBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()

    }
    protected abstract fun getData()
}