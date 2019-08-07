package com.tistory.black_jin0427.navermovie.presentation.model

import com.tistory.black_jin0427.navermovie.domain.model.BookItemEntity

data class BookItem(
    val title: String,
    val image: String
)

fun BookItemEntity.mapToPresentation(): BookItem = BookItem(
    title,
    image
)

fun List<BookItemEntity>.mapToPresentation(): MutableList<BookItem> = map { it.mapToPresentation() }.toMutableList()