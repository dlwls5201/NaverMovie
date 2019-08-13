package com.tistory.black_jin0427.navermovie.presentation

interface BasePresenter<in T: BaseView> {

    fun attachView(view: T)

    fun detachView()
}