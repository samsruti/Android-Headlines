package com.byjus.headlines.assignment.samsruti.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.byjus.headlines.assignment.samsruti.R
//
//@BindingAdapter("recyclerViewData")
//fun bindAllHeadlinesRecyclerView(recyclerView: RecyclerView, data: List<DatabaseArticles>?) {
//
//    val adapter = recyclerView.adapter as HeadlinesListAdapter
//    adapter.submitList(domainModel)
//}


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.color.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imgView)
    }
}
