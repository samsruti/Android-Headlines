package com.byjus.headlines.assignment.samsruti.domain


import android.os.Parcelable
import com.byjus.headlines.assignment.samsruti.database.DatabaseArticles
import com.byjus.headlines.assignment.samsruti.util.formatDate
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

enum class ApiStatus {LOADING, DONE, ERROR, UNSUCCESSFUL, UNKNOWN_HOST}

@Parcelize
data class Headline(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?
) : Parcelable {
    val publishedDate: String
        get() = publishedAt.formatDate()
}

@JsonClass(generateAdapter = true)
data class NetworkNews(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?
)

@Parcelize
data class Source(

    val id: String?,
    val name: String?
) : Parcelable


@JsonClass(generateAdapter = true)
data class ApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NetworkNews>
)

fun ApiResponse.asDatabaseModel(): Array<DatabaseArticles> {
    return articles.map {
        DatabaseArticles(
            author = it.author,
            content = it.content,
            description = it.description,
            publishedAt = it.publishedAt,
            source = it.source,
            title = it.title,
            url = it.url,
            urlToImage = it.urlToImage
        )
    }.toTypedArray()
}
