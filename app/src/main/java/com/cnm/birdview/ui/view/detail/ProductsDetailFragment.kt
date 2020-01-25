package com.cnm.birdview.ui.view.detail

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
import com.cnm.birdview.ui.contract.ProductsDetailContract
import com.cnm.birdview.ui.presenter.ProductsDetailPresenter
import kotlinx.android.synthetic.main.fragment_products_detail.*


class ProductsDetailFragment : RoundedBottomSheetDialogFragment(), ProductsDetailContract.View {
    private val presenter: ProductsDetailContract.Presenter by lazy {
        ProductsDetailPresenter(this@ProductsDetailFragment)
    }
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
        presenter.detailApiCall(product)

        tv_close.setOnClickListener {
            dismiss()
        }
        bt_buy.startAnimation(anim)
    }

    override fun onDestroy() {
        presenter.disposableClear()
        super.onDestroy()
    }


    @SuppressLint("SetTextI18n")
    override fun setView(item: ProductsDetailResponse.Body) {
        with(item) {
            Glide.with(this@ProductsDetailFragment)
                .load(fullSizeImage)
                .into(iv_detail_image)
            tv_detail_title.text = title
            tv_detail_price.text = "${presenter.makeCommaNumber(price.toInt())}원"
            tv_detail_description.text = description.replace("\\n", "\n")
        }
    }
}

