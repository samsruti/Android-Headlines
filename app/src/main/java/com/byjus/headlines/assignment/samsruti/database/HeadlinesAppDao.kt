package com.byjus.headlines.assignment.samsruti.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeadlinesAppDao {

    @Query("SELECT * FROM articles ORDER BY publishedAt DESC")
    fun getHeadlines(): LiveData<List<DatabaseArticles>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHeadlines(vararg posts: DatabaseArticles)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(headline: DatabaseArticles)

}