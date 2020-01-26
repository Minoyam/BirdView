package com.cnm.birdview.data.remote

import com.cnm.birdview.data.model.ProductsDetailResponse
import com.cnm.birdview.data.model.ProductsResponse
import com.cnm.birdview.data.remote.network.NetworkHelper
import io.reactivex.Single

class ProductsRemoteDataSourceImpl : ProductsRemoteDataSource {

    override fun getNextPageProducts(skin_type: String, page: Int): Single<ProductsResponse> {
        return NetworkHelper.productsApi.getNextPageProducts(skin_type, page)
    }

    override fun getSearchProducts(search: String): Single<ProductsResponse> {
        return NetworkHelper.productsApi.getSearchProducts(search)
    }

    override fun getIdProducts(id: Int): Single<ProductsDetailResponse> {
        return NetworkHelper.productsApi.getIdProducts(id)
    }

    override fun getSortProducts(skin_type: String): Single<ProductsResponse> {
        return NetworkHelper.productsApi.getSortProducts(skin_type)
    }

}