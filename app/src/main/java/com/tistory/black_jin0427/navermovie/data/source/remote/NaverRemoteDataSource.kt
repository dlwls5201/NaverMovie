package com.tistory.black_jin0427.navermovie.data.source.remote

import com.tistory.black_jin0427.navermovie.domain.model.Book
import com.tistory.black_jin0427.navermovie.domain.model.BookItem
import com.tistory.black_jin0427.navermovie.domain.model.Movie
import com.tistory.black_jin0427.navermovie.domain.model.MovieItem
import io.reactivex.Single

interface NaverRemoteDataSource {

    fun getMovie(
        query: String
    ): Single<Movie>

    fun getBook(
        query: String
    ): Single<Book>
}