package com.byjus.headlines.assignment.samsruti.domain

import android.os.Parcelable
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
    val articles: List<News>,
    val status: String,
    val totalResults: Int
)