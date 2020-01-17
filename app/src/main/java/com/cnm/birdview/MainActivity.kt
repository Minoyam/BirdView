package com.cnm.birdview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.cnm.birdview.network.NetworkHelper
import com.cnm.birdview.network.ProductsResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ProductsAdapter.ItemOnClickListener {

    private val productsAdapter = ProductsAdapter(this@MainActivity)
    private val disposable = CompositeDisposable()

    override fun itemOnClick(productsItem: ProductsResponse.Body) {
        val intent = Intent(this, ProductsDetail::class.java)
        intent.putExtra("product", productsItem.id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinnerItems = resources.getStringArray(R.array.sort_array)
        val spinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)

        sp_sort.adapter = spinnerAdapter
        rv_content.apply {
            adapter = productsAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }

        sp_sort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
                when (position) {
                    0 -> {

                    }
                    1 -> {

                    }
                    //...
                    else -> {

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
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
