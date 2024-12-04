package com.holat.login.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holat.login.models.ErrorDetails
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    fun callDatabase(enterMethod: () -> Boolean): Boolean {
        var resultFinal = false
        viewModelScope.launch {
            val result = async {
                enterMethod()
            }
            resultFinal = result.await()
        }
        return resultFinal

    }

    suspend fun <T> callDatabaseGeneric(enterMethod: suspend () -> T): T =
        viewModelScope.async { enterMethod() }.await()


//    fun callDatabase(enterMethod: () -> Boolean): String {
//        var resultFinal = false
//        viewModelScope.launch {
//            val result = async {
//                enterMethod()
//            }
//            resultFinal = result.await()
//        }
//        return resultFinal
//
//    }

    fun callDatabaseErrors(enterMethod: () -> ErrorDetails?): ErrorDetails? {
        var resultFinal: ErrorDetails? = null
        viewModelScope.launch {
            val result = async {
                enterMethod()
            }
            resultFinal = result.await()
        }
        return resultFinal

    }
}