package com.cnm.birdview.ui.contract

import com.cnm.birdview.data.model.ProductsResponse

interface MainContract {

    interface View {
        fun setItem(items: List<ProductsResponse.Body>, clearBoolean: Boolean)

        fun showErrorEmptyQuery()

        fun showErrorEmptyResult()

        fun scrollClear(type: String = "oily")
    }

    interface Presenter {
        fun getAllProducts()

        fun getNextPageProducts(skin_type: String, page: Int)

        fun getSearchProducts(search: String)

        fun getSortProducts(skin_type: String)

        fun disposableClear()
    }
}