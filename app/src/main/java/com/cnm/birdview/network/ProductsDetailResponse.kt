package com.cnm.birdview.network


import com.google.gson.annotations.SerializedName

data class ProductsDetailResponse(
    @SerializedName("body")
    val body: Body,
    @SerializedName("statusCode")
    val statusCode: Int
) {
    data class Body(
        @SerializedName("description")
        val description: String,
        @SerializedName("dry_score")
        val dryScore: Int,
        @SerializedName("full_size_image")
        val fullSizeImage: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("oily_score")
        val oilyScore: Int,
        @SerializedName("price")
        val price: String,
        @SerializedName("sensitive_score")
        val sensitiveScore: Int,
        @SerializedName("title")
        val title: String
    )
}