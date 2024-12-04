package com.holat.login.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri

/**
Created by Mohamed Mohamed Taha on 2/3/2024
 */

fun Activity.openLink(link:String){
    val intent = Intent()
    intent.setAction(Intent.ACTION_VIEW)
    intent.addCategory(Intent.CATEGORY_BROWSABLE)
    intent.setData(Uri.parse(link))
    startActivity(intent)
}