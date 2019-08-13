package com.tistory.black_jin0427.navermovie.presentation.main

import com.tistory.black_jin0427.navermovie.presentation.BasePresenter
import com.tistory.black_jin0427.navermovie.presentation.BaseView

interface MainContract {

    interface View: BaseView {

    }

    interface Presenter: BasePresenter<View> {

    }

}