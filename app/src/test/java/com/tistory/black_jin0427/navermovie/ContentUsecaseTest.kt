package com.tistory.black_jin0427.navermovie

import com.tistory.black_jin0427.navermovie.data.repository.BookRepository
import com.tistory.black_jin0427.navermovie.data.repository.MovieRepository
import com.tistory.black_jin0427.navermovie.domain.SingleUseCase
import com.tistory.black_jin0427.navermovie.domain.model.BookEntity
import com.tistory.black_jin0427.navermovie.domain.model.BookItemEntity
import com.tistory.black_jin0427.navermovie.domain.model.MovieEntity
import com.tistory.black_jin0427.navermovie.domain.model.MovieItemEntity
import com.tistory.black_jin0427.navermovie.domain.usecase.GetContentsUsecase
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ContentUsecaseTest {

    @Mock
    private lateinit var bookRepository: BookRepository

    @Mock
    private lateinit var movieRepository: MovieRepository

    private val schedulerProvider = trampolineSchedulerProvider()

    private lateinit var getContentsUsecase: SingleUseCase<Pair<List<BookItemEntity>, List<MovieItemEntity>>, String>

    @Before
    fun setUp() {

        getContentsUsecase = GetContentsUsecase(bookRepository, movieRepository, schedulerProvider)
    }

    @Test
    fun test_load_data_success() {

        val query = "test"

        val bookItems = listOf(
            BookItemEntity("image", "string")
        )
        val movieItems = listOf(
            MovieItemEntity("title", "image", "director", "actor", 10f)
        )

        val result = Single.just(Pair(bookItems, movieItems))

        `when`(bookRepository.get(query)).thenReturn(
            Single.just(BookEntity("", 0, 0, 0, items = bookItems))
        )

        `when`(movieRepository.get(query)).thenReturn(
            Single.just(MovieEntity("", 0, 0, 0, items = movieItems))
        )

        //when
        getContentsUsecase.get(query)

        //TODO then

    }

}