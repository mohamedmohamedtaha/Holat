package com.holat.login.models

data class ErrorResponse(
    val code: Int,
    val type: String,
    val message: String,
    val errors: Any,
    val details: String
)