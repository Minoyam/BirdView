package com.cnm.birdview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.cnm.birdview.network.NetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_products_detail.*

class ProductsDetail : AppCompatActivity() {
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_detail)
        val product = intent.getSerializableExtra("product") as Int
        disposable.add(NetworkHelper.productsApi.getIdProducts(product)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Glide.with(this)
                    .load(it.body.fullSizeImage)
                    .into(iv_detail_image)
                tv_detail_title.text = it.body.title
                tv_detail_price.text = "${it.body.price}원"
                tv_detail_description.text = it.body.description
            }
        )

    }
}
