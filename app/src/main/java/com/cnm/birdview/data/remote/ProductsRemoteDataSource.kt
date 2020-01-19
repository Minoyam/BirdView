package com.cnm.birdview.data.remote

import com.cnm.birdview.data.model.ProductsDetailResponse
import com.cnm.birdview.data.model.ProductsResponse
import io.reactivex.Observable

interface ProductsRemoteDataSource {

    fun getAllProducts(): Observable<ProductsResponse>

    fun getNextPageProducts(
        skin_type: String, page: Int
    ): Observable<ProductsResponse>

    fun getSearchProducts(search: String): Observable<ProductsResponse>

    fun getIdProducts(id: Int): Observable<ProductsDetailResponse>

    fun getSortProducts(skin_type: String): Observable<ProductsResponse>
}