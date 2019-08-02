package com.tistory.black_jin0427.navermovie.domain

import com.tistory.black_jin0427.navermovie.constant.SchedulersProvider
import io.reactivex.Single

abstract class SingleUseCase<T, in Params>(
    private val schedulersProvider: SchedulersProvider
) {
    protected abstract fun buildUseCaseSingle(params: Params): Single<T>

    fun get(params: Params) = buildUseCaseSingle(params)
        .subscribeOn(schedulersProvider.io())
        .observeOn(schedulersProvider.ui())
}