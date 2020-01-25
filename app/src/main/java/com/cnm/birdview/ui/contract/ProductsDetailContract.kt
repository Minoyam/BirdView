package com.cnm.birdview.ui.contract

import com.cnm.birdview.data.model.ProductsDetailResponse

interface ProductsDetailContract {
    interface View {
        fun setView(item: ProductsDetailResponse.Body)
    }

    interface Presenter {
        fun detailApiCall(product: Int)

        fun makeCommaNumber(input: Int): String

        fun disposableClear()
    }
}