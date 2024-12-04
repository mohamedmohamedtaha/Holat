package com.holat.login.base

class ResponseBody<T : Any>(val code: Int, val type: String, val message: String, val detail: T?) {
}