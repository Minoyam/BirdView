package com.cnm.birdview.ui.view

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

open class EndlessScrollListener(
    private val manager: GridLayoutManager,
    private val lastItemListener: (String,Int) -> Unit
) : RecyclerView.OnScrollListener() {

    private var page = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var pastVisibleItems: Int = 0
    private var skinType = "oily"
    var loading = false

    fun clear(type : String = "oily") {
        page = 0
        skinType = type
        loading = false
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy < 0)
            return

        visibleItemCount = manager.childCount
        totalItemCount = manager.itemCount
        pastVisibleItems = manager.findFirstVisibleItemPosition()
        if (!loading) {
            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                loading = true
                page++
                lastItemListener(skinType,page)
            }
        }
    }
}