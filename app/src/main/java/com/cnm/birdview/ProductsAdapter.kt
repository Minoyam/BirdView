package com.cnm.birdview

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cnm.birdview.network.ProductsResponse
import kotlinx.android.synthetic.main.item_main.view.*

class ProductsAdapter(private val itemOnClickListener: ItemOnClickListener) :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    private val productsItems = mutableListOf<ProductsResponse.Body>()

    fun setItem(items: List<ProductsResponse.Body>) {
        productsItems.clear()
        productsItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ProductsViewHolder(view, itemOnClickListener)
    }

    override fun getItemCount(): Int {
        return productsItems.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(productsItems[position])
    }

    interface ItemOnClickListener {
        fun itemOnClick(productsItem: ProductsResponse.Body)
    }

    inner class ProductsViewHolder(view: View, itemOnClickListener: ItemOnClickListener) :
        RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                itemOnClickListener.itemOnClick(productsItems[adapterPosition])
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: ProductsResponse.Body) {
            with(itemView) {
                Glide.with(this)
                    .load(item.thumbnailImage)
                    .override(165, 165)
                    .into(iv_thumbnail_image)
                tv_title.text = item.title
                tv_price.text = "${item.price}원"
            }
        }
    }

}