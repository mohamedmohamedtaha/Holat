package com.holat.login.models.countries

data class Countries(
    val data: List<CountryData>,
    val message: String, // data retrieved
    val status: String // success
)