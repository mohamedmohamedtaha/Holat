package com.holat.holat.ui.profile.fragments

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.holat.holat.R
import com.holat.holat.databinding.FragmentUpdateClientProfileBinding
import com.holat.holat.ui.profile.viewmodel.ClientProfileViewModel
import com.holat.login.base.BaseFragment
import com.holat.login.models.clientprofile.ClientProfile
import com.holat.login.network.NetworkResult
import com.holat.login.utils.ApplicationException
import com.holat.login.utils.DatePickerDialogUtil.selectDateOfBirthGregorian
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.showSnackBar
import com.holat.login.viewmodels.LoginViewModel
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class UpdateClientProfileFragment : BaseFragment() {
    private lateinit var binding: FragmentUpdateClientProfileBinding
    private val clientProfileViewModel by viewModels<ClientProfileViewModel>()
    private val loginViewModel by viewModels<LoginViewModel>()
    private var countryIdSelected = -1
    private var regionIdSelected = -1
    private var cityIdSelected = -1
    private var nationalTypesSelected: Short = -1
    private var countries = mutableListOf<Any>()
    private var regions = mutableListOf<Any>()
    private var cities = mutableListOf<Any>()
    private var nationalTypes = mutableListOf<Any>()


    override fun getBinding(): ViewBinding {
        binding = FragmentUpdateClientProfileBinding.inflate(layoutInflater)
        return binding
    }

    override fun getData() {
        toolbar.changeTextTitle(getString(R.string.title_change_data))
        toolbar.showSearchText(false)
        toolbar.showProfileImage(false)
        toolbar.showNotificationIcon(true)
        clientProfile()
        getAllNationalTypes()
        getAllCountries()
    }

    override fun onClick() {
        binding.dateOfBirthEt.onDebouncedListener {
            binding.root.selectDateOfBirthGregorian(
                binding.dateOfBirthEt,
                getString(R.string.date_of_birth)
            )
        }
        binding.dateOfEndIdEt.onDebouncedListener {
            binding.root.selectDateOfBirthGregorian(
                binding.dateOfEndIdEt,
                getString(R.string.date_of_expired_id),
                enableFutureDate = false
            )
        }
        binding.updateProfileBtn.onDebouncedListener {
            try {
                validator.apply {
                    submit(binding.usernameEt).checkEmpty()
                        .errorMessage(getString(R.string.required))
                        .check()
                    if (nationalTypesSelected.toInt() == -1) {
                        binding.root.showSnackBar(getString(R.string.error_national_type))
                        return@onDebouncedListener
                    }
                    submit(binding.nationalIdEt).checkEmpty()
                        .errorMessage(getString(R.string.required)).checkMinDigits(10)
                        .errorMessage(getString(R.string.err_id_number_not_equal_10_numbers))
                        .check()
                    if (countryIdSelected == -1) {
                        binding.root.showSnackBar(getString(R.string.error_country_name))
                        return@onDebouncedListener
                    }
                    if (regionIdSelected == -1) {
                        binding.root.showSnackBar(getString(R.string.error_region_name))
                        return@onDebouncedListener
                    }
                    if (cityIdSelected == -1) {
                        binding.root.showSnackBar(getString(R.string.error_city_name))
                        return@onDebouncedListener
                    }
                    submit(binding.includeCountryCodeWithPhone.phoneEt).checkEmpty()
                        .errorMessage(getString(R.string.err_phone_number_not_correct))
                        .checkMinDigits(9)
                        .errorMessage(getString(R.string.err_phone_number_not_correct))
                        .check()
                    submit(binding.dateOfBirthEt).checkEmpty()
                        .errorMessage(getString(R.string.error_date_of_birth)).check()
                    submit(binding.dateOfEndIdEt).checkEmpty()
                        .errorMessage(getString(R.string.error_date_of_expired_date)).check()
                    submit(binding.emailEt).checkEmpty()
                        .errorMessage(getString(R.string.required)).checkValidEmail()
                        .errorMessage(getString(R.string.error_email)).check()
                }

                updateClientProfile(
                    name = binding.usernameEt.text.toString().trim(),
                    nationalId = binding.nationalIdEt.text.toString().toLong(),
                    countryId = countryIdSelected.toString(),
                    regionId = regionIdSelected.toString(),
                    cityId = cityIdSelected.toString(),
                    idEndDate = binding.dateOfEndIdEt.text.toString().trim(),
                    mobile = binding.includeCountryCodeWithPhone.CountryCodePicker.selectedCountryCodeWithPlus + binding.includeCountryCodeWithPhone.phoneEt.text.toString()
                        .trim(),
                    email = binding.emailEt.text.toString(),
                    birthId = binding.dateOfBirthEt.text.toString().trim()
                )
            } catch (e: ApplicationException) {
                e.message?.let { binding.root.showSnackBar(it) }
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
                                    nationalTypesSelected = it.data.data[p2].id
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

    private fun getAllCountries() {
        clientProfileViewModel.getAllCountries()
        viewLifecycleOwner.lifecycleScope.launch {
            clientProfileViewModel.getAllCountries.collect {
                when (it) {
                    is NetworkResult.Loading -> {

                    }

                    is NetworkResult.Success -> {
                        countries.clear()
                        it.data.data.forEach { title -> countries.add(title.titleAr) }
                        binding.spinnerCountries.item = countries
                        binding.spinnerCountries.onItemSelectedListener =
                            object : OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    countryIdSelected = it.data.data[p2].id
                                    regionIdSelected = -1
                                    cityIdSelected = -1
                                    getAllRegions(it.data.data[p2].id)
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

    private fun getAllRegions(countryId: Int) {
        clientProfileViewModel.getAllRegions(countryId)
        viewLifecycleOwner.lifecycleScope.launch {
            clientProfileViewModel.getAllRegions.collect {
                when (it) {
                    is NetworkResult.Loading -> {

                    }

                    is NetworkResult.Success -> {
                        regions.clear()
                        it.data.data.forEach { title -> regions.add(title.titleAr) }
                        binding.spinnerRegions.item = regions
                        binding.spinnerRegions.onItemSelectedListener =
                            object : OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    regionIdSelected = it.data.data[p2].id
                                    cityIdSelected = -1
                                    getAllCities(
                                        countryId = countryIdSelected,
                                        regionId = it.data.data[p2].id
                                    )
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

    private fun getAllCities(countryId: Int, regionId: Int) {
        clientProfileViewModel.getAllCities(countryId, regionId)
        viewLifecycleOwner.lifecycleScope.launch {
            clientProfileViewModel.getAllCities.collect {
                when (it) {
                    is NetworkResult.Loading -> {

                    }

                    is NetworkResult.Success -> {
                        cities.clear()
                        it.data.data.forEach { title -> cities.add(title.titleAr) }
                        binding.spinnerCities.item = cities
                        binding.spinnerCities.onItemSelectedListener =
                            object : OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    cityIdSelected = it.data.data[p2].id
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

    private fun clientProfile(
    ) {
        clientProfileViewModel.clientProfile()
        viewLifecycleOwner.lifecycleScope.launch {
            clientProfileViewModel.clientProfile.collect {
                when (it) {
                    is NetworkResult.Loading -> {
                        showProgressBar()
                    }

                    is NetworkResult.Success -> {
                        hideProgressBar()
                        getClientProfile(it.data)
                    }

                    is NetworkResult.Error -> {
                        showError(it.code, it.responseBody)
                        hideProgressBar()
                    }

                    is NetworkResult.ErrorEX -> {

                        hideProgressBar()
                        // showError(it)
                        //reloadCaptcha()
                    }

                    is NetworkResult.Exception -> {
                        hideProgressBar()
                    }
                }
            }
        }
    }

    private fun getClientProfile(data: ClientProfile) {
        binding.usernameEt.setText(data.name)
        binding.includeCountryCodeWithPhone.phoneEt.setText(data.mobile)
        binding.nationalIdEt.setText(data.nationalId)
        binding.dateOfBirthEt.setText(data.birthDate)
        binding.dateOfEndIdEt.setText(data.idEndDate)
        binding.emailEt.setText(data.email)

        val currentLanguage = Lingver.getInstance().getLanguage()
//        if (currentLanguage == Constants.AR_LANGUAGE) {
//            binding.spinnerNationalTypes.item = data.nationalIdType.titleAr
//        } else {
//            binding.spinnerNationalTypes.text = data.nationalIdType.titleEn
//        }
    }

    private fun updateClientProfile(
        name: String,
        nationalId: Long,
        countryId: String,
        regionId: String,
        cityId: String,
        idEndDate: String,
        mobile: String,
        email: String,
        birthId: String
    ) {
        clientProfileViewModel.updateClientProfile(
            name,
            nationalId,
            countryId,
            regionId,
            cityId,
            idEndDate,
            mobile,
            email,
            birthId
        )
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                clientProfileViewModel.updateClientProfile.collect {
                    when (it) {
                        is NetworkResult.Loading -> {
                            showProgressBar()
                        }

                        is NetworkResult.Success -> {
                            binding.root.showSnackBar(getString(R.string.update_successfully))
                            findNavController().navigateUp()
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
}