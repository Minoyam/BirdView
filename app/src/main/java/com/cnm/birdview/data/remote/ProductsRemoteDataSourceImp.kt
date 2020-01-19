package com.cnm.birdview.data.remote

import com.cnm.birdview.data.model.ProductsDetailResponse
import com.cnm.birdview.data.model.ProductsResponse
import com.cnm.birdview.data.remote.network.NetworkHelper
import io.reactivex.Observable

class ProductsRemoteDataSourceImp : ProductsRemoteDataSource {
    override fun getAllProducts(): Observable<ProductsResponse> {
        return NetworkHelper.productsApi.getAllProducts()
    }

    override fun getNextPageProducts(skin_type: String, page: Int): Observable<ProductsResponse> {
        return NetworkHelper.productsApi.getNextPageProducts(skin_type, page)
    }

    override fun getSearchProducts(search: String): Observable<ProductsResponse> {
        return NetworkHelper.productsApi.getSearchProducts(search)
    }

    override fun getIdProducts(id: Int): Observable<ProductsDetailResponse> {
        return NetworkHelper.productsApi.getIdProducts(id)
    }

    override fun getSortProducts(skin_type: String): Observable<ProductsResponse> {
        return NetworkHelper.productsApi.getSortProducts(skin_type)
    }

}