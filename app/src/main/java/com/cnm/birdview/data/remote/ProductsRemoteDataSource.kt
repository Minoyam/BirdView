package com.cnm.birdview.data.remote

import com.cnm.birdview.data.model.ProductsDetailResponse
import com.cnm.birdview.data.model.ProductsResponse
import io.reactivex.Single

interface ProductsRemoteDataSource {

    fun getNextPageProducts(
        skin_type: String, page: Int
    ): Single<ProductsResponse>

    fun getSearchProducts(search: String): Single<ProductsResponse>

    fun getIdProducts(id: Int): Single<ProductsDetailResponse>

    fun getSortProducts(skin_type: String): Single<ProductsResponse>
}