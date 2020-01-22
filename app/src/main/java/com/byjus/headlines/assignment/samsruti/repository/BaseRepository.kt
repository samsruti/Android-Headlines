package com.byjus.headlines.assignment.samsruti.repository

import com.byjus.headlines.assignment.samsruti.network.NetworkResult
import retrofit2.Response

import timber.log.Timber
import java.io.IOException

open class BaseRepository {
    suspend fun <T : Any> validApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result : NetworkResult<T> = validApiNetworkResult(call,errorMessage)
        var data : T? = null

        when(result) {
            is NetworkResult.SuccessResponse ->
                data = result.data
            is NetworkResult.ErrorResponse -> {
                Timber.d("Network Repository $errorMessage \nException - ${result.exception}")
            }
        }

        return data

    }

    private suspend fun <T: Any> validApiNetworkResult(call: suspend ()-> Response<T>, errorMessage: String) : NetworkResult<T>{
        val response = call.invoke()
        if(response.isSuccessful)
            return NetworkResult.SuccessResponse(response.body()!!)

        return NetworkResult.ErrorResponse(IOException(errorMessage))
    }
}