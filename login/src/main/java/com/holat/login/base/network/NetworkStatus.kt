package com.holat.login.base.network

sealed class NetworkStatus{
    object Available : NetworkStatus()
    object UnAvailable : NetworkStatus()
}