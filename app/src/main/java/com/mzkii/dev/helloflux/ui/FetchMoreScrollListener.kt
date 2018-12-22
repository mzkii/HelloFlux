package com.mzkii.dev.helloflux.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class FetchMoreScrollListener : RecyclerView.OnScrollListener(), LoadMoreScrollListener {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleThreshold = 5
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val firstVisibleItem = layoutManager!!.findFirstVisibleItemPosition()
        val totalItemCount = recyclerView.childCount
        val layoutManagerItemCount = layoutManager.itemCount
        if (firstVisibleItem + totalItemCount + visibleThreshold >= layoutManagerItemCount) {
            onLoadMore()
        }
    }
}

interface LoadMoreScrollListener {
    fun onLoadMore()
}