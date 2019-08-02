package com.tistory.black_jin0427.navermovie.domain

import com.tistory.black_jin0427.navermovie.constant.SchedulersProvider
import io.reactivex.Completable

abstract class CompletableUseCase<in Params>(
    private val schedulersProvider: SchedulersProvider
) {
    protected abstract fun buildUseCaseCompletable(params: Params): Completable

    fun get(params: Params): Completable = buildUseCaseCompletable(params)
        .subscribeOn(schedulersProvider.io())
        .observeOn(schedulersProvider.ui())
}