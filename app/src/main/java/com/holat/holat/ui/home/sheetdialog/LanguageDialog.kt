package com.holat.holat.ui.home.sheetdialog

import android.app.Activity
import android.content.res.Configuration
import androidx.viewbinding.ViewBinding
import com.holat.holat.R
import com.holat.holat.databinding.FragmentLanguageBinding
import com.holat.login.LoginActivity
import com.holat.login.base.BaseBottomSheetDialogFragment
import com.holat.login.utils.Constants.AR_LANGUAGE
import com.holat.login.utils.Constants.EN_LANGUAGE
import com.holat.login.utils.gotToSpecificActivity
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.showSnackBar
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class LanguageDialog : BaseBottomSheetDialogFragment() {
    private lateinit var binding: FragmentLanguageBinding

    override fun getBinding(): ViewBinding {
        binding = FragmentLanguageBinding.inflate(layoutInflater)
        return binding
    }

    override fun getData() {
        isCancelable = true

    }

    override fun onClick() {
        val currentLanguage = Lingver.getInstance().getLanguage()
        binding.textViewArabic.onDebouncedListener {
            if (currentLanguage == AR_LANGUAGE) {
                binding.root.showSnackBar(getString(R.string.error_language_ar))
            } else {
                Lingver.getInstance().setLocale(requireContext(), AR_LANGUAGE)
                dataStoreViewModel.saveLanguage(AR_LANGUAGE)
                // userDefaultLanguage(requireActivity(),language)
                //   requireActivity().updateResources(requireContext(),language)
                requireActivity().gotToSpecificActivity(LoginActivity::class.java)
                // changeLanguage(Constants.AR_LANGUAGE)
            }
        }
        binding.textViewEnglish.onDebouncedListener {
            //  changeLanguage(Constants.EN_LANGUAGE)
            if (currentLanguage == EN_LANGUAGE) {
                binding.root.showSnackBar(getString(R.string.error_language_en))
            } else {
                Lingver.getInstance().setLocale(requireContext(), EN_LANGUAGE)
                dataStoreViewModel.saveLanguage(EN_LANGUAGE)
                // userDefaultLanguage(requireActivity(),language)
                //   requireActivity().updateResources(requireContext(),language)
                requireActivity().gotToSpecificActivity(LoginActivity::class.java)
            }

        }

    }

    private fun changeLanguage(language: String) {
        dataStoreViewModel.saveLanguage(language)
        userDefaultLanguage(requireActivity(), language)
        //   requireActivity().updateResources(requireContext(),language)
        requireActivity().gotToSpecificActivity(LoginActivity::class.java)
    }

    fun userDefaultLanguage(context: Activity, localeName: String?) {
        //Log.e("Lan",session.getLanguage());
        val locale: Locale = Locale(localeName)
        val config = Configuration(context.resources.configuration)
        Locale.setDefault(locale)
        config.setLocale(locale)
        context.baseContext.resources.updateConfiguration(
            config,
            context.baseContext.resources.displayMetrics
        )
    }
}