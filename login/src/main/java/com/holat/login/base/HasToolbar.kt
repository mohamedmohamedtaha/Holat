package com.holat.login.base

import androidx.appcompat.widget.Toolbar

interface HasToolbar {
    fun setToolbar(toolbar: Toolbar)
    fun getToolbar(): Toolbar
    fun showToolbar(b:Boolean)
    fun showProfileImage(b:Boolean)
    fun showSearchText(b:Boolean)
    fun showNotificationIcon(b:Boolean)
    fun changeTextTitle(title:String)
}