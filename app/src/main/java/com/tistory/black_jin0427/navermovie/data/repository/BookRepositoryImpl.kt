package com.tistory.black_jin0427.navermovie.data.repository

import com.tistory.black_jin0427.navermovie.data.source.remote.NaverRemoteDataSource
import com.tistory.black_jin0427.navermovie.domain.model.BookEntity
import io.reactivex.Single

class BookRepositoryImpl(
    private val remoteDataSource: NaverRemoteDataSource
) : BookRepository {

    override fun get(query: String): Single<BookEntity> {
        return remoteDataSource.getBook(query)

    }

    companion object {

        private var INSTANCE: BookRepositoryImpl? = null

        fun getInstance(remoteDataSource: NaverRemoteDataSource) : BookRepository =
            INSTANCE ?: BookRepositoryImpl(remoteDataSource).apply {
                INSTANCE = this
            }
    }
}