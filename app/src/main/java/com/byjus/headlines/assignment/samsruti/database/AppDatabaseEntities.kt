package com.byjus.headlines.assignment.samsruti.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.byjus.headlines.assignment.samsruti.domain.Headline
import com.byjus.headlines.assignment.samsruti.domain.Source


@Entity(tableName = "articles")
data class DatabaseArticles constructor(
    @Embedded
    val source: Source,
    @PrimaryKey
    val url: String,
    val author: String?,
    val content: String,
    val description: String?,
    val publishedAt: String,
    val title: String,
    val urlToImage: String?
)


fun List<DatabaseArticles>.asDomainModel(): List<Headline> {
    return map {
        Headline(
            author = it.author,
            content = it.content,
            description = it.description,
            publishedAt = it.publishedAt,
            source = it.source,
            title = it.title,
            url = it.url,
            urlToImage = it.urlToImage
        )
    }
}