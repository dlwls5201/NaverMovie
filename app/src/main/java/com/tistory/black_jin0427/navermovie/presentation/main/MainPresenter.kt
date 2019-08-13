package com.tistory.black_jin0427.navermovie.presentation.main

import com.tistory.black_jin0427.navermovie.domain.usecase.GetContentsUsecase
import com.tistory.black_jin0427.navermovie.presentation.model.mapToPresentation
import com.tistory.black_jin0427.navermovie.utils.Dlog
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(
    private val view: MainContract.View,
    private val getContentsUsecase: GetContentsUsecase
) : MainContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun attachView() {
        //...
    }

    override fun detachView() {
        compositeDisposable.clear()
    }

    override fun loadData(query: String) {
        getContentsUsecase.get(query)
            .doOnSubscribe {
                view.showProgress()
            }.doOnSuccess {
                view.hideProgress()
            }.subscribe({
                view.showSearchTextEmpty()

                if (it.first.isEmpty()) {
                    view.showBookItemsCleared()
                } else {
                    view.showBookItems(it.first.mapToPresentation())
                }

                if (it.second.isEmpty()) {
                    view.showMovieItemsCleared()
                } else {
                    view.showMovieItems(it.second.mapToPresentation())
                }

                if (it.first.isEmpty() && it.second.isEmpty()) {
                    view.showEmptyText()
                } else {
                    view.hideEmptyText()
                }

            }) {
                Dlog.e(it.message)
                view.showToast("error")
                view.hideProgress()
            }.also {
                compositeDisposable.add(it)
            }
    }

}