package com.byjus.headlines.assignment.samsruti.base

import com.byjus.headlines.assignment.samsruti.database.HeadlinesAppDao
import com.byjus.headlines.assignment.samsruti.repository.HeadlineRepository
import com.byjus.headlines.assignment.samsruti.utils.JsonUtils.Companion.getJSON
import com.nhaarman.mockitokotlin2.mock

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

open class BaseMockServerTest : KoinTest {

    protected val appHeadlineRepository by inject<HeadlineRepository>()
    protected var daoMocked = mock<HeadlinesAppDao>()

    protected lateinit var mockServer: MockWebServer

    @Before
    open fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    @After
    open fun tearDown() {
        mockServer.shutdown()
        stopKoin()
    }

    fun mockHttpResponse(path: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJSON(path))
    )
}