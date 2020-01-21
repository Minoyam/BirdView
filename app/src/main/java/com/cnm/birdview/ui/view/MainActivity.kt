package com.cnm.birdview.ui.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.cnm.birdview.R
import com.cnm.birdview.data.model.ProductsResponse
import com.cnm.birdview.ui.contract.MainContract
import com.cnm.birdview.ui.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    ProductsAdapter.ItemOnClickListener, MainContract.View {

    private val detailFragment = ProductsDetailFragment()
    private val productsAdapter = ProductsAdapter(this@MainActivity)
    private val presenter: MainContract.Presenter by lazy {
        MainPresenter(this@MainActivity)
    }
    private lateinit var scrollListener: EndlessScrollListener

    override fun itemOnClick(productsItem: ProductsResponse.Body) {
        val bundle: Bundle = Bundle().apply {
            this.putSerializable("product", productsItem.id)
        }
        detailFragment.arguments = bundle

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinnerItems = resources.getStringArray(R.array.sort_array)
        val spinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        val gridlayoutManager = GridLayoutManager(this@MainActivity, 2)

        sp_sort.adapter = spinnerAdapter

        setScroll(gridlayoutManager)

        rv_content.apply {
            adapter = productsAdapter
            layoutManager = gridlayoutManager
            addOnScrollListener(scrollListener)
        }

        sp_sort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                spinnerSelected(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        et_search.setOnClickListener {
            // showEmptyLayout()
            TODO("백버튼/ 다른곳 클릭했을때 키보드가 내려가면서 화면이 다시 보여야함")
        }

        et_search.setOnEditorActionListener { _, i, _ ->
            when (i) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    scrollListener.clear()
                    presenter.getSearchProducts(et_search.text.toString())
                    et_search.hideKeyboard()
                }
            }
            true
        }
        presenter.getAllProducts()
    }


    override fun showErrorEmptyQuery() =
        Toast.makeText(this, "검색 내용이 없습니다.", Toast.LENGTH_SHORT).show()

    override fun showErrorEmptyResult() =
        Toast.makeText(this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()

    override fun scrollClear(type: String) =
        scrollListener.clear(type)

    override fun setItem(items: List<ProductsResponse.Body>, clearBoolean: Boolean) {
        scrollListener.loading = false
        productsAdapter.setItem(items, clearBoolean)
    }
/*
    override fun onBackPressed() {
        super.onBackPressed()
        if (et_search.isFocusable) {
            Log.e("d", "forus")
            et_search.isCursorVisible = false
            showMainLayout()
        }
    }

 */


    private fun spinnerSelected(position: Int) {
        when (position) {
            0, 1 -> {
                presenter.getSortProducts(OILY)
            }
            2 -> {
                presenter.getSortProducts(DRY)
            }
            else -> {
                presenter.getSortProducts(SENSITIVE)
            }
        }
    }


    private fun setScroll(gridlayoutManager: GridLayoutManager) {
        scrollListener =
            EndlessScrollListener(gridlayoutManager) { skinType, page ->
                presenter.getNextPageProducts(skinType, page)
            }
    }

    private fun showEmptyLayout() {
        fl_empty.visibility = View.VISIBLE
        cl_full.visibility = View.GONE
    }

    private fun showMainLayout() {
        fl_empty.visibility = View.GONE
        cl_full.visibility = View.VISIBLE
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
        showMainLayout()

    }


    companion object {
        const val OILY = "oily"
        const val DRY = "dry"
        const val SENSITIVE = "sensitive"
    }
}
