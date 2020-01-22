package com.cnm.birdview.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.cnm.birdview.R
import com.cnm.birdview.data.model.ProductsDetailResponse
import com.cnm.birdview.data.remote.network.NetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_products_detail.*

class ProductsDetailFragment : RoundedBottomSheetDialogFragment() {
    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val product: Int = this.arguments!!.getInt("product")

        disposable.add(NetworkHelper.productsApi.getIdProducts(product)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                setView(it)
            }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun setView(item: ProductsDetailResponse) {
        with(item) {
            Glide.with(this@ProductsDetailFragment)
                .load(body.fullSizeImage)
                .into(iv_detail_image)
            tv_detail_title.text = body.title
            tv_detail_price.text = "${body.price}원"
            tv_detail_description.text = body.description
        }
    }
}
