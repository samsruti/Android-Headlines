package com.byjus.headlines.assignment.samsruti.database

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.io.IOException

open class HeadlinesDaoTest{

    protected lateinit var dao: HeadlinesAppDao
    private lateinit var db: HeadlinesAppDataBase

    @Rule
    @JvmField
    val countingTaskExecutorRule = CountingTaskExecutorRule()


    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, HeadlinesAppDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.getDao()

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}