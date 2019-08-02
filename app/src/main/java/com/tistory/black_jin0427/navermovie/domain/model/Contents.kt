package com.tistory.black_jin0427.navermovie.domain.model

import com.tistory.black_jin0427.navermovie.data.response.BookResponse
import com.tistory.black_jin0427.navermovie.data.response.MovieResponse

data class Contents(
    val books: List<BookResponse>,
    val movies: List<MovieResponse>
)