package com.holat.holat.ui.home.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.holat.holat.R
import com.holat.holat.databinding.ActivityMainBinding
import com.holat.login.base.BaseActivity
import com.holat.login.base.HasToolbar
import com.holat.login.utils.listener.NotificationListener
import com.holat.login.utils.listener.SearchListener
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), HasToolbar {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navGraph: NavGraph
    private lateinit var notificationListener: NotificationListener
    private lateinit var myAccountListener: NotificationListener
    private lateinit var searchListener: SearchListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigationComponentWithActionAbr()
        closeApp(navController = navController, currentFragment = R.id.main_home)

//        val navView: BottomNavigationView = binding.navView
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        //setupWithNavController(binding.toolBar,navController)
//        //setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }

    private fun setNavigationComponentWithActionAbr() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragmentMain) as NavHostFragment
        navController = navHostFragment.navController
        // appBarConfiguration = AppBarConfiguration.Builder(R.id.loginFragment).build()
        setupWithNavController(binding.toolBar, navController)
        setupWithNavController(binding.navView, navController)
        //For hide back button and navigation button in some cases
        navController.addOnDestinationChangedListener { navController: NavController, navDestination: NavDestination?, bundle: Bundle? ->
            if (navController.currentDestination!!.id == R.id.main_home ||
                navController.currentDestination!!.id == R.id.sama_fragment ||
                navController.currentDestination!!.id == R.id.my_compliant_fragment ||
                navController.currentDestination!!.id == R.id.my_account
            ) {
                getToolbar().navigationIcon = null
                binding.navView.visibility = View.VISIBLE
                binding.createComplaint.visibility = View.VISIBLE
            } else {
                binding.navView.visibility = View.GONE
                binding.createComplaint.visibility = View.GONE
            }
        }

        binding.includeToolBar.icNotification.onDebouncedListener {
            if (::notificationListener.isInitialized) {
                notificationListener.openNotificationPage()
            }
        }
        binding.includeToolBar.searchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (::searchListener.isInitialized) {
                    searchListener.openSearchDetails(text = p0)
                }
                return true
            }

        })
        binding.createComplaint.onDebouncedListener {
            navController.safeNavigate(R.id.complaintFragment)
        }
        binding.includeToolBar.linearMyAccount.onDebouncedListener {
            if (::myAccountListener.isInitialized) {
                myAccountListener.openNotificationPage()
            }
        }
    }

    fun setNotificationListener(notificationListener: NotificationListener) {
        this.notificationListener = notificationListener
    }

    fun setMyAccountListener(myAccountListener: NotificationListener) {
        this.myAccountListener = myAccountListener
    }

    fun setSearchListener(searchListener: SearchListener) {
        this.searchListener = searchListener
    }

    override fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
    }

    override fun getToolbar(): Toolbar {
        return binding.toolBar
    }

    override fun showToolbar(b: Boolean) {
        if (b) binding.toolBar.visibility = View.VISIBLE
        else binding.toolBar.visibility = View.GONE
    }

    override fun showProfileImage(b: Boolean) {
        if (b)
            binding.includeToolBar.myAccount.visibility = View.VISIBLE
        else
            binding.includeToolBar.myAccount.visibility = View.GONE
    }

    override fun showSearchText(b: Boolean) {
        if (b)
            binding.includeToolBar.searchView.visibility = View.VISIBLE
        else
            binding.includeToolBar.searchView.visibility = View.GONE
    }

    override fun showNotificationIcon(b: Boolean) {
        if (b)
            binding.includeToolBar.icNotification.visibility = View.VISIBLE
        else
            binding.includeToolBar.icNotification.visibility = View.GONE
    }

    override fun changeTextTitle(title: String) {
        binding.includeToolBar.tvPageName.text = title
    }
//    private fun setNavigateComponentWithActionBar(){
//        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
//        val graphInflater = navHostFragment.navController.navInflater
//        val navGraph = graphInflater.inflate()
//    }
}