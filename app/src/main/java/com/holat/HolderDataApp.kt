package com.holat

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//import com.yariksoffice.lingver.Lingver

@HiltAndroidApp
class HolderDataApp : Application(){
    override fun onCreate() {
        super.onCreate()
     //  Lingver.init(this,"ar")
    }

}