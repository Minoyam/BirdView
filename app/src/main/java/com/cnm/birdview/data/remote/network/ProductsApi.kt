package com.cnm.birdview.data.remote.network

import com.cnm.birdview.data.model.ProductsDetailResponse
import com.cnm.birdview.data.model.ProductsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi {
    @GET("products")
    fun getAllProducts(): Observable<ProductsResponse>

    @GET("products?skin_type?page")
    fun getNextPageProducts(
        @Query("skin_type") skin_type: String, @Query("page") page: Int
    ): Observable<ProductsResponse>

    @GET("products?search")
    fun getSearchProducts(@Query("search") search: String): Observable<ProductsResponse>

    @GET("products/{id}")
    fun getIdProducts(@Path("id") id: Int): Observable<ProductsDetailResponse>

    @GET("products?skin_type")
    fun getSortProducts(@Query("skin_type") skin_type: String): Observable<ProductsResponse>
}