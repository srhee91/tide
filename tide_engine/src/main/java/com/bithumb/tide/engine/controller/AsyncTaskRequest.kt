package com.bithumb.tide.engine.controller

import com.fasterxml.jackson.databind.JsonNode

data class AsyncTaskRequest(
    val protocol: RequestProtocol,
    val taskName: String,
    val transactionId: String,
    val taskRequest: JsonNode,
    val callbackRequest: JsonNode?,
)

enum class RequestProtocol {
    HTTP_API_V1,
    UNKNOWN_PROTOCOL,
}
 data class HttpApiV1Request(
     val requestUrl: String,
 )