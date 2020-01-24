package com.byjus.headlines.assignment.samsruti.utils

import com.byjus.headlines.assignment.samsruti.domain.ApiResponse
import com.byjus.headlines.assignment.samsruti.repository.HeadlineRepository
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.net.HttpURLConnection

fun generateDummyResponse(mockWebServer: MockWebServer, repository: HeadlineRepository, connectionType: Int = HttpURLConnection.HTTP_OK): ApiResponse? {
    mockHttpResponse("topheadlines.json", connectionType, mockWebServer)

    return runBlocking {
        repository.fetchArticles()
    }
}

fun mockHttpResponse(path: String, responseCode: Int, mockWebServer: MockWebServer) = mockWebServer.enqueue(
    MockResponse()
        .setResponseCode(responseCode)
        .setBody(JsonUtils.getJSON(path))
)