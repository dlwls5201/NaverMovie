package com.tistory.black_jin0427.navermovie.data.source.remote.model


import com.google.gson.annotations.SerializedName

data class BookEntity(
    @SerializedName("lastBuildDate") val lastBuildDate: String,
    @SerializedName("total") val total: Int,
    @SerializedName("start") val start: Int,
    @SerializedName("display") val display: Int,
    @SerializedName("itemEntities") val itemEntities: List<BookItemEntity>
)