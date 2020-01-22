package com.byjus.headlines.assignment.samsruti.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.byjus.headlines.assignment.samsruti.database.DatabaseArticles
import com.byjus.headlines.assignment.samsruti.domain.News
import com.byjus.headlines.assignment.samsruti.ui.headlines.HeadlinesListAdapter

@BindingAdapter("recyclerViewData")
fun bindAllHeadlinesRecyclerView(recyclerView: RecyclerView, data: List<DatabaseArticles>?) {

    val adapter = recyclerView.adapter as HeadlinesListAdapter
    val domainModel = data?.map {
        News(
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
    adapter.submitList(domainModel)
}


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}
