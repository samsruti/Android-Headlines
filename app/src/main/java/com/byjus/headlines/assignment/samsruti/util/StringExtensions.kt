package com.byjus.headlines.assignment.samsruti.util

import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate(): String{
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val formattedDate: Date? = dateFormat.parse(this)

    return SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(formattedDate!!)
}