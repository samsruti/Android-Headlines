package com.byjus.headlines.assignment.samsruti.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.byjus.headlines.assignment.samsruti.domain.Source


@Entity(tableName = "articles")
data class DatabaseArticles constructor(
    @Embedded
    val source: Source,
    @PrimaryKey
    val url: String,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val urlToImage: String
)
