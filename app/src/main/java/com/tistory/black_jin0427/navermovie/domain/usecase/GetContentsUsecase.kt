package com.tistory.black_jin0427.navermovie.domain.usecase

import com.tistory.black_jin0427.navermovie.constant.SchedulersProvider
import com.tistory.black_jin0427.navermovie.data.repository.BookRepository
import com.tistory.black_jin0427.navermovie.data.repository.MovieRepository
import com.tistory.black_jin0427.navermovie.domain.SingleUseCase
import com.tistory.black_jin0427.navermovie.domain.model.BookEntity
import com.tistory.black_jin0427.navermovie.domain.model.BookItemEntity
import com.tistory.black_jin0427.navermovie.domain.model.MovieEntity
import com.tistory.black_jin0427.navermovie.domain.model.MovieItemEntity
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GetContentsUsecase(
    private val bookRepository: BookRepository,
    private val movieRepository: MovieRepository,
    schedulersProvider: SchedulersProvider
) : SingleUseCase<Pair<List<BookItemEntity>, List<MovieItemEntity>>, String>(
    schedulersProvider
) {

    override fun buildUseCaseSingle(params: String): Single<Pair<List<BookItemEntity>, List<MovieItemEntity>>> {
        return Single.zip(
            bookRepository.get(params),
            movieRepository.get(params),
            BiFunction<BookEntity, MovieEntity, Pair<List<BookItemEntity>, List<MovieItemEntity>>> { book, movie ->
                Pair(book.items, movie.items)
            }
        )
    }
}