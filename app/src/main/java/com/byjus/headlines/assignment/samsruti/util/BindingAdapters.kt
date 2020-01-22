package com.byjus.headlines.assignment.samsruti.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.byjus.headlines.assignment.samsruti.domain.News
import com.byjus.headlines.assignment.samsruti.ui.headlines.HeadlinesListAdapter

@BindingAdapter("recyclerViewData")
fun bindAllHeadlinesRecyclerView(recyclerView: RecyclerView, data: List<News>?) {

    val adapter = recyclerView.adapter as HeadlinesListAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}
