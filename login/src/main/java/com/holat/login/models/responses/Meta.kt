package com.holat.login.models.responses

data class Meta(
    val current_page: Int, // 1
    val from: Int, // 1
    val last_page: Int, // 1
    val links: List<Link>,
    val path: String, // http://hq-scph-vip.sama.gov.sa/hollat_sama/public/api/getToCustomerReplies
    val per_page: String, // 5
    val to: Int, // 2
    val total: Int // 2
)