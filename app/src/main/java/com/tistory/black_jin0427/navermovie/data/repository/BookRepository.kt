package com.tistory.black_jin0427.navermovie.data.repository

import com.tistory.black_jin0427.navermovie.data.source.remote.model.Book
import io.reactivex.Single

interface BookRepository {

    fun get(query: String): Single<Book>
}