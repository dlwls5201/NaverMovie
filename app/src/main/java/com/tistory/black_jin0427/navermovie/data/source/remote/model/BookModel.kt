package com.tistory.black_jin0427.navermovie.data.source.remote.model


import com.google.gson.annotations.SerializedName
import com.tistory.black_jin0427.navermovie.domain.model.BookEntity
import com.tistory.black_jin0427.navermovie.domain.model.BookItemEntity

data class BookModel(
    @SerializedName("lastBuildDate") val lastBuildDate: String,
    @SerializedName("total") val total: Int,
    @SerializedName("start") val start: Int,
    @SerializedName("display") val display: Int,
    @SerializedName("items") val items: List<BookItemModel>
)

fun BookModel.mapToDomain() =
    BookEntity(lastBuildDate, total, start, display, items.mapToDomain())

fun List<BookItemModel>.mapToDomain(): List<BookItemEntity> = map { BookItemEntity(it.title, it.image) }