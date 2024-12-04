package com.holat.login.models.responses

data class ResponsesResponse(
    val data: List<DataResponses>,
    val links: Links,
    val meta: Meta
)