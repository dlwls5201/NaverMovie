package com.tistory.black_jin0427.navermovie.data.source.remote.model


import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("lastBuildDate") val lastBuildDate: String,
    @SerializedName("total") val total: Int,
    @SerializedName("start") val start: Int,
    @SerializedName("display") val display: Int,
    @SerializedName("items") val items: List<BookItem>
)