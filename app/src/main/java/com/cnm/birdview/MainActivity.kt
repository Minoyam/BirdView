package com.cnm.birdview

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.cnm.birdview.network.NetworkHelper
import com.cnm.birdview.network.ProductsResponse
import io.reactivex.Observable
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
        et_search.setOnEditorActionListener { _, i, _ ->
            when (i) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    if (et_search.text.toString().isNotEmpty()) {
                        productsShow(NetworkHelper.productsApi.getSearchProducts(et_search.text.toString()))
                    } else {
                        Toast.makeText(this, "검색 내용이 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                    et_search.hideKeyboard()
                }
            }
            true
        }
        productsShow(NetworkHelper.productsApi.getAllProducts())

    }

    private fun productsShow(sc: Observable<ProductsResponse>) {
        disposable.add(
            sc
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    productsAdapter.setItem(it.body)
                }
        )
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
