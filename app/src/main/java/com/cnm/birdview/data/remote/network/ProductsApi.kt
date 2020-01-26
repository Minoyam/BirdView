package com.cnm.birdview.data.remote.network

import com.cnm.birdview.data.model.ProductsDetailResponse
import com.cnm.birdview.data.model.ProductsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi {

    @GET("products")
    fun getNextPageProducts(
        @Query("skin_type") skin_type: String, @Query("page") page: Int
    ): Single<ProductsResponse>

    @GET("products")
    fun getSearchProducts(@Query("search") search: String): Single<ProductsResponse>

    @GET("products/{id}")
    fun getIdProducts(@Path("id") id: Int): Single<ProductsDetailResponse>

    @GET("products")
    fun getSortProducts(@Query("skin_type") skin_type: String): Single<ProductsResponse>
}