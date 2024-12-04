package com.holat.login.models.responses

data class Links(
    val first: String, // http://hq-scph-vip.sama.gov.sa/hollat_sama/public/api/getToCustomerReplies?page=1
    val last: String, // http://hq-scph-vip.sama.gov.sa/hollat_sama/public/api/getToCustomerReplies?page=1
    val next: Any, // null
    val prev: Any // null
)