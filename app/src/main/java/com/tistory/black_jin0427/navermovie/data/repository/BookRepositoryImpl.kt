package com.tistory.black_jin0427.navermovie.data.repository

import com.tistory.black_jin0427.navermovie.data.source.remote.NaverRemoteDataSource
import com.tistory.black_jin0427.navermovie.data.source.remote.NaverService
import com.tistory.black_jin0427.navermovie.data.source.remote.model.BookEntity
import com.tistory.black_jin0427.navermovie.domain.model.BookItem
import io.reactivex.Single

class BookRepositoryImpl(
    private val remoteDataSource: NaverRemoteDataSource
): BookRepository {

    override fun get(query: String): Single<List<BookItem>> {
        return remoteDataSource.getBook(query)

    }
}