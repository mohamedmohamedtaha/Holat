package com.holat.login.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.android.volley.ServerError
import com.holat.login.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hbb20.CountryCodePicker
import com.holat.login.data.datastore.DataStoreViewModel
import com.holat.login.database.SelfServiceConfigDatabaseViewModel
import com.holat.login.LoginActivity
import com.holat.login.models.ErrorResponse
import com.holat.login.models.ServiceConfigDatabase
import com.holat.login.utils.Validator
import com.holat.login.utils.gotToSpecificActivity
import com.holat.login.utils.setDefaultCountry
import com.holat.login.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.ResponseBody
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {
    private var binding: ViewBinding? = null
    protected lateinit var toolbar: HasToolbar
    protected val dataStoreViewModel by viewModels<DataStoreViewModel>()
    protected val selfServiceConfigDatabaseViewModel by viewModels<SelfServiceConfigDatabaseViewModel>()

    @Inject
    protected lateinit var validator: Validator

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
        onClick()
        getData()
    }

    protected abstract fun getData()
    protected abstract fun onClick()

    //    fun updateVersionNumber(): String {
//        return   String.format(getString(R.string.version_no), BuildConfig.VERSION_NAME)
//    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is HasToolbar) {
            toolbar = activity as HasToolbar
        }
        Log.e("onAttach", "BaseFragment")

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

    fun getScreenOn() {
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    fun hideKeyBoardForActivity() {
        // Check if no view has focus:
        val view = requireActivity().currentFocus
        if (view != null) {
            val inputManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                view.windowToken,
                0
            )
        }
    }

    fun onError(throwable: Throwable) {
        try {
            //   hideProgressBar()
            if (throwable is ConnectException || throwable is SocketException) {
                requireView().showSnackBar(getString(R.string.connect_to_internet))
            } else if (throwable is RuntimeException) {
                throwable.message?.let { requireView().showSnackBar(it) }
                requireActivity().finish()
                val intentLogin = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intentLogin)
            } else if (throwable is SocketTimeoutException) {
                requireView().showSnackBar(getString(R.string.connection_timed_out))
            } else if (throwable is ServerError) {
                throwable.message?.let { requireView().showSnackBar(it) }
            } else {
                requireView().showSnackBar(getString(R.string.something_wrong_here))
                throwable.printStackTrace()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun showError(code:Int, data: ResponseBody?) {
        if (code == 401 || code == 403) {
            dataStoreViewModel.deleteToken()
          //  dataStoreViewModel.deleteUserData()
            binding?.root?.showSnackBar(getString(R.string.err_session_expired))
            requireActivity().gotToSpecificActivity(LoginActivity::class.java)
            return
        }

        val gson = Gson()
        val type = object : TypeToken<ErrorResponse>() {}.type
        try {
//            if (data.code() == 400){
//                data.body()
//            }
            val errorResponse: ErrorResponse? = gson.fromJson(data?.charStream(), type)
            // data.errorBody()?.contentType()
            errorResponse?.errors.let {
                it.toString()
            }
            if (errorResponse?.errors != null) {
                requireView().showSnackBar(errorResponse.errors.toString())
            } else
                requireView().showSnackBar(errorResponse?.message.toString())
//            if (errorResponse != null) {
//                requireView().showSnackBar(errorResponse.message)
//                if (errorResponse.details == TOKEN_EXPIRED) {
//                    val intent = Intent(context, LoginActivity::class.java)
//                    intent.putExtra(Constants.IS_REFRESH_TOKEN, true)
//                    //or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
//                    requireActivity().startActivity(intent)
//                    return
//                }
//            }
        } catch (ex: Exception) {
            requireView().showSnackBar(ex.message.toString())
        }
    }

    //    fun showError(data: Response<Void>) {
//        val gson = Gson()
//        val type = object : TypeToken<ErrorResponse>() {}.type
//        try {
//            if (data.code() == 400){
//                data.body()
//            }
//            val errorResponse: ErrorResponse? = gson.fromJson(data.errorBody()?.charStream(), type)
//            data.errorBody()?.contentType()
//            if (errorResponse != null) {
//                requireView().showSnackBar(errorResponse.message)
//                if (errorResponse.details == TOKEN_EXPIRED) {
//                    val intent = Intent(context, LoginActivity::class.java)
//                    intent.putExtra(Constants.IS_REFRESH_TOKEN, true)
//                    //or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
//                    requireActivity().startActivity(intent)
//                    return
//                }
//            }
//        } catch (ex: Exception) {
//            requireView().showSnackBar(ex.message.toString())
//        }
//    }
    protected fun hideIconsInToolbar() {
        toolbar.showProfileImage(false)
        toolbar.showSearchText(false)
    }

    //    protected fun refreshApp() {
//        val intent = Intent(requireActivity(), LoginActivity::class.java)
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        startActivity(intent)
//       // requireActivity().finish()
//    }


     fun updateResources(context: Context,language: String) {

//         val local = Locale(language)
//         Locale.setDefault(local)
//         val config = Configuration()
//         config.setLocale(local)
//         context.resources.updateConfiguration(config, context.resources.displayMetrics)


//        val locale = Locale(language)
//        Locale.setDefault(locale)
//        val configuration: Configuration = context.resources.configuration
//        configuration.setLocale(locale)
//        configuration.setLayoutDirection(locale)
//        return context.createConfigurationContext(configuration)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

     fun setDefaultCountryCode(phoneNumberPicker: CountryCodePicker){
         if (ServiceConfigDatabase.serviceConfigDatabase.mobileDefaultCountry.isNotEmpty())
             binding?.root?.setDefaultCountry(phoneNumberPicker = phoneNumberPicker, countryCode =  ServiceConfigDatabase.serviceConfigDatabase.mobileDefaultCountry)
         else
             binding?.root?.setDefaultCountry(phoneNumberPicker = phoneNumberPicker, countryCode =  "sa")
     }


}