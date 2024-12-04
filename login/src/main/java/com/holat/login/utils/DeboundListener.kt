package com.holat.login.utils

import android.view.View

inline fun View.onDebouncedListener(delayClick:Long = 500L,crossinline listener: (View)-> Unit){
    val enableAgain = Runnable { isEnabled = true }
    setOnClickListener{
        if (isEnabled){
            isEnabled = false
            postDelayed(enableAgain,delayClick)
            listener(it)
        }
    }

}