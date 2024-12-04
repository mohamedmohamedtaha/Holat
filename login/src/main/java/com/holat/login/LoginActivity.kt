package com.holat.login


import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.google.firebase.FirebaseApp
import com.holat.login.base.BaseActivity
import com.holat.login.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigationComponentWithActionAbr()
        closeApp(navController = navController, currentFragment = R.id.loginWithNafathFragment)
        FirebaseApp.initializeApp(this)
    }

    private fun setNavigationComponentWithActionAbr() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragmentLogin) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration.Builder(R.id.loginWithNafathFragment).build()
        //  NavigationUI.setupWithNavController(binding.toolBar, navController)
        //For hide back button and login fragment in toolbar
//        navController.addOnDestinationChangedListener { navController: NavController, navDestination: NavDestination?, bundle: Bundle? ->
////            if (navController.currentDestination!!.id == R.id.loginFragment)
////                getToolbar().navigationIcon = null
//        }
    }
}