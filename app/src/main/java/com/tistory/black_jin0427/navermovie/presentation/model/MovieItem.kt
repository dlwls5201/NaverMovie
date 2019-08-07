package com.tistory.black_jin0427.navermovie.presentation.model

import com.tistory.black_jin0427.navermovie.domain.model.MovieItemEntity

data class MovieItem(
    val title: String,
    val image: String,
    val director: String,
    val actor: String,
    val rating: String
)

fun MovieItemEntity.mapToPresentation(): MovieItem = MovieItem(
    title,
    image,
    director.replace("|", ", ").dropLast(2),
    actor.replace("|", ", ").dropLast(2),
    rating.toString()
)

fun List<MovieItemEntity>.mapToPresentation(): MutableList<MovieItem> = map { it.mapToPresentation() }.toMutableList()