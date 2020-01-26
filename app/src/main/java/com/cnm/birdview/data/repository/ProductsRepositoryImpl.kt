package com.cnm.birdview.data.repository

import com.cnm.birdview.data.model.ProductsDetailResponse
import com.cnm.birdview.data.model.ProductsResponse
import com.cnm.birdview.data.remote.ProductsRemoteDataSource
import io.reactivex.Single

class ProductsRepositoryImpl(private val remoteDataSource: ProductsRemoteDataSource) :
    ProductsRepository {

    override fun getNextPageProducts(skin_type: String, page: Int): Single<ProductsResponse> {
        return remoteDataSource.getNextPageProducts(skin_type, page)
    }

    override fun getSearchProducts(search: String): Single<ProductsResponse> {
        return remoteDataSource.getSearchProducts(search)
    }

    override fun getIdProducts(id: Int): Single<ProductsDetailResponse> {
        return remoteDataSource.getIdProducts(id)
    }

    override fun getSortProducts(skin_type: String): Single<ProductsResponse> {
        return remoteDataSource.getSortProducts(skin_type)
    }

}