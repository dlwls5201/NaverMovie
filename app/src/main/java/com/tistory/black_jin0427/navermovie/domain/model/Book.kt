package com.tistory.black_jin0427.navermovie.domain.model

data class Book (
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val itemEntities: List<BookItem>
)