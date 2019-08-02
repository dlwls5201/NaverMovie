package com.tistory.black_jin0427.navermovie.domain.usecase

import com.tistory.black_jin0427.navermovie.data.repository.BookRepository
import com.tistory.black_jin0427.navermovie.data.repository.MovieRepository
import com.tistory.black_jin0427.navermovie.data.response.BookResponse
import com.tistory.black_jin0427.navermovie.domain.model.Contents
import com.tistory.black_jin0427.navermovie.data.response.MovieResponse
import com.tistory.black_jin0427.navermovie.data.source.remote.model.Book
import com.tistory.black_jin0427.navermovie.data.source.remote.model.Movie
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
            BiFunction<Book, Movie, Contents> { book, movie ->

                val bookResponses =
                    book.items.map {
                        BookResponse(
                            it.title,
                            it.image
                        )
                    }

                val movieResponses =
                    movie.items.map { movieItem ->
                        MovieResponse(
                            movieItem.title,
                            movieItem.image,
                            movieItem.director.replace("|",", "),
                            movieItem.actor.replace("|",", "),
                            movieItem.userRating.toString()
                        )
                    }

                Contents(bookResponses, movieResponses)
            }
        )
    }

}