package com.cnm.birdview.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.cnm.birdview.R
import com.cnm.birdview.data.model.ProductsDetailResponse
import com.cnm.birdview.data.remote.network.NetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_products_detail.*
import java.text.DecimalFormat


class ProductsDetailFragment : RoundedBottomSheetDialogFragment() {
    private val disposable = CompositeDisposable()
    private lateinit var mContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val anim = AnimationUtils.loadAnimation(mContext, R.anim.anim_button_move)
        val product: Int = this.arguments!!.getInt("product")
        disposable.add(NetworkHelper.productsApi.getIdProducts(product)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                setView(it.body)
            }
        )
        tv_close.setOnClickListener {
            onDestroy()
            dismiss()
        }
        bt_buy.startAnimation(anim)
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }


    @SuppressLint("SetTextI18n")
    private fun setView(item: ProductsDetailResponse.Body) {
        with(item) {
            Glide.with(this@ProductsDetailFragment)
                .load(fullSizeImage)
                .into(iv_detail_image)
            tv_detail_title.text = title
            tv_detail_price.text = "${makeCommaNumber(price.toInt())}원"
            tv_detail_description.text = description.replace("\\n", "\n")
        }
    }

    private fun makeCommaNumber(input: Int): String {
        val formatter = DecimalFormat("###,###")
        return formatter.format(input)
    }
}

