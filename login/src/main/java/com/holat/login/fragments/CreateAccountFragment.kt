package com.holat.login.fragments

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import coil.load
import com.holat.login.R
import com.holat.login.base.BaseFragment
import com.holat.login.databinding.FragmentCreateAccountBinding

import com.holat.login.viewmodels.CreateAccountViewModel
import com.holat.login.viewmodels.LoginViewModel
import com.holat.login.network.NetworkResult
import com.holat.login.utils.ApplicationException
import com.holat.login.utils.DatePickerDialogUtil.selectDateOfBirthGregorian
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.showImage
import com.holat.login.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CreateAccountFragment : BaseFragment() {
    private lateinit var binding: FragmentCreateAccountBinding
    private val loginViewModel by viewModels<LoginViewModel>()
    private val createAccountViewModel by viewModels<CreateAccountViewModel>()
    private var nationalTypes = mutableListOf<Any>()

    override fun getBinding(): ViewBinding {
        binding = FragmentCreateAccountBinding.inflate(layoutInflater)
        return binding
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(this, enabled = true) {
            findNavController().navigateUp()
        }
    }

    override fun getData() {
       // setDefaultCountryCode(binding.co)
        getAllNationalTypes()
        reloadCaptcha()

        binding.datOfBirthEt.onDebouncedListener {
            binding.root.selectDateOfBirthGregorian(binding.datOfBirthEt,getString(R.string.date_of_birth))
        }
    }
    override fun onClick() {
        binding.includeCaptcha.reloadCaptcha.onDebouncedListener {
            reloadCaptcha()
        }
        binding.createAccountBtn.onDebouncedListener {
            try {
                validator.apply {
                    submit(binding.includeCountryCodeWithPhone.phoneEt).checkEmpty()
                        .errorMessage(getString(R.string.err_phone_number_not_correct)).checkMinDigits(9)
                        .errorMessage(getString(R.string.err_phone_number_not_correct))
                        .check()
                    submit(binding.usernameEt).checkEmpty()
                        .errorMessage(getString(R.string.name_required_one_later)).check()
                    if (createAccountViewModel.nationalIdTypeId.toInt() == -1) {
                        binding.root.showSnackBar(getString(R.string.error_national_type))
                        return@onDebouncedListener
                    }
                    submit(binding.NationalIdEt).checkEmpty()
                        .errorMessage(getString(R.string.required)).check()
                    submit(binding.emailEt).checkEmpty()
                        .errorMessage(getString(R.string.error_email)).checkValidEmail().errorMessage(getString(R.string.error_email)).check()
                    submit(binding.datOfBirthEt).checkEmpty()
                        .errorMessage(getString(R.string.error_date_of_birth)).check()
                    submit(binding.includeCaptcha.checkCodeEt).checkEmpty()
                        .errorMessage(getString(R.string.captcha_required)).check()
                }
                createAccount(
                    birthDay = binding.datOfBirthEt.text.toString().trim(),
                    email = binding.emailEt.text.toString().trim(),
                    name = binding.usernameEt.text.toString().trim(),
                    nationalIdTypeId = createAccountViewModel.nationalIdTypeId,
                    captchaCode = binding.includeCaptcha.checkCodeEt.text.toString().trim(),
                    key = loginViewModel.keyCaptcha,
                    mobile = binding.includeCountryCodeWithPhone.CountryCodePicker.selectedCountryCodeWithPlus + binding.includeCountryCodeWithPhone.phoneEt.text.toString().trim(),
                    nationalId = binding.NationalIdEt.text.toString().trim().toLong()
                )
            } catch (e: ApplicationException) {
                e.message?.let { binding.root.showSnackBar(it) }
            }

        }
    }

    private fun createAccount(
        birthDay: String,
        email: String,
        name: String,
        nationalIdTypeId: Short,
        captchaCode: String,
        key: String,
        mobile: String,
        nationalId: Long
    ) {
        createAccountViewModel.createAccount(
            key = key, captchaCode = captchaCode, nationalId = nationalId, email = email,
            mobile = mobile, name = name, birthDay = birthDay, nationalIdTypeId = nationalIdTypeId
        )
        viewLifecycleOwner.lifecycleScope.launch {
            createAccountViewModel.createAccount.collect {
                when (it) {
                    is NetworkResult.Loading -> {
                        showProgressBar()
                    }

                    is NetworkResult.Success -> {
                        hideProgressBar()
                        binding.root.showSnackBar(it.data.message)
                        findNavController().navigateUp()
                    }

                    is NetworkResult.Error -> {
                        hideProgressBar()
                        showError(it.code, it.responseBody)

                    }

                    is NetworkResult.ErrorEX -> {
                        hideProgressBar()
                        binding.root.showSnackBar(it.exception?.message.toString())
                    }

                    else -> {
                        hideProgressBar()
                    }
                }
            }
        }
    }

    private fun getAllNationalTypes() {
        loginViewModel.getAllNationalTypes()
        viewLifecycleOwner.lifecycleScope.launch {
            loginViewModel.getAllNationalTypes.collect {
                when (it) {
                    is NetworkResult.Loading -> {

                    }

                    is NetworkResult.Success -> {
                        it.data.data.forEach { title -> nationalTypes.add(title.titleAr) }
                        binding.spinnerNationalTypes.item = nationalTypes
                        binding.spinnerNationalTypes.onItemSelectedListener =
                            object : OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    createAccountViewModel.nationalIdTypeId = it.data.data[p2].id
                                }

                                override fun onNothingSelected(p0: AdapterView<*>?) {
                                }
                            }
                    }

                    is NetworkResult.Exception -> {

                    }

                    else -> {

                    }
                }
            }
        }
    }

    private fun reloadCaptcha() {
        loginViewModel.reloadCaptcha()
        viewLifecycleOwner.lifecycleScope.launch {
            loginViewModel.reloadCaptcha.collect {
                when (it) {
                    is NetworkResult.Loading -> {
                        binding.includeCaptcha.captcha.load(R.drawable.loading_animation)
                    }

                    is NetworkResult.Success -> {
                        loginViewModel.keyCaptcha = it.data.key
                        binding.root.showImage(it.data.img, binding.includeCaptcha.captcha)

//                            binding.captcha.load(it.data.img) {
//                                crossfade(true)
//                                transformations(CircleCropTransformation())
//                                    .error(R.drawable.loading_animation)
//                            }

                    }

                    is NetworkResult.Exception -> {

                    }

                    else -> {

                    }
                }
            }
        }
    }

}