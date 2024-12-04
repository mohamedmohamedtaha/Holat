package com.holat.login.utils

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.holat.login.R


fun View.showSnackBar(message: String) {
    if (rootView != null) {
        val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        (snackBar.view as Snackbar.SnackbarLayout).let { snackBarLayout ->
            snackBarLayout.removeAllViews()
            snackBarLayout.setPadding(0, 0, 0, 0)
            snackBarLayout.setBackgroundColor(Color.TRANSPARENT)
            LayoutInflater.from(context).inflate(R.layout.snackbar_top, snackBarLayout)
                .let { layout ->
                    val tvTitle = layout.findViewById(R.id.tvTitle) as AppCompatTextView
                    tvTitle.text = message
                }
            val params = snackBarLayout.layoutParams
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            if (params is FrameLayout.LayoutParams) {
                params.gravity = Gravity.TOP
            } else if (params is CoordinatorLayout.LayoutParams) {
                params.gravity = Gravity.TOP
            }
            snackBarLayout.layoutParams = params
        }
        snackBar.show()
    }
}

//fun MaterialButton.setShowProgress(showProgress: Boolean, textSource: String = "") {
//    iconGravity = MaterialButton.ICON_GRAVITY_TEXT_START
//    isClickable = showProgress == false
//    text = if (showProgress) "" else textSource
//    icon = if (showProgress) {
//        CircularProgressDrawable(context).apply {
//            setStyle(CircularProgressDrawable.LARGE)
//            setColorSchemeColors(ContextCompat.getColor(context, android.R.color.white))
//            start()
//        }
//    } else null
//
//    icon?.let { // execute if icon is not null
//
//        icon.callback = object : Drawable.Callback {
//            override fun invalidateDrawable(p0: Drawable) {
//                this@setShowProgress.invalidate()
//            }
//
//            override fun scheduleDrawable(p0: Drawable, p1: Runnable, p2: Long) {
//
//            }
//
//            override fun unscheduleDrawable(p0: Drawable, p1: Runnable) {
//            }
//
//        }
//    }
//}