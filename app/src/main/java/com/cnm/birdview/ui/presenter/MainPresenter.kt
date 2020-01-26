package com.cnm.birdview.ui.presenter

import com.cnm.birdview.data.model.ProductsResponse
import com.cnm.birdview.data.remote.ProductsRemoteDataSourceImpl
import com.cnm.birdview.data.repository.ProductsRepositoryImpl
import com.cnm.birdview.ui.contract.MainContract
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    private val disposable = CompositeDisposable()
    private val productsRepository: ProductsRepositoryImpl by lazy {
        ProductsRepositoryImpl(ProductsRemoteDataSourceImpl())
    }

    override fun getNextPageProducts(skin_type: String, page: Int) =
        callApi(productsRepository.getNextPageProducts(skin_type, page), false)


    override fun getSearchProducts(search: String) {
        if (search.isEmpty()) {
            view.showErrorEmptyQuery()
        } else {
            view.scrollClear()
            callApi(productsRepository.getSearchProducts(search))
        }
    }

    override fun getSortProducts(skin_type: String) {
        view.scrollClear(skin_type)
        callApi(productsRepository.getSortProducts(skin_type))
    }

    override fun disposableClear() {
        disposable.clear()
    }

    private fun callApi(api: Single<ProductsResponse>, clearBoolean: Boolean = true) {
        disposable.add(api
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view.showProgress()
            }
            .doAfterTerminate {
                view.hideProgress()
            }
            .subscribe({
                view.setItem(it.body, clearBoolean)
            }, {
                view.showErrorEmptyResult()
            })

        )
    }

}