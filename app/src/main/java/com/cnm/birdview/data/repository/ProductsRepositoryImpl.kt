package com.cnm.birdview.data.repository

import com.cnm.birdview.data.model.ProductsDetailResponse
import com.cnm.birdview.data.model.ProductsResponse
import com.cnm.birdview.data.remote.ProductsRemoteDataSource
import io.reactivex.Observable

class ProductsRepositoryImpl(private val remoteDataSource: ProductsRemoteDataSource) :
    ProductsRepository {
    override fun getAllProducts(): Observable<ProductsResponse> {
        return remoteDataSource.getAllProducts()
    }

    override fun getNextPageProducts(skin_type: String, page: Int): Observable<ProductsResponse> {
        return remoteDataSource.getNextPageProducts(skin_type, page)
    }

    override fun getSearchProducts(search: String): Observable<ProductsResponse> {
        return remoteDataSource.getSearchProducts(search)
    }

    override fun getIdProducts(id: Int): Observable<ProductsDetailResponse> {
        return remoteDataSource.getIdProducts(id)
    }

    override fun getSortProducts(skin_type: String): Observable<ProductsResponse> {
        return remoteDataSource.getSortProducts(skin_type)
    }

}