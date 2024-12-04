package com.holat.login.base.network

import android.content.Context
import android.net.ConnectivityManager

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


inline fun <Result> Flow<NetworkStatus>.map(
    crossinline onUnavailable:suspend ()-> Result,
    crossinline onAvailable: suspend ()-> Result
): Flow<Result> = map { status->
    when(status){
        NetworkStatus.UnAvailable -> onUnavailable()
        NetworkStatus.Available -> onAvailable()
    }

}
class NetworkStatusTracker ( context: Context) {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

//    @OptIn(ExperimentalCoroutinesApi::class)
//    val networkStatus = callbackFlow<NetworkStatus> {
//        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
//            override fun onAvailable(network: Network) {
//                trySend(NetworkStatus.Available).isSuccess
//                println("OnAvailable")
//            }
//
//            override fun onUnavailable() {
//                trySend(NetworkStatus.UnAvailable).isSuccess
//                println("OnUnAvailable")
//            }
//
//            override fun onLost(network: Network) {
//                trySend(NetworkStatus.UnAvailable).isSuccess
//                println("onLost")
//            }
//
//        }
//        val request = NetworkRequest.Builder()
//            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//            .build()
//        connectivityManager.registerNetworkCallback(request,networkStatusCallback)
//
//    awaitClose {
//        connectivityManager.unregisterNetworkCallback(networkStatusCallback)
//    }
//    }

}














