package com.tistory.black_jin0427.navermovie.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class MovieItemModel(
    @field:SerializedName("title") val title: String,
    @field:SerializedName("link") val link: String,
    @field:SerializedName("image") val image: String,
    @field:SerializedName("subtitle") val subtitle: String,
    @field:SerializedName("pubDate") val pubDate: Float,
    @field:SerializedName("director") val director: String,
    @field:SerializedName("actor") val actor: String,
    @field:SerializedName("userRating") val userRating: Float
)