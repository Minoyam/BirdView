package com.cnm.birdview

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

open class EndlessScrollListener(
    private val manager: GridLayoutManager,
    private val lastItemListener: (Int) -> Unit
) : RecyclerView.OnScrollListener() {

    private var page = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var pastVisibleItems: Int = 0
    var loading = false

    fun clear() {
        page = 0
        loading = false
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        //check for scroll down

        if (dy < 0)
            return

        visibleItemCount = manager.childCount
        totalItemCount = manager.itemCount
        pastVisibleItems = manager.findFirstVisibleItemPosition()
        if (!loading) {
            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                loading = true
                page++
                lastItemListener(page)
            }
        }
    }
}