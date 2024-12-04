package com.holat.login.models.cities

data class Cities(
    val data: List<CityData>,
    val message: String, // data retrieved
    val status: String // success
)