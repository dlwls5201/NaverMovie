package com.tistory.black_jin0427.navermovie.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.black_jin0427.navermovie.adapter.MainAdapter
import com.tistory.black_jin0427.navermovie.api.NaverApi
import com.tistory.black_jin0427.navermovie.api.model.MovieItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieListViewModel(
    private val mainAdapter: MainAdapter,
    private val api: NaverApi)
    : DisposableViewModel() {

    private val _progressView = MutableLiveData<Int>()
    private val _items = MutableLiveData<List<MovieItem>>()
    private val _adapter= MutableLiveData<MainAdapter>().apply { value =  mainAdapter }

    //mutableLiveData 를 immutable 하게 노출
    //ViewModel 내부에서는 Mutable 한 데이터를 외부에서는 Immutable 하게 사용하도록 제약을 주기 위함
    val progressView: LiveData<Int> get() = _progressView
    val items: LiveData<List<MovieItem>> get() = _items
    val adapter: LiveData<MainAdapter> get() = _adapter

    init {

        loadData()

    }

    private fun loadData() {

        addDisposable(
            api.movie("마블")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    showProgress()
                }
                .doOnSubscribe {
                    hideProgress()
                }
                .doOnError {
                    hideProgress()
                }
                .subscribe({
                    _items.value = it.items
                },{ error
                    -> Log.e("blackJin", error.message)
                })
        )

    }

    private fun showProgress() {
        _progressView.value = View.VISIBLE
    }

    private fun hideProgress() {
        _progressView.value = View.INVISIBLE
    }

}
