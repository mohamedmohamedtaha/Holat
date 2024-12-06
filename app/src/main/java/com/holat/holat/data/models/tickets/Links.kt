package com.holat.holat.data.models.tickets

data class Links(
    val first: String, // http://hq-scph-vip.sama.gov.sa/hollat_sama/public/api/self_service/old-tickets?page=1
    val last: String, // http://hq-scph-vip.sama.gov.sa/hollat_sama/public/api/self_service/old-tickets?page=1
    val next: Any, // null
    val prev: Any // null
)