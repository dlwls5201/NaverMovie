package com.tistory.black_jin0427.navermovie

import com.tistory.black_jin0427.navermovie.domain.model.BookItemEntity
import com.tistory.black_jin0427.navermovie.domain.model.MovieItemEntity
import com.tistory.black_jin0427.navermovie.domain.usecase.GetContentsUsecase
import com.tistory.black_jin0427.navermovie.presentation.main.MainContract
import com.tistory.black_jin0427.navermovie.presentation.main.MainPresenter
import com.tistory.black_jin0427.navermovie.presentation.model.mapToPresentation
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InOrder
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock
    private lateinit var view: MainContract.View

    @Mock
    private lateinit var userCase: GetContentsUsecase

    private lateinit var presenter: MainPresenter

    private lateinit var inOrder: InOrder

    @Before
    fun setUp() {

        presenter = MainPresenter(view, userCase)

        inOrder = Mockito.inOrder(view, userCase)
    }

    @Test
    fun test_load_empty_data_success() {

        val query = "test"

        val result = Pair(listOf<BookItemEntity>(), listOf<MovieItemEntity>())

        //given
        `when`(userCase.get(query)).thenReturn(Single.just(result))

        //when
        presenter.loadData(query)

        //then
        verify(view).showProgress()
        verify(view).hideProgress()
        verify(view).showSearchTextEmpty()
        verify(view).showBookItemsCleared()
        verify(view).showMovieItemsCleared()
        verify(view).showEmptyText()
    }

    @Test
    fun test_load_data_success() {

        val query = "test"

        val bookItems = listOf(
            BookItemEntity("image", "string")
        )
        val movieItems = listOf(
            MovieItemEntity("title", "image","director", "actor",10f)
        )

        val result = Pair(bookItems, movieItems)

        //given
        `when`(userCase.get(query)).thenReturn(Single.just(result))

        //when
        presenter.loadData(query)

        //then
        verify(view).showProgress()
        verify(view).hideProgress()
        verify(view).showSearchTextEmpty()
        verify(view).showBookItems(bookItems.mapToPresentation())
        verify(view).showMovieItems(movieItems.mapToPresentation())
        verify(view).hideEmptyText()
    }
}