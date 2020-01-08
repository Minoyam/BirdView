package com.cnm.birdview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.cnm.birdview.network.NetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val productsAdapter = ProductsAdapter()
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_content.apply {
            adapter = productsAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
        productsShow()

    }

    private fun productsShow() {
        disposable.add(
            NetworkHelper.productsApi.getAllProducts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    productsAdapter.setItem(it.body)
                }
        )
    }
}
