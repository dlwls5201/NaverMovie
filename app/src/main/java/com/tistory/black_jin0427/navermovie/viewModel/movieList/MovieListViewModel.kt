package com.tistory.black_jin0427.navermovie.viewModel.movieList

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.black_jin0427.navermovie.adapter.MainAdapter
import com.tistory.black_jin0427.navermovie.api.NaverApi
import com.tistory.black_jin0427.navermovie.api.model.MovieItem
import com.tistory.black_jin0427.navermovie.viewModel.DisposableViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieListViewModel(
    private val mainAdapter: MainAdapter,
    private val api: NaverApi)
    : DisposableViewModel() {

    private val _searchQuery = MutableLiveData<String>()
    private val _emptyDataView = MutableLiveData<Int>()
    private val _progressView = MutableLiveData<Int>()
    private val _items = MutableLiveData<List<MovieItem>>()
    private val _adapter= MutableLiveData<MainAdapter>().apply { value =  mainAdapter }

    //mutableLiveData 를 immutable 하게 노출
    //ViewModel 내부에서는 Mutable 한 데이터를 외부에서는 Immutable 하게 사용하도록 제약을 주기 위함
    val searchQuery: LiveData<String> get() = _searchQuery
    val emptyDataView: LiveData<Int> get() = _emptyDataView
    val progressView: LiveData<Int> get() = _progressView
    val items: LiveData<List<MovieItem>> get() = _items
    val adapter: LiveData<MainAdapter> get() = _adapter

    init {

        showEmptyDataView()
        hideProgress()

    }

    fun loadData(query: String) {

        showProgress()

        addDisposable(
            api.movie(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    if(it.items.isNullOrEmpty()) {
                        showEmptyDataView()
                    } else {
                        hideEmptyDataView()
                    }

                    _items.value = it.items

                    setSearchQueryToNull()
                    hideProgress()

                },{ error
                    -> Log.e("blackJin", error.message)
                })
        )

    }

    private fun setSearchQueryToNull() {
        _searchQuery.value = null
    }

    private fun showEmptyDataView() {
        _emptyDataView.value = View.VISIBLE
    }

    private fun hideEmptyDataView() {
        _emptyDataView.value = View.GONE
    }

    private fun showProgress() {
        _progressView.value = View.VISIBLE
    }

    private fun hideProgress() {
        _progressView.value = View.GONE
    }

}
