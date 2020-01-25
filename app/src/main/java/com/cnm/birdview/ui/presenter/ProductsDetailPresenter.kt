package com.cnm.birdview.ui.presenter

import com.cnm.birdview.data.remote.network.NetworkHelper
import com.cnm.birdview.ui.contract.ProductsDetailContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.text.DecimalFormat

class ProductsDetailPresenter(private val view: ProductsDetailContract.View) :
    ProductsDetailContract.Presenter {


    private val disposable = CompositeDisposable()

    override fun detailApiCall(product: Int) {
        disposable.add(
            NetworkHelper.productsApi.getIdProducts(product)
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