package com.holat.holat.data.models.tickets

data class Meta(
    val current_page: Int, // 1
    val from: Int, // 1
    val last_page: Int, // 1
    val links: List<Link>,
    val path: String, // http://hq-scph-vip.sama.gov.sa/hollat_sama/public/api/self_service/old-tickets
    val per_page: Int, // 10
    val to: Int, // 1
    val total: Int // 1
)