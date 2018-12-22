package com.mzkii.dev.helloflux.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mzkii.dev.helloflux.data.api.response.Repository
import com.mzkii.dev.helloflux.databinding.ListRepositoryBinding

class RepositoryAdapter(
    private val onCardClick: ((Repository) -> Unit)
) : ListAdapter<Repository, RepositoryAdapter.ViewHolder>(ITEM_CALLBACK) {

    companion object {
        private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(
                oldItem: Repository,
                newItem: Repository
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Repository,
                newItem: Repository
            ): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ListRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        getItem(position).let { item ->
            holder.binding.title.text = item.fullName ?: "---"
            holder.binding.description.text = item.description ?: "---"
            holder.binding.card.setOnClickListener {
                onCardClick.invoke(item)
            }
        }
    }

    class ViewHolder(val binding: ListRepositoryBinding) : RecyclerView.ViewHolder(binding.root)
}