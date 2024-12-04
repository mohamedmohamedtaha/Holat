package com.holat.login.models.regions

data class Regions(
    val data: List<RegionData>,
    val message: String, // data retrieved
    val status: String // success
)