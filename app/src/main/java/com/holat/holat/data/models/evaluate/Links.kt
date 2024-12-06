package com.holat.holat.data.models.evaluate

data class Links(
    val first: String, // http://hq-scph-vip.sama.gov.sa/hollat_sama/public/api/self_service/questions?page=1
    val last: String, // http://hq-scph-vip.sama.gov.sa/hollat_sama/public/api/self_service/questions?page=1
    val next: Any, // null
    val prev: Any // null
)