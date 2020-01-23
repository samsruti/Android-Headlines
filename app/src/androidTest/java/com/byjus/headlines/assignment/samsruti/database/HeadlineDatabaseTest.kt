package com.byjus.headlines.assignment.samsruti.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.byjus.headlines.assignment.samsruti.domain.Source
import com.byjus.headlines.assignment.samsruti.utils.getLiveDataValue
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HeadlineDatabaseTest : HeadlinesDaoTest(){

    private val dbHeadline1 = DatabaseArticles(
        author = "Lisa Respers France, CNN",
        content = "How much do we love Brad Pitt?",
        description = "How much do we love Brad Pitt?",
        publishedAt = "2020-01-23T14:25:00Z",
        source = Source(id = "cnn", name = "CNN"),
        title = "Brad Pitt is the new toastmaster of awards season - CNN",
        url = "https://www.cnn.com/2020/01/23/entertainment/brad-pitt-speeches/index.html",
        urlToImage =  "https://cdn.cnn.com/cnnnext/dam/assets/200123082407-brad-pitt-santa-barbara-film-festival-super-tease.jpg"
    )

    private val dbHeadline2 = DatabaseArticles(
        author = "Rohan Nadkarni",
        content = "Let me start by saying I’m a man of science. Climate change is real, and corporations should be held responsible. Vaccinate your children (and pets!). Mayo helps achieve great browning for your grilled cheeses. I’m not anti-analytics. And I’m not even anti–lo… [+4897 chars]",
        description = "Zion Williamson lived up to the hype in his NBA debut, but the Pelicans' minutes restriction took away from a memorable night.",
        publishedAt = "2020-01-23T14:25:00Z",
        source = Source(id = null, name = "Si.com"),
        title = "Dear Adam Silver, Please Do Something About Minutes Restrictions - Sports Illustrated",
        url = "https://www.si.com/nba/2020/01/23/zion-williamson-adam-silver-pelicans-minutes-restriction",
        urlToImage = "https://www.si.com/.image/t_share/MTY5OTQ1ODExNTQxNTY2ODQx/zion-williamson-pelicans-spurs-debut.jpg"
    )


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dbHeadlineList = mutableListOf(dbHeadline1)

    @Before
    fun initDb(){
        runBlocking {
            dao.insertAllHeadlines(*dbHeadlineList.toTypedArray())
        }
    }

    @Test
    @Throws(Exception::class)
    fun insertHeadline() {
        dbHeadlineList.add(dbHeadline2)
        runBlocking {
            dao.insertAllHeadlines(*dbHeadlineList.toTypedArray())
        }
        val headlines =
            getLiveDataValue(dao.getHeadlines())
        assert (!headlines.isNullOrEmpty())
        Assert.assertEquals(headlines.size, 2)
    }

    @Test
    @Throws(Exception::class)
    fun getAllHeadlines() {
        val headlines =
            getLiveDataValue(dao.getHeadlines())
        Assert.assertEquals(headlines.size, 1)
        insertHeadline()

    }
}