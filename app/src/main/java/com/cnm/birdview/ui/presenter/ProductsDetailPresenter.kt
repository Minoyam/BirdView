package com.cnm.birdview.ui.presenter

import com.cnm.birdview.data.remote.ProductsRemoteDataSourceImpl
import com.cnm.birdview.data.repository.ProductsRepositoryImpl
import com.cnm.birdview.ui.contract.ProductsDetailContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.text.DecimalFormat

class ProductsDetailPresenter(private val view: ProductsDetailContract.View) :
    ProductsDetailContract.Presenter {


    private val disposable = CompositeDisposable()
    private val productsRepository: ProductsRepositoryImpl by lazy {
        ProductsRepositoryImpl(ProductsRemoteDataSourceImpl())
    }

    override fun detailApiCall(product: Int) {
        disposable.add(
            productsRepository.getIdProducts(product)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.setView(it.body)
                }
        )
    }

    override fun disposableClear() {
        disposable.clear()
    }

    override fun makeCommaNumber(input: Int): String {
        val formatter = DecimalFormat("###,###")
        return formatter.format(input)
    }


}