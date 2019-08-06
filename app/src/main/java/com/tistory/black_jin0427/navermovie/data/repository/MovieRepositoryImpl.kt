package com.tistory.black_jin0427.navermovie.data.repository

import com.tistory.black_jin0427.navermovie.data.source.remote.NaverRemoteDataSource
import com.tistory.black_jin0427.navermovie.domain.model.MovieEntity
import io.reactivex.Single

class MovieRepositoryImpl(
    private val remoteDataSource: NaverRemoteDataSource
) : MovieRepository {

    override fun get(query: String): Single<MovieEntity> {
        return remoteDataSource.getMovie(query)

    }

    companion object {

        private var INSTANCE: MovieRepositoryImpl? = null

        fun getInstance(remoteDataSource: NaverRemoteDataSource) : MovieRepository =
            INSTANCE ?: MovieRepositoryImpl(remoteDataSource).apply {
                INSTANCE = this
            }
    }
}