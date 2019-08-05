package com.tistory.black_jin0427.navermovie.data.source.remote

import com.tistory.black_jin0427.navermovie.domain.model.BookEntity
import com.tistory.black_jin0427.navermovie.domain.model.MovieEntity
import io.reactivex.Single

interface NaverRemoteDataSource {

    fun getMovie(
        query: String
    ): Single<MovieEntity>

    fun getBook(
        query: String
    ): Single<BookEntity>
}