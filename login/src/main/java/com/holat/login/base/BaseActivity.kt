package com.holat.login.base

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.holat.login.R
import com.holat.login.data.datastore.DataStoreViewModel
import com.holat.login.database.SelfServiceConfigDatabaseViewModel
import com.holat.login.viewmodels.SelfServiceConfigOnlineViewModel
import com.holat.login.network.NetworkResult
import com.holat.login.utils.Constants.AR_LANGUAGE
import com.holat.login.utils.Constants.EN_LANGUAGE
import com.holat.login.utils.DialogUtil
import com.holat.login.utils.updateResources
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {
    private val dataStoreViewModel by viewModels<DataStoreViewModel>()
    private val selfServiceConfigOnlineViewModel by viewModels<SelfServiceConfigOnlineViewModel>()
    private val selfServiceConfigDatabaseViewModel by viewModels<SelfServiceConfigDatabaseViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLanguage()
        getSelfServiceConfigsOnline()
    }

    private fun setLanguage() {
        var language: String
        dataStoreViewModel.getLanguage.observe(this) {
            if (it.isNullOrEmpty()) {
                Log.d("setLanguage", "if")
                language = if (Resources.getSystem().configuration.locale.language == AR_LANGUAGE) {
                    dataStoreViewModel.saveLanguage(AR_LANGUAGE)
                    AR_LANGUAGE
                } else {
                    dataStoreViewModel.saveLanguage(EN_LANGUAGE)
                    EN_LANGUAGE
                }
            } else {
                Log.e("setLanguage", "else")
                language = when (it) {
                    AR_LANGUAGE ->
                        AR_LANGUAGE

                    EN_LANGUAGE ->
                        EN_LANGUAGE

                    else -> {
                        dataStoreViewModel.saveLanguage(AR_LANGUAGE)
                        AR_LANGUAGE
                    }
                }
            }
            updateResources(language)
        }
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

//    private fun updateResources(language: String) {
//        val local = Locale(language)
//        Locale.setDefault(local)
//        val config = Configuration()
//        config.setLocale(local)
//        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
//    }

    private fun getSelfServiceConfigsOnline() {
        selfServiceConfigOnlineViewModel.getSelfServiceConfigsOnline()
        lifecycleScope.launch {
            selfServiceConfigOnlineViewModel.getSelf.collect {
                when (it) {
                    is NetworkResult.Loading -> {
                    }

                    is NetworkResult.Success -> {
                        selfServiceConfigDatabaseViewModel.setConfigService(it)
                    }

                    is NetworkResult.Error -> {
                        // binding.root.showSnackBar(it.toString())
                    }

                    is NetworkResult.Exception -> {
                        // binding.root.showSnackBar(it.toString())
                    }

                    else -> {
                        //   binding.root.showSnackBar(it.toString())
                    }
                }
            }
        }
    }

    fun closeApp(navController: NavController, currentFragment: Int) {
        onBackPressedDispatcher.addCallback(this, enabled = true) {
            if (navController.currentDestination?.id == currentFragment) {
                DialogUtil.showMessageWithYesNoMaterialDesign(
                    this@BaseActivity,
                    getString(R.string.exit),
                    getString(R.string.warning_exit_app)
                ) { _, _ -> finish() }
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }

//    fun showProgressBar() {
//        val progressBar = findViewById<View>(R.id.progressBar)
//
//        progressBar.visibility = View.VISIBLE
//        progressBar.animation =
//            AnimationUtils.loadAnimation(this, R.anim.custom_progress_bar)
//    }

//    fun hideProgressBar() {
//        val progressBar = findViewById<View>(R.id.progressBar)
//        progressBar.clearAnimation()
//        progressBar.visibility = View.GONE
//    }
}