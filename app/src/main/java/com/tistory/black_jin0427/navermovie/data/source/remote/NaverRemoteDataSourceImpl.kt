package com.tistory.black_jin0427.navermovie.data.source.remote

import com.tistory.black_jin0427.navermovie.data.source.remote.model.mapToDomain
import com.tistory.black_jin0427.navermovie.domain.model.BookEntity
import com.tistory.black_jin0427.navermovie.domain.model.MovieEntity
import io.reactivex.Single

class NaverRemoteDataSourceImpl(
    private val api: NaverService
) : NaverRemoteDataSource {

    override fun getMovie(query: String): Single<MovieEntity> =
        api.getMovie(query).map { it.mapToDomain() }

    override fun getBook(query: String): Single<BookEntity> =
        api.getBook(query).map { it.mapToDomain() }
}