package com.holat.login.utils.listener

import android.view.View

/**
Created by Mohamed Mohamed Taha on 12/11/2023
 */
interface ClickListener<T> {
    fun onClick(view: View, t:T)
}

