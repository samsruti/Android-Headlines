package com.byjus.headlines.assignment.samsruti.network

import java.lang.Exception

sealed class NetworkResult <out T: Any> {
    data class SuccessResponse<out T: Any>(val data: T): NetworkResult<T>()
    data class ErrorResponse(val exception: Exception): NetworkResult<Nothing>()
}