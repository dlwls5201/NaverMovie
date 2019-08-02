package com.tistory.black_jin0427.navermovie.domain.usecase

import com.tistory.black_jin0427.navermovie.data.repository.BookRepository
import com.tistory.black_jin0427.navermovie.data.repository.MovieRepository
import com.tistory.black_jin0427.navermovie.domain.model.BookItem
import com.tistory.black_jin0427.navermovie.domain.model.Contents
import com.tistory.black_jin0427.navermovie.domain.model.MovieItem
import com.tistory.black_jin0427.navermovie.data.source.remote.model.BookEntity
import com.tistory.black_jin0427.navermovie.data.source.remote.model.MovieEntity
import com.tistory.black_jin0427.navermovie.constant.SchedulersProvider
import com.tistory.black_jin0427.navermovie.domain.SingleUseCase
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GetContentsUsecase(
    private val bookRepository: BookRepository,
    private val movieRepository: MovieRepository,
    schedulersProvider: SchedulersProvider
) : SingleUseCase<Contents, String>(
    schedulersProvider
) {
    override fun buildUseCaseSingle(params: String): Single<Contents> {
        return Single.zip(
            bookRepository.get(params),
            movieRepository.get(params),
            BiFunction<List<BookItem>, List<MovieItem>, Contents> { book, movie ->
                Contents(book, movie)
            }
        )
    }

}