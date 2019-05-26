package com.tistory.black_jin0427.navermovie.viewModel.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tistory.black_jin0427.navermovie.adapter.MainAdapter
import com.tistory.black_jin0427.navermovie.api.NaverApi

class MovieListViewModelFactory(private val mainAdapter: MainAdapter, private val api: NaverApi)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(mainAdapter, api) as T
    }
}
