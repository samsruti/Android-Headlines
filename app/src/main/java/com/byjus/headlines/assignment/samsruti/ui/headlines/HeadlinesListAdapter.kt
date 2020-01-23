package com.byjus.headlines.assignment.samsruti.ui.headlines


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.byjus.headlines.assignment.samsruti.databinding.ListItemHeadlinesBinding
import com.byjus.headlines.assignment.samsruti.domain.Headline

class HeadlinesListAdapter (val clickListener: CallBackClickListener):
    ListAdapter<Headline, HeadlinesListAdapter.HeadlinesViewHolder>(DiffCallback) {

    class HeadlinesViewHolder(private var dataBinding: ListItemHeadlinesBinding)
        : RecyclerView.ViewHolder(dataBinding.root) {

        fun bind(networkNews: Headline){
            dataBinding.headline = networkNews
            dataBinding.executePendingBindings()
        }

    }

    companion object DiffCallback: DiffUtil.ItemCallback<Headline>(){
        override fun areItemsTheSame(oldItem: Headline, newItem: Headline): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Headline, newItem: Headline): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlinesViewHolder {
        return HeadlinesViewHolder(ListItemHeadlinesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HeadlinesViewHolder, position: Int) {
        val currentNews = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener.onClick(currentNews)
//            Toast.makeText(holder.itemView.context,currentNews.toString(),Toast.LENGTH_LONG).show()

        }
        holder.bind(currentNews)
    }

    class CallBackClickListener(val clickListener: (networkNews: Headline) -> Unit) {
        fun onClick(networkNews: Headline) = clickListener(networkNews)
    }



}