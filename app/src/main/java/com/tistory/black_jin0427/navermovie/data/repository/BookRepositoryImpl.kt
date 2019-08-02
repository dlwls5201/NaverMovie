package com.tistory.black_jin0427.navermovie.data.repository

import com.tistory.black_jin0427.navermovie.data.source.remote.NaverService
import com.tistory.black_jin0427.navermovie.data.source.remote.model.Book
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BookRepositoryImpl(
    private val api: NaverService
): BookRepository {

    override fun get(query: String): Single<Book> {
        return api.getBook(query)

    }
}