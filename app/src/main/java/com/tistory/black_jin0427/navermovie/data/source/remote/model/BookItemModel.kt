package com.tistory.black_jin0427.navermovie.data.source.remote.model


import com.google.gson.annotations.SerializedName

data class BookItemModel(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("image") val image: String,
    @SerializedName("author") val author: String,
    @SerializedName("price") val price: String,
    @SerializedName("discount") val discount: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("pubdate") val pubdate: String,
    @SerializedName("isbn") val isbn: String,
    @SerializedName("description") val description: String
)