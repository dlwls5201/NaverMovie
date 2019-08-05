package com.tistory.black_jin0427.navermovie.data.repository

import com.tistory.black_jin0427.navermovie.domain.model.BookEntity
import io.reactivex.Single

interface BookRepository {

    fun get(query: String): Single<BookEntity>
}