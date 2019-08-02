package com.tistory.black_jin0427.navermovie.data.repository

import com.tistory.black_jin0427.navermovie.data.source.remote.NaverRemoteDataSource
import com.tistory.black_jin0427.navermovie.domain.model.MovieItem
import io.reactivex.Single

class MovieRepositoryImpl(
    private val remoteDataSource: NaverRemoteDataSource
) : MovieRepository {

    override fun get(query: String): Single<List<MovieItem>> {
        return remoteDataSource.getMovie(query)

    }
}