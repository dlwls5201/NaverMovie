package com.tistory.black_jin0427.navermovie.presentation.main

import com.tistory.black_jin0427.navermovie.presentation.BasePresenter
import com.tistory.black_jin0427.navermovie.presentation.BaseView
import com.tistory.black_jin0427.navermovie.presentation.model.BookItem
import com.tistory.black_jin0427.navermovie.presentation.model.MovieItem

interface MainContract {

    interface View : BaseView {

        fun showBookItems(items: MutableList<BookItem>)
        fun showMovieItems(items: MutableList<MovieItem>)

        fun showBookItemsCleared()
        fun showMovieItemsCleared()

        fun showEmptyText()
        fun hideEmptyText()

        fun showSearchTextEmpty()

        fun showProgress()
        fun hideProgress()
    }

    interface Presenter : BasePresenter {

        fun loadData(query: String)
    }

}