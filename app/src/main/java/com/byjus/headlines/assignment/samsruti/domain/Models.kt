package com.byjus.headlines.assignment.samsruti.domain

import android.os.Parcelable
import com.byjus.headlines.assignment.samsruti.database.DatabaseArticles
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
) : Parcelable

@Parcelize
data class Source(
    val id: String?,
    val name: String?
) : Parcelable


data class ApiResponse(
    @SerializedName("status")
    val status: String = "",

    @SerializedName("totalResults")
    val results: Int = 0,

    @SerializedName("articles")
    val articles: List<DatabaseArticles> = emptyList()
)