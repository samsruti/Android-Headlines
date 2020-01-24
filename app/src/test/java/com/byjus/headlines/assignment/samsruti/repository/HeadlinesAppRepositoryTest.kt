package com.byjus.headlines.assignment.samsruti.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.byjus.headlines.assignment.samsruti.utils.generateDummyResponse
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@Rule
var rule: TestRule = InstantTaskExecutorRule()

@RunWith(JUnit4::class)
class HeadlinesAppRepositoryTest : BaseRepositoryMockTest() {


    val dummyResponse = generateDummyResponse(repository = appHeadlineRepository, mockWebServer = mockServer)

    @Test
    fun fetch_headlines_result_ok() {
        Assert.assertNotNull(dummyResponse !!)
        Assert.assertEquals(dummyResponse.articles.isEmpty(), false)
        Assert.assertEquals(dummyResponse.status,"ok")
    }

    @Test
    fun single_first_news_source_valid_ok() {
        val firstNewsHeadline = dummyResponse!!.articles[0]
        Assert.assertEquals(firstNewsHeadline.source.id, null)
        Assert.assertEquals(firstNewsHeadline.source.name, "Ndtv.com")

    }

//    TODO: Add more tests

}