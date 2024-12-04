package com.holat.login.utils

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun NavController.safeNavigate(direction:NavDirections){
    Log.e("clickTag","ClickHappened")
    currentDestination?.getAction(direction.actionId)?.run {
        Log.e("clickTag","ClickPropagated")
        navigate(direction)
    }
}
fun NavController.safeNavigate(action:Int){
    Log.e("clickTag","ClickHappened")
    if (currentDestination?.id != action){
        navigate(action)
        Log.e("clickTag","ClickPropagated")
    }else{
        navigateUp()
    }
//    currentDestination?.id?.run {
//        Log.e("clickTag","ClickPropagated")
//        navigate(action)
//    }
}

fun NavController.safeNavigateUp(){
    Log.e("clickTag","ClickHappened")
    currentDestination?.run {
        Log.e("clickTag","ClickPropagated")
        navigateUp()
    }
}