package com.cnm.birdview.network

import io.reactivex.Observable
import retrofit2.http.GET

interface ProductsApi {
    @GET("products")
    fun getAllProducts():Observable<ProductsResponse>
}