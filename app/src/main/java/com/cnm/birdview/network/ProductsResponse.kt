package com.cnm.birdview.network


import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("body")
    val body: List<Body>,
    @SerializedName("scanned_count")
    val scannedCount: Int,
    @SerializedName("statusCode")
    val statusCode: Int
) {
    data class Body(
        @SerializedName("id")
        val id: Int,
        @SerializedName("oily_score")
        val oilyScore: Int,
        @SerializedName("dry_score")
        val dryScore: Int,
        @SerializedName("sensitive_score")
        val sensitiveScore: Int,
        @SerializedName("price")
        val price: String,
        @SerializedName("thumbnail_image")
        val thumbnailImage: String,
        @SerializedName("title")
        val title: String
    )
}