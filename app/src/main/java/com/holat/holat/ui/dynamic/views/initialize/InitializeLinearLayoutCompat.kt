package com.holat.holat.ui.dynamic.views.initialize

import androidx.appcompat.widget.LinearLayoutCompat

/**
Created by Mohamed Mohamed Taha on 3/12/2024
 */
fun initializeLinearLayoutCompat(): LinearLayoutCompat.LayoutParams {
    val layoutParams = LinearLayoutCompat.LayoutParams(
        LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
        LinearLayoutCompat.LayoutParams.WRAP_CONTENT
    )
    layoutParams.setMargins(18, 18, 18, layoutParams.bottomMargin)
    return layoutParams
}