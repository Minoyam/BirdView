package com.cnm.birdview.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi {
    @GET("products")
    fun getAllProducts(): Observable<ProductsResponse>

    @GET("products?search")
    fun getSearchProducts(@Query("search") search: String): Observable<ProductsResponse>
}